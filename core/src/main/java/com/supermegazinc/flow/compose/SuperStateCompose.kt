package com.supermegazinc.flow.compose

import androidx.compose.runtime.Stable
import kotlin.reflect.KProperty

@Stable
interface SuperStateCompose<T> {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T
}
