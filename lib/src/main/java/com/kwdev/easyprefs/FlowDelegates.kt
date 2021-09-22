package com.kwdev.easyprefs

import com.kwdev.easyprefs.flow.*
import com.kwdev.easyprefs.flow.EasyBooleanPropertyFlow
import com.kwdev.easyprefs.flow.EasyCustomTypePropertyFlow
import com.kwdev.easyprefs.flow.EasyIntegerPropertyFlow
import com.kwdev.easyprefs.flow.EasyLongPropertyFlow
import com.kwdev.easyprefs.flow.EasyStringSetPropertyFlow
import com.kwdev.easyprefs.flow.EasyStringPropertyFlow
import kotlin.reflect.KProperty

fun integerFlow(
    key: KProperty<Int>,
    default: Int = 0,
): EasyPropertyFlow<Int> = EasyIntegerPropertyFlow(key, default)

fun longFlow(
    key: KProperty<Long>,
    default: Long = 0L,
): EasyPropertyFlow<Long> = EasyLongPropertyFlow(key, default)

fun booleanFlow(
    key: KProperty<Boolean>,
    default: Boolean = false,
): EasyPropertyFlow<Boolean> = EasyBooleanPropertyFlow(key, default)

fun stringSetFlow(
    key: KProperty<Set<String>>,
    default: Set<String> = setOf(),
): EasyPropertyFlow<Set<String>> = EasyStringSetPropertyFlow(key, default)

fun stringFlow(
    key: KProperty<String?>,
): EasyPropertyFlow<String?> = EasyStringPropertyFlow(key)

fun <T> customFlow(
    key: KProperty<T>,
    adapter: TypeAdapter<T>
): EasyPropertyFlow<T> = EasyCustomTypePropertyFlow(key, adapter)
