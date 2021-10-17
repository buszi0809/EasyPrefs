package com.kwdev.easyprefs

import android.content.SharedPreferences
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter

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

    companion object {
        const val DEFAULT_BUFFER_CAPACITY = 64
        const val DEFAULT_REPLAY = 0
    }
}
