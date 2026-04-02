package com.supermegazinc.flow.core

interface SuperMutable<T> {
	fun update(new: (value: T) -> T)
}