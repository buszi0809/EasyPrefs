package com.kwdev.easyprefs

import android.util.Base64

class ByteArrayAdapter : TypeAdapter<ByteArray?> {
    override fun toString(value: ByteArray?): String? =
        value?.let { Base64.encodeToString(it, Base64.NO_WRAP) }

    override fun fromString(value: String?): ByteArray? =
        value?.let { Base64.decode(it, Base64.NO_WRAP) }
}
