package com.supermegazinc.flow.compose

import androidx.compose.runtime.Stable
import kotlin.reflect.KProperty

@Stable
interface SuperMutableCompose<T> {
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}
