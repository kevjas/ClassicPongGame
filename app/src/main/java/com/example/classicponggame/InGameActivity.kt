package com.example.classicponggame

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class InGameActivity : AppCompatActivity() {
    private lateinit var pongView: GameView

    companion object {
        const val EXTRA_GAME_MODE = "game_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameMode = intent.getSerializableExtra(EXTRA_GAME_MODE) as GameActivity.GameMode
        val mainActivity = GameActivity()
        mainActivity.gameMode = gameMode

        val display = windowManager.defaultDisplay


        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        // Load the resolution into a Point object
        val size = Point()
        display.getSize(size)



//he



        // Initialize pongView and set it as the view
        pongView = GameView(this, screenWidth, screenHeight,)
        setContentView(pongView)


        //hide bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN )

    }
}