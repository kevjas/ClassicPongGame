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
     var playerX2 = 0f
     var playerY2 = 0f
     val playerWidth2 = 300f
     val playerHeight2 = 70f

     val playerHitbox = RectF()
    fun updateP2(){
        playerY2+= speedX
    }


    // Position player at bottom of screen
    fun P2Position() {
        playerY2 = screenHeight - playerHeight2
    }


    fun DrawPlayer2(canvas: Canvas) {

        // Update player hitbox
        playerHitbox.left = playerX2
        playerHitbox.top = playerY2
        playerHitbox.right = playerX2 + playerWidth2
        playerHitbox.bottom = playerY2 + playerHeight2

        // Draw the player
        paint.color = Color.RED
        canvas.drawRect(playerX2, playerY2, playerX2 + playerWidth2, playerY2 + playerHeight2, paint)
    }

    fun checkBounds(bounds : Rect){
        if (playerX2-75 < bounds.left){
            this.speedX *=-1
        }
        if (playerX2+75 > bounds.right){
            this.speedX*= -1
        }
        if (playerY2-75 < bounds.top){
            this.speedY *= -1
        }
        if (playerY2+75 > bounds.bottom){
            this.speedY *= -1
        }
    }
}
