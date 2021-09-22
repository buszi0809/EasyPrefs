package com.kwdev.easyprefs

import com.kwdev.easyprefs.properties.EasyIntegerProperty
import com.kwdev.easyprefs.properties.*
import com.kwdev.easyprefs.properties.EasyBooleanProperty
import com.kwdev.easyprefs.properties.EasyNullableStringSetProperty
import com.kwdev.easyprefs.properties.EasyStringProperty

fun boolean(
    commit: Boolean = false,
    default: Boolean = false,
): EasyPrefsProperty<Boolean> = EasyBooleanProperty(commit, default)

fun integer(
    commit: Boolean = false,
    default: Int = 0,
): EasyPrefsProperty<Int> = EasyIntegerProperty(commit, default)

fun long(
    commit: Boolean = false,
    default: Long = 0L,
): EasyPrefsProperty<Long> = EasyLongProperty(commit, default)

fun string(
    commit: Boolean = false,
    default: String = "",
): EasyPrefsProperty<String> = EasyStringProperty(commit, default)

fun nullableString(
    commit: Boolean = false,
    default: String? = null,
): EasyPrefsProperty<String?> = EasyNullableStringProperty(commit, default)

fun stringSet(
    commit: Boolean = false,
    default: Set<String> = setOf(),
): EasyPrefsProperty<Set<String>> = EasyStringSetProperty(commit, default)

fun nullableStringSet(
    commit: Boolean = false,
    default: Set<String>? = null,
): EasyPrefsProperty<Set<String>?> = EasyNullableStringSetProperty(commit, default)

fun <T> custom(
    adapter: TypeAdapter<T>,
    commit: Boolean = false,
): EasyPrefsProperty<T> = EasyCustomTypeProperty(commit, adapter)
