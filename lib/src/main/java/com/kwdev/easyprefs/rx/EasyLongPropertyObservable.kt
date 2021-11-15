package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

internal class EasyLongPropertyObservable(
    private val key: KProperty<Long>,
    private val default: Long,
) : EasyPropertyObservable<Long> {

    override fun getValue(thisRef: EasyPrefsRx, property: KProperty<*>): Observable<Long> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getLong(it, default)
            }
            .startWithItem(thisRef.prefs.getLong(getKeyFor(thisRef, key), default))
}
