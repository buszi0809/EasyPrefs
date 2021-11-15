package com.kwdev.easyprefs

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.kwdev.easyprefs.rx.*
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.reflect.KProperty

abstract class EasyPrefsRx(
    prefs: SharedPreferences
) : EasyPrefs(prefs) {

    private val keysSubject = PublishSubject.create<String>()

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        key?.let(keysSubject::onNext)
    }

    init {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    internal fun propertyObservable(key: String) = keysSubject.filter(key::equals)

    /**
     * RxJava3 Observable delegate of [Boolean] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    protected fun integerObservable(
        key: KProperty<Int>,
        default: Int = 0,
    ): EasyPropertyObservable<Int> = EasyIntegerPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of [Long] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    protected fun longObservable(
        key: KProperty<Long>,
        default: Long = 0L,
    ): EasyPropertyObservable<Long> = EasyLongPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of [Boolean] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    protected fun booleanObservable(
        key: KProperty<Boolean>,
        default: Boolean = false,
    ): EasyPropertyObservable<Boolean> = EasyBooleanPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of [Set] of [String] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun stringSetObservable(
        key: KProperty<Set<String>>,
        default: Set<String>,
    ): EasyPropertyObservable<Set<String>> = EasyStringSetPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of nullable [Set] of [String] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun nullableStringSetObservable(
        key: KProperty<Set<String>?>,
        default: Set<String>?,
    ): EasyPropertyObservable<Nullable<Set<String>?>> = EasyNullableStringSetPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of [String] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    protected fun stringObservable(
        key: KProperty<String>,
        default: String,
    ): EasyPropertyObservable<String> = EasyStringPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of nullable [String] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     */
    protected fun nullableStringObservable(
        key: KProperty<String?>,
        default: String? = null,
    ): EasyPropertyObservable<Nullable<String?>> = EasyNullableStringPropertyObservable(key, default)

    /**
     * RxJava3 Observable delegate of custom type [T] easy prefs property.
     * The [key] is property that you want to observe with use of this Observable.
     * The [key] property needs to be declared within the same class as this Observable delegate.
     * Requires [TypeAdapter] implementation of type [T].
     */
    protected fun <T> customObservable(
        key: KProperty<T>,
        adapter: TypeAdapter<T>,
        default: T? = null,
    ): EasyPropertyObservable<Nullable<T>> = EasyCustomTypePropertyObservable(key, adapter, default)
}
