package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

class EasyNullableStringPropertyObservable(
    private val key: KProperty<String?>,
    private val default: String?,
) : EasyPropertyObservable<Nullable<String?>> {

    override fun getValue(
        thisRef: EasyPrefsRx,
        property: KProperty<*>
    ): Observable<Nullable<String?>> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                Nullable(thisRef.prefs.getString(it, default))
            }
            .startWithItem(
                Nullable(thisRef.prefs.getString(getKeyFor(thisRef, key), default))
            )
}
