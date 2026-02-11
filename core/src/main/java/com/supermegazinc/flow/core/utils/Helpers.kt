package com.supermegazinc.flow.core.utils

import com.supermegazinc.flow.core.SuperMutable

fun <T> SuperMutable<T>.set(new: T) = update { new }