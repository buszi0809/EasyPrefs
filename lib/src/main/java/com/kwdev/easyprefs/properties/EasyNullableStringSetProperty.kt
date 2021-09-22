package com.kwdev.easyprefs.properties

import androidx.core.content.edit
import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlin.reflect.KProperty

internal class EasyNullableStringSetProperty(
    private val commit: Boolean,
    private val default: Set<String>?,
): EasyPrefsProperty<Set<String>?> {
    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Set<String>? =
        thisRef.prefs.getStringSet(getKeyFor(thisRef, property), default)

    override fun setValue(thisRef: EasyPrefs, property: KProperty<*>, value: Set<String>?) {
        thisRef.prefs.edit(commit) {
            putStringSet(getKeyFor(thisRef, property), value)
        }
    }
}
