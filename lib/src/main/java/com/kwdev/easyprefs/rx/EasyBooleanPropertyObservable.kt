package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

internal class EasyBooleanPropertyObservable(
    private val key: KProperty<Boolean>,
    private val default: Boolean,
) : EasyPropertyObservable<Boolean> {

    override fun getValue(thisRef: EasyPrefsRx, property: KProperty<*>): Observable<Boolean> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getBoolean(it, default)
            }
            .startWithItem(thisRef.prefs.getBoolean(getKeyFor(thisRef, key), default))
}
