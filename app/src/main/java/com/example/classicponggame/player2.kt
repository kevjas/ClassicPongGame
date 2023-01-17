package com.example.classicponggame

import android.content.Context
import android.graphics.*
import android.view.View

class player2(context: Context,) : View(context) {

    val displayMetrics = resources.displayMetrics
    val screenHeight = displayMetrics.heightPixels

    var speedX = 0f
    var speedY = 0f


    // Create a new Paint object for drawing the player
    val paint = Paint()


    // Variables for the player position and size
     var playerX = 0f
     var playerY = 0f
     val playerWidth = 300f
     val playerHeight = 70f

     val playerHitbox = RectF()
    fun updateP2(){
        playerX += speedX
    }


    // Position player at bottom of screen
    fun P2Position() {
        playerY = screenHeight - playerHeight
    }


    fun DrawPlayer2(canvas: Canvas) {

        // Update player hitbox
        playerHitbox.left = playerX
        playerHitbox.top = playerY
        playerHitbox.right = playerX + playerWidth
        playerHitbox.bottom = playerY + playerHeight

        // Draw the player
        paint.color = Color.GREEN
        canvas.drawRect(playerX, playerY, playerX + playerWidth, playerY + playerHeight, paint)
    }

    fun checkBounds(bounds : Rect){
        if (playerX-75 < bounds.left){
            this.speedX *=-1
        }
        if (playerX+75 > bounds.right){
            this.speedX*= -1
        }
        if (playerY-75 < bounds.top){
            this.speedY *= -1
        }
        if (playerY+75 > bounds.bottom){
            this.speedY *= -1
        }
    }
}
