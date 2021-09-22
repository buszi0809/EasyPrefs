package com.kwdev.easyprefs

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter

abstract class EasyPrefs(
    val prefs: SharedPreferences,
    bufferCapacity: Int = DEFAULT_BUFFER_CAPACITY,
) {
    private val _keysFlow = MutableSharedFlow<String>(extraBufferCapacity = bufferCapacity)

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        key?.let(_keysFlow::tryEmit)
    }

    init {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    internal fun propertyFlow(key: String): Flow<String> = _keysFlow.filter { it == key }

    companion object {
        const val DEFAULT_BUFFER_CAPACITY = 64
    }
}
