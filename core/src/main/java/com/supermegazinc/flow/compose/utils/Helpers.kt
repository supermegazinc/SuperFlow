package com.supermegazinc.flow.compose.utils

import com.supermegazinc.flow.compose.SuperMutableCompose
import com.supermegazinc.flow.compose.SuperStateCompose
import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
inline operator fun<T> SuperStateCompose<T>.getValue(thisRef: Any?, property: KProperty<*>): T = value

@Suppress("NOTHING_TO_INLINE")
inline operator fun<T> SuperMutableCompose<T>.setValue(
	thisRef: Any?,
	property: KProperty<*>,
	value: T
) = set(value)

