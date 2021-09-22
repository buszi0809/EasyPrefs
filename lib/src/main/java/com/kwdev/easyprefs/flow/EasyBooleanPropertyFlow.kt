package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyBooleanPropertyFlow(
    private val key: KProperty<Boolean>,
    private val default: Boolean,
) : EasyPropertyFlow<Boolean> {

    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Flow<Boolean> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getBoolean(it, default)
            }
            .onStart {
                emit(thisRef.prefs.getBoolean(getKeyFor(thisRef, key), default))
            }
}
