package com.supermegazinc.flow.core.utils

import com.supermegazinc.flow.core.SuperMutable
import com.supermegazinc.flow.core.SuperMutableState
import com.supermegazinc.flow.core.SuperState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.reflect.KProperty1
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.jvmErasure

@OptIn(ExperimentalCoroutinesApi::class)
fun <Parent, Field> SuperMutableState<Parent>.bind(
	prop: KProperty1<Parent, Field>,
	initialValue: Field,
	coroutineScope: CoroutineScope
): SuperMutableState<Field> {
	val getter = bindGetter(prop, initialValue, coroutineScope)
	val setter = bindSetter(prop)

	return object: SuperMutableState<Field> {
		override val value: StateFlow<Field>
			get() = getter.value

		@Suppress("UNCHECKED_CAST")
		override fun update(new: (value: Field) -> Field) = setter.update(new)
	}
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <Parent, Field> SuperMutable<Parent>.bindSetter(
	prop: KProperty1<Parent, Field>
): SuperMutable<Field> {
	val parent = this
	val parentClass = prop.parameters.first().type.jvmErasure
	return object: SuperMutable<Field> {
		@Suppress("UNCHECKED_CAST")
		override fun update(new: (value: Field) -> Field) {
			parent.update { old->
				val fieldValue = prop.get(old)
				val copy = parentClass.memberFunctions.first { it.name == "copy" }
				val instanceParam = copy.instanceParameter!!
				copy.callBy(mapOf(
					instanceParam to old,
					copy.parameters.first { it.name == prop.name } to new(fieldValue)
				)) as Parent
			}
		}
	}
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <Parent, Field> SuperState<Parent>.bindGetter(
	prop: KProperty1<Parent, Field>,
	initialValue: Field,
	coroutineScope: CoroutineScope
): SuperState<Field> {
	val parent = this
	return object: SuperState<Field> {
		override val value: StateFlow<Field> = parent.value.mapLatest { prop.get(it) }
			.stateIn(coroutineScope, SharingStarted.Eagerly, initialValue)
	}
}

fun <Parent, R> SuperState<R>.bindTo(
	state: SuperMutable<Parent>,
	prop: KProperty1<Parent, R>,
	coroutineScope: CoroutineScope
) {
    val toBind = state.bindSetter(
        prop = prop
    )
    value
        .onEach(toBind::set)
        .launchIn(coroutineScope)
}