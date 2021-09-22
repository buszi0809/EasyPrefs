package com.kwdev.easyprefs.properties

import com.kwdev.easyprefs.EasyPrefs
import kotlin.properties.ReadWriteProperty

sealed interface EasyPrefsProperty<T> : ReadWriteProperty<EasyPrefs, T>
