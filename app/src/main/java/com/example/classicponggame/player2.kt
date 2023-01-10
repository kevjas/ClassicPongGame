package com.example.classicponggame

import android.content.Context
import android.graphics.*
import android.view.View

class player2(context: Context,) : View(context) {
//he
    var speedX = 0f
    var speedY = 0f


    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

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


    fun startGameP2() {
        playerY = screenHeight - playerHeight // Position player at bottom of screen
    }


    fun DrawPlayer2(canvas: Canvas) {

        // Update player hitbox
        playerHitbox.left = playerX
        playerHitbox.top = playerY
        playerHitbox.right = playerX + playerWidth
        playerHitbox.bottom = playerY + playerHeight

        // Set the size of the player using the paint's stroke width


        // Draw the player as a horizontal line on the canvas using the drawLine method
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
