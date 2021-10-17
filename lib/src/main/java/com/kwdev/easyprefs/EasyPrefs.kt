package com.kwdev.easyprefs

import android.content.SharedPreferences
import kotlin.reflect.KProperty

/**
 * Abstraction with basic functionality required to use easy prefs delegates.
 */
abstract class EasyPrefs(
    val prefs: SharedPreferences,
) {
    fun getKeyFor(property: KProperty<*>): String = getKeyFor(this, property)
}
