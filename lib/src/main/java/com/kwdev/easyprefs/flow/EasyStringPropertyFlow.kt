package com.kwdev.easyprefs.flow

import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.*
import kotlin.reflect.KProperty

internal class EasyStringPropertyFlow(
    private val key: KProperty<String?>,
) : EasyPropertyFlow<String?> {

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<String?> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getString(it, null)
            }
            .onStart {
                emit(thisRef.prefs.getString(getKeyFor(thisRef, key), null))
            }
}
