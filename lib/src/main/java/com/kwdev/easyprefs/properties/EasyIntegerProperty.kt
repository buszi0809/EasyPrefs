package com.kwdev.easyprefs.properties

import androidx.core.content.edit
import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlin.reflect.KProperty

internal class EasyIntegerProperty(
    private val commit: Boolean,
    private val default: Int,
) : EasyPrefsProperty<Int> {
    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Int =
        thisRef.prefs.getInt(getKeyFor(thisRef, property), default)

    override fun setValue(thisRef: EasyPrefs, property: KProperty<*>, value: Int) {
        thisRef.prefs.edit(commit) {
            putInt(getKeyFor(thisRef, property), value)
        }
    }
}
