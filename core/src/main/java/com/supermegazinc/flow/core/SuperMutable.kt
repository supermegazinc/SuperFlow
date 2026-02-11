package com.supermegazinc.flow.core

import kotlinx.coroutines.flow.StateFlow

interface SuperMutable<T> {
	fun update(new: (value: T) -> T)
}