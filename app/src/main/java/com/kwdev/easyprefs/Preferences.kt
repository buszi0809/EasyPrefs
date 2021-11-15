package com.kwdev.easyprefs

import android.content.SharedPreferences

class PreferencesFlow(
    sharedPreferences: SharedPreferences,
    byteArrayAdapter: TypeAdapter<ByteArray?>,
) : EasyPrefsFlow(sharedPreferences) {
    var string by string(commit = true, default = "0")
    var byteArray by custom(adapter = byteArrayAdapter, commit = true)

    val stringFlow by stringFlow(key = ::string, default = "0")
    val byteArrayFlow by customFlow(key = ::byteArray, adapter = byteArrayAdapter)
}

class PreferencesRx(
    sharedPreferences: SharedPreferences,
    byteArrayAdapter: TypeAdapter<ByteArray?>,
) : EasyPrefsRx(sharedPreferences) {
    var string by string(commit = true, default = "0")
    var byteArray by custom(adapter = byteArrayAdapter, commit = true)

    val stringObservable by stringObservable(key = ::string, default = "0")
    val byteArrayObservable by customObservable(key = ::byteArray, adapter = byteArrayAdapter)
}
