package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefs
import com.kwdev.easyprefs.TypeAdapter
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyCustomTypePropertyFlow<T>(
    private val key: KProperty<T>,
    private val typeAdapter: TypeAdapter<T>,
) : EasyPropertyFlow<T> {

    override fun getValue(thisRef: EasyPrefs, property: KProperty<*>): Flow<T> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getString(it, null)
            }
            .onStart {
                emit(thisRef.prefs.getString(getKeyFor(thisRef, key), null))
            }
            .map(typeAdapter::fromString)
}
