package com.example.classicponggame

import android.content.Context
import android.graphics.*
import android.view.View
import kotlin.math.max
import kotlin.math.min

class Game {
    companion object {
        lateinit var ball1: Ball
    }
}

class playerbot(context: Context,) : View(context) {

    val displayMetrics = resources.displayMetrics
    val screenHeight = displayMetrics.heightPixels

    var speedX = 0f
    var speedY = 0f
     var AI_SPEED = 0.09f


    // Create a new Paint object for drawing the player
    val paint = Paint()


    // Variables for the player position and size
    var playerBotX = 100f
    var playerBotY = -300f
    val playerBotWidth = 300f
    val playerBotHeight = 70f
    val playerHitbox = RectF()


    // Position player at bottom of screen
    fun PlayerBotPosition() {
        playerBotY = screenHeight - playerBotHeight
    }


    //AI following the ball
        fun updateAIPlayer() {
        playerBotY = screenHeight - playerBotHeight

        //Calculate the difference in position between the ball and the AI player's paddle
        val deltaX = Game.ball1.ballX - playerBotX - playerBotWidth / 2
        playerBotX += deltaX * AI_SPEED
        }


    fun Draw(canvas: Canvas) {

        // Update player hitbox
        playerHitbox.left = playerBotX
        playerHitbox.top = playerBotY
        playerHitbox.right = playerBotX + playerBotWidth
        playerHitbox.bottom = playerBotY + playerBotHeight

        // Draw the player
        paint.color = Color.RED
        canvas.drawRect(playerBotX, playerBotY, playerBotX + playerBotWidth, playerBotY + playerBotHeight, paint)
    }

    fun checkBounds(bounds : Rect){
        if (playerBotX-75 < bounds.left){
            this.speedX *=-1
        }
        if (playerBotX+75 > bounds.right){
            this.speedX*= -1
        }
        if (playerBotY-75 < bounds.top){
            this.speedY *= -1
        }
        if (playerBotY+75 > bounds.bottom){
            this.speedY *= -1
        }
    }

}