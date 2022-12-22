package com.example.classicponggame

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager

class InGameActivity : AppCompatActivity() {
    private lateinit var pongView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display = windowManager.defaultDisplay

        // Load the resolution into a Point object
        val size = Point()
        display.getSize(size)

        val screenWidth = size.x
        val screenHeight = size.y


//he



        // Initialize pongView and set it as the view
        pongView = GameView(this, size.x, size.y)
        setContentView(pongView)


        //hide bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN )

    }
}