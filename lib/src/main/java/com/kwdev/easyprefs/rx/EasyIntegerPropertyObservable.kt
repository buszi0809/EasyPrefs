package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

internal class EasyIntegerPropertyObservable(
    private val key: KProperty<Int>,
    private val default: Int,
) : EasyPropertyObservable<Int> {

    override fun getValue(thisRef: EasyPrefsRx, property: KProperty<*>): Observable<Int> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getInt(it, default)
            }
            .startWithItem(thisRef.prefs.getInt(getKeyFor(thisRef, key), default))
}
