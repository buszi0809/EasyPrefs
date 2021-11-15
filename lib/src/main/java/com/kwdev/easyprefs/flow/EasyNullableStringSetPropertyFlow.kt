package com.kwdev.easyprefs.flow

import android.os.Build
import androidx.annotation.RequiresApi
import com.kwdev.easyprefs.EasyPrefsFlow
import com.kwdev.easyprefs.getKeyFor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.reflect.KProperty

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class EasyNullableStringSetPropertyFlow(
    private val key: KProperty<Set<String>?>,
    private val default: Set<String>?,
) : EasyPropertyFlow<Set<String>?> {

    override fun getValue(thisRef: EasyPrefsFlow, property: KProperty<*>): Flow<Set<String>?> =
        thisRef.propertyFlow(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getStringSet(it, default)
            }
            .onStart {
                emit(thisRef.prefs.getStringSet(getKeyFor(thisRef, key), default))
            }
}
