package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyLongPropertyFlow(
    private val key: KProperty<Long>,
    private val default: Long,
) : EasyPropertyFlow<Long> {

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<Long> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getLong(it, default)
            }
            .onStart {
                emit(thisRef.prefs.getLong(getKeyFor(thisRef, key), default))
            }
}
