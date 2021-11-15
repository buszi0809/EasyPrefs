package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.TypeAdapter
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

internal class EasyCustomTypePropertyObservable<T>(
    private val key: KProperty<T>,
    private val typeAdapter: TypeAdapter<T>,
    private val default: T?,
) : EasyPropertyObservable<Nullable<T>> {

    private val stringDefault by lazy { default?.let(typeAdapter::toString) }

    override fun getValue(
        thisRef: EasyPrefsRx,
        property: KProperty<*>
    ): Observable<Nullable<T>> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                Nullable(thisRef.prefs.getString(it, stringDefault))
            }
            .startWithItem(
                Nullable(thisRef.prefs.getString(getKeyFor(thisRef, key), stringDefault))
            )
            .map {
                Nullable(typeAdapter.fromString(it.value))
            }
}
