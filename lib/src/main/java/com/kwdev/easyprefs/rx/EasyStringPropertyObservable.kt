package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

internal class EasyStringPropertyObservable(
    private val key: KProperty<String>,
    private val default: String,
) : EasyPropertyObservable<String> {

    override fun getValue(
        thisRef: EasyPrefsRx,
        property: KProperty<*>
    ): Observable<String> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getString(it, null) ?: default
            }
            .startWithItem(
                thisRef.prefs.getString(getKeyFor(thisRef, key), null) ?: default
            )
}
