package com.kwdev.easyprefs

import kotlin.reflect.KProperty

internal fun getKeyFor(thisRef: EasyPrefs, property: KProperty<*>): String =
    "${thisRef::class.qualifiedName}_${property.name}"
