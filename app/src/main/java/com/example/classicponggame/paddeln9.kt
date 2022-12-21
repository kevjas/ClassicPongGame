package com.example.classicponggame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

class paddeln9(context: Context,) : View(context) {
//he
    var speedX = 0f
    var speedY = 0f

    // Create a new Paint object for drawing the player
    val paint = Paint()

    // Variables for the player position and size
   var playerX = 50f
    var playerY = 50f
     var playerWidth = 500f
    var playerHeight = 30f

    fun update(){


    }
    fun Draw(canvas: Canvas) {

        // Set the paint color to your desired color
        paint.color = Color.RED

        // Set the size of the player using the paint's stroke width
        paint.strokeWidth = 100f


        // Draw the player as a horizontal line on the canvas using the drawLine method
        canvas.drawLine(playerX.toFloat(), playerY.toFloat(), (playerX + playerWidth).toFloat(), playerY.toFloat(), paint)
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
