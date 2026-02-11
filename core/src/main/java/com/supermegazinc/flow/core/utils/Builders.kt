package com.supermegazinc.flow.core.utils

import kotlinx.coroutines.flow.MutableStateFlow

fun <T>SuperMutableState(value: T) = MutableStateFlow(value).asSuperMutableState()