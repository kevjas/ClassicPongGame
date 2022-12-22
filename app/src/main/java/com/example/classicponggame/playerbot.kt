package com.example.classicponggame

import android.content.Context
import android.graphics.*
import android.view.View
import kotlin.math.max
import kotlin.math.min


class playerbot(context: Context,) : View(context) {
    //he
    var speedX = 0f
    var speedY = 0f
     var AI_SPEED = 0.05f
    var ballX = 1350f


    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels



    // Create a new Paint object for drawing the player
    val paint = Paint()

    // Variables for the player position and size
    var playerBotX = 500f
    var playerBotY = -300f
    val playerBotWidth = 300f
    val playerBotHeight = 70f
    val playerHitbox = RectF()


    fun startGame() {
        playerBotY = screenHeight - playerBotHeight // Position player at bottom of screen
    }


        fun updateAIPlayer() {
            val deltaX = ballX - playerBotX - playerBotWidth / 2
            playerBotX += deltaX * AI_SPEED

            if (playerBotX < 0 || playerBotX + playerBotWidth > screenWidth) {
                // Reverse player's direction
                AI_SPEED = -AI_SPEED
            }
        }

    fun Draw(canvas: Canvas) {

        // Update player hitbox
        playerHitbox.left = playerBotX
        playerHitbox.top = playerBotY
        playerHitbox.right = playerBotX + playerBotWidth
        playerHitbox.bottom = playerBotY + playerBotHeight

        // Set the size of the player using the paint's stroke width


        // Draw the player as a horizontal line on the canvas using the drawLine method
        paint.color = Color.BLUE
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