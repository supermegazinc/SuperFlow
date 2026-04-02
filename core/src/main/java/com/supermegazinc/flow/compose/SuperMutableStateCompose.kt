package com.supermegazinc.flow.compose

import androidx.compose.runtime.Stable

@Stable
interface SuperMutableStateCompose<T>: SuperMutableCompose<T>, SuperStateCompose<T>