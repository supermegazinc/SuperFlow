package com.supermegazinc.flow.core

import com.supermegazinc.flow.core.utils.SuperMutableState
import com.supermegazinc.flow.core.utils.asSuperMutableState
import com.supermegazinc.flow.core.utils.bind
import com.supermegazinc.flow.core.utils.bindGetter
import com.supermegazinc.flow.core.utils.bindSetter
import com.supermegazinc.flow.core.utils.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class SuperMutableStateTest {

	companion object {
		data class ExampleForm(
			val input1: String = "",
			val input2: String = "",
			val input3: String = "",
		)
	}

	@Test
	fun `Create `() {
		val state = SuperMutableState(ExampleForm())
		println(state)
	}

	@Test
	fun `Create using MutableStateFlow`() {
		val state = MutableStateFlow(ExampleForm()).asSuperMutableState()
		println(state)
	}

	@Test
	fun `Update `() {
		val state = SuperMutableState(ExampleForm())
		assertEquals(ExampleForm(), state.value.value)
		val new = ExampleForm("a","a","a")
		state.set(new)
		assertEquals(new, state.value.value)
	}

	@Test
	fun `Bind getter`(): Unit = runBlocking {
		val state = SuperMutableState(ExampleForm())
		val bindInput1 = state.bindGetter(
			prop = ExampleForm::input1,
			initialValue = "",
			coroutineScope = CoroutineScope(this.coroutineContext + Job())
		)
		assertEquals(ExampleForm().input1, bindInput1.value.value)

		val new = ExampleForm("a","a","a")
		state.set(new)
		delay(50)
		assertEquals(new.input1, bindInput1.value.value)
	}

	@Test
	fun `Bind setter`(): Unit = runBlocking {
		val state = SuperMutableState(ExampleForm())
		val bindInput1 = state.bindSetter(
			prop = ExampleForm::input1
		)
		assertEquals(ExampleForm(), state.value.value)
		val new = ExampleForm("a","a","a")
		bindInput1.set(new.input1)
		delay(50)
		assertEquals(ExampleForm().copy(input1 = new.input1), state.value.value)
	}

	@Test
	fun `Bind getter & setter`(): Unit = runBlocking {
		val state = SuperMutableState(ExampleForm())

		val input1Setter = state.bindSetter(
			prop = ExampleForm::input1
		)
		val input1getter = state.bindGetter(
			prop = ExampleForm::input1,
			initialValue = "",
			coroutineScope = CoroutineScope(this.coroutineContext + Job())
		)
		assertEquals(ExampleForm(), state.value.value)
		val new = ExampleForm("a","a","a")
		input1Setter.set(new.input1)
		delay(50)
		assertEquals(new.input1, input1getter.value.value)
		delay(50)
		assertEquals(ExampleForm().copy(input1 = new.input1), state.value.value)
	}
	@Test
	fun `Bind`(): Unit = runBlocking {
		val state = SuperMutableState(ExampleForm())

		val input1bind = state.bind(
			prop = ExampleForm::input1,
			initialValue = "",
			coroutineScope = CoroutineScope(this.coroutineContext + Job())
		)
		assertEquals(ExampleForm(), state.value.value)
		val new = ExampleForm("a","a","a")
		input1bind.set(new.input1)
		delay(50)
		assertEquals(new.input1, input1bind.value.value)
		delay(50)
		assertEquals(ExampleForm().copy(input1 = new.input1), state.value.value)
	}


}