package com.example.classicponggame

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.view.View

class Ball(context: Context,) : View(context) {

    var ballX = 725f
     var ballY = 1350f
     val ballRadius = 50f
    var speedX = 20f
    var speedY = 20f

    private var playerScore = 0
    private var aiScore = 0
    private var playerHighScore = 0

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_FILE = "com.example.pingpong.prefs"

    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    val ballHitbox = RectF()

    val paint = Paint()

    fun update(){

        //ball moving
        ballX += speedX
        ballY += speedY

        sharedPreferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        playerHighScore = sharedPreferences.getInt("highScore", 0)


        // Check if ball has collided with left or right wall
        if (ballX - ballRadius < 0 || ballX + ballRadius > screenWidth) {
            speedX = -speedX
        }

        // Check if ball has collided with top or bottom wall
        if (ballY - ballRadius < 0 || ballY + ballRadius > screenHeight) {
            speedY = -speedY
        }

    }


    override fun draw(canvas: Canvas?){
        super.draw(canvas)
        canvas ?: return

        // Update ball hitbox
        ballHitbox.left = ballX - ballRadius
        ballHitbox.top = ballY - ballRadius
        ballHitbox.right = ballX + ballRadius
        ballHitbox.bottom = ballY + ballRadius

        // Draw ball
        paint.color = Color.WHITE
        canvas.drawCircle(ballX, ballY, ballRadius, paint)


        // Draw scores and high score
        paint.color = Color.WHITE
        paint.textSize = 66f
        val playerScoreText = "Player Score: $playerScore"
        val aiScoreText = "AI Score: $aiScore"
        val playerHighScoreText = "High Score: $playerHighScore"
        canvas.drawText(playerScoreText, 30f, (screenHeight/2).toFloat(), paint)
        canvas.drawText(aiScoreText, 1080f, (screenHeight/2).toFloat(), paint)
        canvas.drawText(playerHighScoreText, (screenWidth/2).toFloat()-250f, (screenHeight/2).toFloat()+200f, paint)
    }

    //draw for player2 mode
     fun drawP2(canvas: Canvas?){
        super.draw(canvas)
        canvas ?: return

        // Update ball hitbox
        ballHitbox.left = ballX - ballRadius
        ballHitbox.top = ballY - ballRadius
        ballHitbox.right = ballX + ballRadius
        ballHitbox.bottom = ballY + ballRadius

        // Draw ball
        paint.color = Color.WHITE
        canvas.drawCircle(ballX, ballY, ballRadius, paint)


        // Draw scores and high score
        paint.color = Color.WHITE
        paint.textSize = 66f
        val playerScoreText = "Player score: $playerScore"
        val aiScoreText = "Player2 score: $aiScore"
         canvas.drawText(playerScoreText, 30f, 150f, paint)
         canvas.drawText(aiScoreText, 30f, 2600f, paint)
    }


    fun updateScore() {
        if (ballY - ballRadius < 0) {
            // Ball passed through player's side of the screen
            aiScore++
            ballX = (screenWidth / 2).toFloat()
            ballY = (screenHeight / 2).toFloat()
            speedX = -speedX

        } else if (ballY + ballRadius > screenHeight) {
            // Ball passed through AI's side of the screen
            playerScore++
            ballX = (screenWidth / 2).toFloat()
            ballY = (screenHeight / 2).toFloat()
            speedX = -speedX
            if (playerScore > playerHighScore) {
                playerHighScore = playerScore
                sharedPreferences.edit().putInt("highScore", playerHighScore).apply()
            }
        }
        }

}

