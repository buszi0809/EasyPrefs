package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefs
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty

sealed interface EasyPropertyFlow<T> : ReadOnlyProperty<EasyPrefs, Flow<T>>
