package com.kwdev.easyprefs.rx

import android.os.Build
import androidx.annotation.RequiresApi
import com.kwdev.easyprefs.EasyPrefsRx
import com.kwdev.easyprefs.getKeyFor
import io.reactivex.rxjava3.core.Observable
import kotlin.reflect.KProperty

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
internal class EasyStringSetPropertyObservable(
    private val key: KProperty<Set<String>>,
    private val default: Set<String>,
) : EasyPropertyObservable<Set<String>> {

    override fun getValue(
        thisRef: EasyPrefsRx,
        property: KProperty<*>
    ): Observable<Set<String>> =
        thisRef.propertyObservable(getKeyFor(thisRef, key))
            .map {
                thisRef.prefs.getStringSet(it, null) ?: default
            }
            .startWithItem(
                thisRef.prefs.getStringSet(getKeyFor(thisRef, key), null) ?: default
            )
}
