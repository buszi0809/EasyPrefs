package com.kwdev.easyprefs.rx

import com.kwdev.easyprefs.EasyPrefsRx
import io.reactivex.rxjava3.core.Observable
import kotlin.properties.ReadOnlyProperty

sealed interface EasyPropertyObservable<T> : ReadOnlyProperty<EasyPrefsRx, Observable<T>>
