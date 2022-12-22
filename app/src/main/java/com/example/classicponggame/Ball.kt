package com.example.classicponggame

import android.content.Context
import android.graphics.*
import android.view.View

class Ball(context: Context,) : View(context) {
//he
    var ballX = 725f
     var ballY = 1350f
     val ballRadius = 50f
    var speedX = 7f
    var speedY = 7f

    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels



    val ballHitbox = RectF()

    val paint = Paint()

    fun update(){
        ballX += speedX
        ballY += speedY

        // Check if ball has collided with left or right wall
        if (ballX - ballRadius < 0 || ballX + ballRadius > screenWidth) {
            ballX = -ballX
        }

        // Check if ball has collided with top or bottom wall
        if (ballY - ballRadius < 0 || ballY + ballRadius > screenHeight) {
            ballY = -ballY
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
        canvas.drawCircle(ballX, ballY, ballRadius, paint)    }

    fun checkBounds(bounds : Rect){
        if (ballX-75 < bounds.left){
            this.speedX *=-1
        }
        if (ballX+75 > bounds.right){
            this.speedX*= -1
        }
        if (ballY-75 < bounds.top){
            this.speedY *= -1
        }
        if (ballY+75 > bounds.bottom){
            this.speedY *= -1
        }
    }
}
