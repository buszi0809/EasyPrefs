package com.kwdev.easyprefs.properties

import androidx.core.content.edit
import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlin.reflect.KProperty

internal class EasyBooleanProperty(
    private val commit: Boolean,
    private val default: Boolean,
) : EasyPrefsProperty<Boolean> {
    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Boolean =
        thisRef.prefs.getBoolean(getKeyFor(thisRef, property), default)

    override fun setValue(thisRef: EasyPrefs, property: KProperty<*>, value: Boolean) {
        thisRef.prefs.edit(commit) {
            putBoolean(getKeyFor(thisRef, property), value)
        }
    }
}
