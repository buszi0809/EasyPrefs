package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyIntegerPropertyFlow(
    private val key: KProperty<Int>,
    private val default: Int,
) : EasyPropertyFlow<Int> {

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<Int> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getInt(it, default)
            }
            .onStart {
                emit(thisRef.prefs.getInt(getKeyFor(thisRef, key), default))
            }
}
