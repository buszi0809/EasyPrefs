package com.kwdev.easyprefs.rx

import android.os.Build
import androidx.annotation.RequiresApi
import io.reactivex.rxjava3.core.Observable
import java.util.*

class Nullable<T : Any?> internal constructor(val value: T)

@RequiresApi(Build.VERSION_CODES.N)
fun <T : Any> Observable<Nullable<T?>>.mapOptional(): Observable<T> =
    mapOptional { nullable ->
        Optional.ofNullable(nullable.value)
    }
