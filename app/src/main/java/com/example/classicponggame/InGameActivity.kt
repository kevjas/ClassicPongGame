package com.example.classicponggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameView(this))
    }
}