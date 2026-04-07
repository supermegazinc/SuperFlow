package com.supermegazinc.flow.compose

import androidx.compose.runtime.Stable

@Stable
interface SuperMutableCompose<T> {
	fun set(value: T)
}
