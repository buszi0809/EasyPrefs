package com.kwdev.easyprefs

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.kwdev.easyprefs.flow.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlin.reflect.KProperty

/**
 * Abstraction with basic functionality required to use easy prefs delegates, and extended functionality to use flow delegates.
 */
abstract class EasyPrefsFlow(
    prefs: SharedPreferences,
    extraBufferCapacity: Int = DEFAULT_BUFFER_CAPACITY,
    replay: Int = DEFAULT_REPLAY,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
) : EasyPrefs(prefs) {

    private val _keysFlow = MutableSharedFlow<String>(
        extraBufferCapacity = extraBufferCapacity,
        replay = replay,
        onBufferOverflow = onBufferOverflow,
    )

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        key?.let(_keysFlow::tryEmit)
    }

    init {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    internal fun propertyFlow(key: String): Flow<String> = _keysFlow.filter(key::equals)

    /**
     * Kotlin flow delegate of [Boolean] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    protected fun integerFlow(
        key: KProperty<Int>,
        default: Int = 0,
    ): EasyPropertyFlow<Int> = EasyIntegerPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of [Long] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    protected fun longFlow(
        key: KProperty<Long>,
        default: Long = 0L,
    ): EasyPropertyFlow<Long> = EasyLongPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of [Boolean] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    protected fun booleanFlow(
        key: KProperty<Boolean>,
        default: Boolean = false,
    ): EasyPropertyFlow<Boolean> = EasyBooleanPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of [Set] of [String] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun stringSetFlow(
        key: KProperty<Set<String>>,
        default: Set<String>,
    ): EasyPropertyFlow<Set<String>> = EasyStringSetPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of nullable [Set] of [String] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun nullableStringSetFlow(
        key: KProperty<Set<String>?>,
        default: Set<String>? = null,
    ): EasyPropertyFlow<Set<String>?> = EasyNullableStringSetPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of [String] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    protected fun stringFlow(
        key: KProperty<String>,
        default: String,
    ): EasyPropertyFlow<String> = EasyStringPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of nullable [String] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     */
    protected fun nullableStringFlow(
        key: KProperty<String?>,
        default: String? = null,
    ): EasyPropertyFlow<String?> = EasyNullableStringPropertyFlow(key, default)

    /**
     * Kotlin flow delegate of custom type [T] easy prefs property.
     * The [key] is property that you want to observe with use of this flow.
     * The [key] property needs to be declared within the same class as this flow delegate.
     * Requires [TypeAdapter] implementation of type [T].
     */
    protected fun <T> customFlow(
        key: KProperty<T>,
        adapter: TypeAdapter<T>,
        default: T? = null,
    ): EasyPropertyFlow<T> = EasyCustomTypePropertyFlow(key, adapter, default)

    companion object {
        const val DEFAULT_BUFFER_CAPACITY = 64
        const val DEFAULT_REPLAY = 0
    }
}
