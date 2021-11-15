package com.kwdev.easyprefs

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.kwdev.easyprefs.properties.*
import kotlin.reflect.KProperty

/**
 * Abstraction with basic functionality required to use easy prefs delegates.
 */
abstract class EasyPrefs(
    val prefs: SharedPreferences,
) {
    /**
     * Get EasyPrefs property key for given [KProperty]. The property needs to be declared within the same class.
     */
    fun getKeyFor(property: KProperty<*>): String = getKeyFor(this, property)

    /**
     * [Boolean] easy prefs delegate.
     */
    protected fun boolean(
        commit: Boolean = false,
        default: Boolean = false,
    ): EasyPrefsProperty<Boolean> = EasyBooleanProperty(commit, default)

    /**
     * [Int] easy prefs delegate.
     */
    protected fun integer(
        commit: Boolean = false,
        default: Int = 0,
    ): EasyPrefsProperty<Int> = EasyIntegerProperty(commit, default)

    /**
     * [Long] easy prefs delegate.
     */
    protected fun long(
        commit: Boolean = false,
        default: Long = 0L,
    ): EasyPrefsProperty<Long> = EasyLongProperty(commit, default)

    /**
     * [String] easy prefs delegate.
     */
    protected fun string(
        commit: Boolean = false,
        default: String = "",
    ): EasyPrefsProperty<String> = EasyStringProperty(commit, default)

    /**
     * Nullable [String] easy prefs delegate.
     */
    protected fun nullableString(
        commit: Boolean = false,
        default: String? = null,
    ): EasyPrefsProperty<String?> = EasyNullableStringProperty(commit, default)

    /**
     * [Set] of [String] easy prefs delegate.
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun stringSet(
        commit: Boolean = false,
        default: Set<String> = setOf(),
    ): EasyPrefsProperty<Set<String>> = EasyStringSetProperty(commit, default)

    /**
     * Nullable [Set] of [String] easy prefs delegate.
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun nullableStringSet(
        commit: Boolean = false,
        default: Set<String>? = null,
    ): EasyPrefsProperty<Set<String>?> = EasyNullableStringSetProperty(commit, default)

    /**
     * Custom type [T] easy prefs delegate. Requires [TypeAdapter] implementation of type [T].
     */
    protected fun <T> custom(
        adapter: TypeAdapter<T>,
        commit: Boolean = false,
        default: T? = null,
    ): EasyPrefsProperty<T> = EasyCustomTypeProperty(commit, adapter, default)
}
