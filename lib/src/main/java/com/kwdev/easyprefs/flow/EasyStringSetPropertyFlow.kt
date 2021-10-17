package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyStringSetPropertyFlow(
    private val key: KProperty<Set<String>?>,
) : EasyPropertyFlow<Set<String>?> {

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<Set<String>?> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getStringSet(it, null)
            }
            .onStart {
                emit(thisRef.prefs.getStringSet(getKeyFor(thisRef, key), null))
            }
}
