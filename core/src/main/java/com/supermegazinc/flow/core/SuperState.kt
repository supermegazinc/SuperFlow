package com.supermegazinc.flow.core

import kotlinx.coroutines.flow.StateFlow

interface SuperState<out T> {
	val value: StateFlow<T>
}