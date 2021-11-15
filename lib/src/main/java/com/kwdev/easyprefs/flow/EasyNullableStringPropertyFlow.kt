package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

class EasyNullableStringPropertyFlow(
    private val key: KProperty<String?>,
    private val default: String?,
) : EasyPropertyFlow<String?> {

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<String?> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getString(it, default)
            }
            .onStart {
                emit(thisRef.prefs.getString(getKeyFor(thisRef, key), default))
            }
}
