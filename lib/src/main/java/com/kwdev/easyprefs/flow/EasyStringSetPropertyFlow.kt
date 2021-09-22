package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyStringSetPropertyFlow(
    private val key: KProperty<Set<String>>,
    private val default: Set<String>,
) : EasyPropertyFlow<Set<String>> {

    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Flow<Set<String>> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getStringSet(it, null) ?: default
            }
            .onStart {
                emit(
                    thisRef.prefs.getStringSet(
                        getKeyFor(thisRef, key),
                        null,
                    ) ?: default
                )
            }
}
