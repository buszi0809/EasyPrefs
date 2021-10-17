package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty

sealed interface EasyPropertyFlow<T> : ReadOnlyProperty<EasyPrefsFlow, Flow<T>>
