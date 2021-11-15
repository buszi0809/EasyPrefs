package com.kwdev.easyprefs

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kwdev.easyprefs.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFlow(binding)
        setupRx(binding)
    }

    private fun setupFlow(binding: ActivityMainBinding) {
        val preferences = PreferencesFlow(
            getSharedPreferences("Preferences flow", Context.MODE_PRIVATE),
            ByteArrayAdapter(),
        )

        preferences.stringFlow
            .onEach { value ->
                binding.textViewFlow.text = value
                Toast.makeText(this, "Data from flow: $value", Toast.LENGTH_SHORT).show()
            }
            .launchIn(lifecycleScope)

        binding.buttonFlow.setOnClickListener {
            val counter = preferences.string.toInt() + 1
            preferences.string = counter.toString()
        }
    }

    private fun setupRx(binding: ActivityMainBinding) {
        val preferences = PreferencesRx(
            getSharedPreferences("Preferences rx", Context.MODE_PRIVATE),
            ByteArrayAdapter(),
        )

        preferences.stringObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { value ->
                binding.textViewRx.text = value
                Toast.makeText(this, "Data from observable: $value", Toast.LENGTH_SHORT).show()
            }

        binding.buttonRx.setOnClickListener {
            val counter = preferences.string.toInt() + 1
            preferences.string = counter.toString()
        }
    }
}
