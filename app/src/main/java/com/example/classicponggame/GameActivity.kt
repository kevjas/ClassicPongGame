package com.example.classicponggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class GameActivity : AppCompatActivity() {

    enum class GameMode {
        SINGLE_PLAYER,
        MULTI_PLAYER
    }
    var gameMode = GameMode.SINGLE_PLAYER

    private lateinit var ball1 : Ball
    private lateinit var player1 : player1
    private lateinit var PlayerBot : playerbot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        //hide bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )



        // Set up spinner
        val spinner = findViewById<Spinner>(R.id.game_mode_spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.game_modes, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set up play button
        val playButton = findViewById<Button>(R.id.Button)
        playButton.setOnClickListener {

            val selectedGameMode = spinner.selectedItem.toString()
            if (selectedGameMode == "Player 1") {
                startInGameActivity(GameMode.SINGLE_PLAYER)
            } else if (selectedGameMode == "Player 2") {
                startInGameActivity(GameMode.MULTI_PLAYER)
            }
        }

            // Start InGameActivity
    }
    private fun startInGameActivity(gameMode: GameMode) {
        val intent = Intent(this, InGameActivity::class.java)
        intent.putExtra(InGameActivity.EXTRA_GAME_MODE, gameMode)
        startActivity(intent)
    }
}

