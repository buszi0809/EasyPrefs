package com.kwdev.easyprefs.rx

import android.os.Build
import androidx.annotation.RequiresApi
import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class EasyNullableStringSetPropertyObservable(
    private val key: KProperty<Set<String>?>,
    private val default: Set<String>?,
) : EasyPropertyObservable<Nullable<Set<String>?>> {

    override fun getValue(
        thisRef: EasyPrefsRx,
        property: KProperty<*>
    ): Observable<Nullable<Set<String>?>> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                Nullable<Set<String>?>(thisRef.prefs.getStringSet(it, default))
            }
            .startWithItem(
                Nullable(thisRef.prefs.getStringSet(getKeyFor(thisRef, key), default))
            )
}
