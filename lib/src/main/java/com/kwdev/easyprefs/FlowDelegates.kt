package com.kwdev.easyprefs

import com.kwdev.easyprefs.flow.*
import kotlin.reflect.KProperty

/**
 * Kotlin flow delegate of [Boolean] easy prefs property. To use this delegate the parent class needs to implement [EasyPrefsFlow] abstraction.
 * The [key] is property that you want to observe with use of this flow. The [key] property needs to be within the same class as this flow delegate.
 */
fun integerFlow(
    key: KProperty<Int>,
    default: Int = 0,
): EasyPropertyFlow<Int> = EasyIntegerPropertyFlow(key, default)

/**
 * Kotlin flow delegate of [Long] easy prefs property. To use this delegate the parent class needs to implement [EasyPrefsFlow] abstraction.
 * The [key] is property that you want to observe with use of this flow. The [key] property needs to be within the same class as this flow delegate.
 */
fun longFlow(
    key: KProperty<Long>,
    default: Long = 0L,
): EasyPropertyFlow<Long> = EasyLongPropertyFlow(key, default)

/**
 * Kotlin flow delegate of [Boolean] easy prefs property. To use this delegate the parent class needs to implement [EasyPrefsFlow] abstraction.
 * The [key] is property that you want to observe with use of this flow. The [key] property needs to be within the same class as this flow delegate.
 */
fun booleanFlow(
    key: KProperty<Boolean>,
    default: Boolean = false,
): EasyPropertyFlow<Boolean> = EasyBooleanPropertyFlow(key, default)

/**
 * Kotlin flow delegate of nullable [Set] of [String] easy prefs property. To use this delegate the parent class needs to implement [EasyPrefsFlow] abstraction.
 * The [key] is property that you want to observe with use of this flow. The [key] property needs to be within the same class as this flow delegate.
 */
fun stringSetFlow(
    key: KProperty<Set<String>?>,
): EasyPropertyFlow<Set<String>?> = EasyStringSetPropertyFlow(key)

/**
 * Kotlin flow delegate of nullable [String] easy prefs property. To use this delegate the parent class needs to implement [EasyPrefsFlow] abstraction.
 * The [key] is property that you want to observe with use of this flow. The [key] property needs to be within the same class as this flow delegate.
 */
fun stringFlow(
    key: KProperty<String?>,
): EasyPropertyFlow<String?> = EasyStringPropertyFlow(key)

/**
 * Kotlin flow delegate of custom type [T] easy prefs property. To use this delegate the parent class needs to implement [EasyPrefsFlow] abstraction.
 * The [key] is property that you want to observe with use of this flow. The [key] property needs to be within the same class as this flow delegate.
 * Requires [TypeAdapter] implementation of type [T].
 */
fun <T> customFlow(
    key: KProperty<T>,
    adapter: TypeAdapter<T>
): EasyPropertyFlow<T> = EasyCustomTypePropertyFlow(key, adapter)
