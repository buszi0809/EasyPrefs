package com.kwdev.easyprefs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        val preferences = Preferences(getPreferences(Context.MODE_PRIVATE))

        textView.text = preferences.string
        var count = preferences.string.substringAfter('+').toIntOrNull() ?: 0
        button.setOnClickListener {
            count++
            preferences.string = "Something new +$count"
            textView.text = preferences.string
        }

        preferences.stringFlow.onEach {
            Toast.makeText(this, it.orEmpty(), Toast.LENGTH_LONG).show()
        }.launchIn(lifecycleScope)
    }
}

class Preferences(
    sharedPreferences: SharedPreferences,
) : EasyPrefs(sharedPreferences) {
    var string by string(commit = true, default = "Some value")
    var charArray by custom(adapter = CharArrayAdapter(), commit = true)
    var byteArray by custom(adapter = ByteArrayAdapter(), commit = true)

    val stringFlow by stringFlow(key = ::string)
    val charArrayFlow by customFlow(key = ::charArray, adapter = CharArrayAdapter())

    fun clear() {
        prefs.edit(commit = true) {
            prefs.all.keys.forEach(::remove)
        }
    }
}

class CharArrayAdapter : TypeAdapter<CharArray?> {
    override fun toString(value: CharArray?): String? =
        value?.let(::String)

    override fun fromString(value: String?): CharArray? =
        value?.toCharArray()
}

class ByteArrayAdapter : TypeAdapter<ByteArray?> {
    override fun toString(value: ByteArray?): String? =
        value?.let { Base64.encodeToString(it, Base64.NO_WRAP) }

    override fun fromString(value: String?): ByteArray? =
        value?.let { Base64.decode(it, Base64.NO_WRAP) }
}
