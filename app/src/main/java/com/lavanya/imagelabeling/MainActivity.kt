package com.lavanya.imagelabeling

import ai.fritz.core.Fritz
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val API_KEY = "dcf6227d8ecf4968b4e1a1b5fc1c483b"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fritz.configure(this, API_KEY)

    }
}
