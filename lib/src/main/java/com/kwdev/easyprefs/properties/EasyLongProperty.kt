package com.kwdev.easyprefs.properties

import androidx.core.content.edit
import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlin.reflect.KProperty

internal class EasyLongProperty(
    private val commit: Boolean,
    private val default: Long,
) : EasyPrefsProperty<Long> {
    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Long =
        thisRef.prefs.getLong(getKeyFor(thisRef, property), default)

    override fun setValue(thisRef: EasyPrefs, property: KProperty<*>, value: Long) {
        thisRef.prefs.edit(commit) {
            putLong(getKeyFor(thisRef, property), value)
        }
    }
}
