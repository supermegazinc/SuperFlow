package com.supermegazinc.flow.compose

import androidx.compose.runtime.Stable

@Stable
interface SuperStateCompose<T> {
	val value: T
}
