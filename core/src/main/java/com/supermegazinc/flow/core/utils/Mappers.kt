package com.supermegazinc.flow.core.utils

import com.supermegazinc.flow.core.SuperMutableState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

fun <T>MutableStateFlow<T>.asSuperMutableState(): SuperMutableState<T> {
	return object: SuperMutableState<T> {
		override val value: StateFlow<T>
			get() = this@asSuperMutableState.asStateFlow()

		override fun update(new: (T) -> T) = this@asSuperMutableState.update(new)
	}
}