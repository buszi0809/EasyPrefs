package com.kwdev.easyprefs

import com.kwdev.easyprefs.properties.*

/**
 * [Boolean] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun boolean(
    commit: Boolean = false,
    default: Boolean = false,
): EasyPrefsProperty<Boolean> = EasyBooleanProperty(commit, default)

/**
 * [Int] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun integer(
    commit: Boolean = false,
    default: Int = 0,
): EasyPrefsProperty<Int> = EasyIntegerProperty(commit, default)

/**
 * [Long] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun long(
    commit: Boolean = false,
    default: Long = 0L,
): EasyPrefsProperty<Long> = EasyLongProperty(commit, default)

/**
 * [String] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun string(
    commit: Boolean = false,
    default: String = "",
): EasyPrefsProperty<String> = EasyStringProperty(commit, default)

/**
 * Nullable [String] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun nullableString(
    commit: Boolean = false,
    default: String? = null,
): EasyPrefsProperty<String?> = EasyNullableStringProperty(commit, default)

/**
 * [Set] of [String] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun stringSet(
    commit: Boolean = false,
    default: Set<String> = setOf(),
): EasyPrefsProperty<Set<String>> = EasyStringSetProperty(commit, default)

/**
 * Nullable [Set] of [String] easy prefs delegate. To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun nullableStringSet(
    commit: Boolean = false,
    default: Set<String>? = null,
): EasyPrefsProperty<Set<String>?> = EasyNullableStringSetProperty(commit, default)

/**
 * Custom type [T] easy prefs delegate. Requires [TypeAdapter] implementation of type [T].
 * To use this delegate the parent class needs to implement [EasyPrefs] or [EasyPrefsFlow] abstraction.
 */
fun <T> custom(
    adapter: TypeAdapter<T>,
    commit: Boolean = false,
): EasyPrefsProperty<T> = EasyCustomTypeProperty(commit, adapter)
