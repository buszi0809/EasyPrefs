package com.kwdev.easyprefs

import android.content.SharedPreferences

class Preferences(
    sharedPreferences: SharedPreferences,
    byteArrayAdapter: TypeAdapter<ByteArray?>,
) : EasyPrefsFlow(sharedPreferences) {
    var string by string(commit = true, default = "Some value")
    var byteArray by custom(adapter = byteArrayAdapter, commit = true)

    val stringFlow by stringFlow(key = ::string)
    val byteArrayFlow by customFlow(key = ::byteArray, adapter = byteArrayAdapter)
}
