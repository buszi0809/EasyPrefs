package com.kwdev.easyprefs.properties

import androidx.core.content.edit
import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.TypeAdapter
import com.kwdev.easyprefs.getKeyFor
import kotlin.reflect.KProperty

internal class EasyCustomTypeProperty<T>(
    private val commit: Boolean,
    private val adapter: TypeAdapter<T>,
    private val default: T?,
) : EasyPrefsProperty<T> {

    private val stringDefault by lazy { default?.let(adapter::toString) }

    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): T =
        thisRef.prefs.getString(getKeyFor(thisRef, property), stringDefault)
            .let(adapter::fromString)

    override fun setValue(thisRef: EasyPrefs, property: KProperty<*>, value: T) {
        thisRef.prefs.edit(commit) {
            putString(getKeyFor(thisRef, property), adapter.toString(value))
        }
    }
}
