package com.kwdev.easyprefs.properties

import androidx.core.content.edit
import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlin.reflect.KProperty

internal class EasyStringProperty(
    private val commit: Boolean,
    private val default: String,
) : EasyPrefsProperty<String> {
    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): String =
        thisRef.prefs.getString(getKeyFor(thisRef, property), null) ?: default

    override fun setValue(thisRef: EasyPrefs, property: KProperty<*>, value: String) {
        thisRef.prefs.edit(commit) {
            putString(getKeyFor(thisRef, property), value)
        }
    }
}
