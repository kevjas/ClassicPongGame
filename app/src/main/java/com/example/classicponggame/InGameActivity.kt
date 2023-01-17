package com.example.classicponggame

import android.graphics.Point
import com.example.classicponggame.GameActivity.GameMode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout

class InGameActivity : AppCompatActivity() {
    private lateinit var Player1view: GameView
    private lateinit var Player2view: GameView2

    companion object {
        const val EXTRA_GAME_MODE = "game_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameMode = intent.getSerializableExtra(EXTRA_GAME_MODE) as GameMode
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        // Set up the layout based on the game mode
        when (gameMode) {
            GameMode.SINGLE_PLAYER -> {
                setContentView(R.layout.activity_in_game)
                Player1view = GameView(this)
                findViewById<ConstraintLayout>(R.id.player1_constraint_layout).addView(Player1view)
                Player1view.start()

            }
            GameMode.MULTI_PLAYER -> {
                setContentView(R.layout.activity_in_game)
                Player2view = GameView2(this)
                findViewById<ConstraintLayout>(R.id.player1_constraint_layout).addView(Player2view)
                Player2view.start()
            }
        }

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        //hide bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN )

    }
}