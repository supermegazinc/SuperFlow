package com.supermegazinc.flow.core

import kotlinx.coroutines.flow.StateFlow

interface SuperMutableState<T> : SuperState<T>, SuperMutable<T> {
	override val value: StateFlow<T>
	override fun update(new: (value: T) -> T)
}