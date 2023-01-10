package com.example.classicponggame

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.view.View

class Ball(context: Context,) : View(context) {
//he
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

    fun checkBounds(bounds : Rect){
        if (ballX-75 < bounds.left){
            this.speedX *=-1
        }
        if (ballX+75 > bounds.right){
            this.speedX*= -1
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
        paint.color = Color.RED
        canvas.drawCircle(ballX, ballY, ballRadius, paint)


        // Draw scores and high score
        paint.color = Color.BLACK
        paint.textSize = 66f
        val playerScoreText = "Player score: $playerScore"
        val aiScoreText = "AI score: $aiScore"
        val playerHighScoreText = "High score: $playerHighScore"
        canvas.drawText(playerScoreText, 50f, 50f, paint)
        canvas.drawText(aiScoreText, 50f, 100f, paint)
        canvas.drawText(playerHighScoreText, 50f, 150f, paint)
    }

     fun drawP2(canvas: Canvas?){
        super.draw(canvas)
        canvas ?: return
        // Update ball hitbox
        ballHitbox.left = ballX - ballRadius
        ballHitbox.top = ballY - ballRadius
        ballHitbox.right = ballX + ballRadius
        ballHitbox.bottom = ballY + ballRadius

        // Draw ball
        paint.color = Color.RED
        canvas.drawCircle(ballX, ballY, ballRadius, paint)


        // Draw scores and high score
        paint.color = Color.BLACK
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

