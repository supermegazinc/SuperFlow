package com.supermegazinc.flow.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.supermegazinc.flow.compose.SuperMutableCompose
import com.supermegazinc.flow.compose.SuperMutableStateCompose
import com.supermegazinc.flow.compose.SuperStateCompose
import com.supermegazinc.flow.core.SuperMutable
import com.supermegazinc.flow.core.SuperMutableState
import com.supermegazinc.flow.core.SuperState
import com.supermegazinc.flow.core.utils.asGetter
import com.supermegazinc.flow.core.utils.asSetter
import com.supermegazinc.flow.core.utils.set

@Composable
fun <T> SuperState<T>.compose(): SuperStateCompose<T> {
	val value by value.collectAsStateWithLifecycle()
	return remember(this) {
		object: SuperStateCompose<T> {
			override val value: T
				get() = value
		}
	}
}

@Composable
fun <T> SuperMutable<T>.compose(): SuperMutableCompose<T> {
	return remember(this) {
		object: SuperMutableCompose<T> {
			override fun set(value: T) {
				this@compose.set(value)
			}
		}
	}
}

@Composable
fun <T> SuperMutableState<T>.compose(): SuperMutableStateCompose<T> {
	val setter = this@compose.asSetter().compose()
	val getter = this@compose.asGetter().compose()
	return remember(this) {
		object: SuperMutableStateCompose<T> {
			override fun set(value: T) = setter.set(value)
			override val value: T
				get() = getter.value
		}
	}
}