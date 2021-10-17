package com.kwdev.easyprefs

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        val preferences = Preferences(getPreferences(Context.MODE_PRIVATE), ByteArrayAdapter())

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
