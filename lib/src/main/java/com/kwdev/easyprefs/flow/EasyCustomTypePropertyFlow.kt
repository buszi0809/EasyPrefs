package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.TypeAdapter
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

internal class EasyCustomTypePropertyFlow<T>(
    private val key: KProperty<T>,
    private val typeAdapter: TypeAdapter<T>,
    private val default: T?,
) : EasyPropertyFlow<T> {

    private val stringDefault by lazy { default?.let(typeAdapter::toString) }

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<T> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getString(it, stringDefault)
            }
            .onStart {
                emit(thisRef.prefs.getString(getKeyFor(thisRef, key), stringDefault))
            }
            .map(typeAdapter::fromString)
}
