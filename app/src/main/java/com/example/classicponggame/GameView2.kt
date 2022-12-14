package com.example.classicponggame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class GameView2(context: Context, var mScreenX: Int, var mScreenY: Int): SurfaceView(context),  SurfaceHolder.Callback, Runnable {

    private var thread : Thread? = null
    private var running = false
    lateinit var canvas : Canvas
    private lateinit var ball1 : Ball
    private lateinit var player1 : player1
    private lateinit var player2 : player2


    private lateinit var GameActivity : GameActivity

    private lateinit var PlayerBot : playerbot

    var mFPS: Long = 0



    private var bounds = Rect()
    var mHolder: SurfaceHolder? = holder

    init {
        if (mHolder != null) {
            mHolder?.addCallback(this)
        }
        setup()

    }

    fun setup() {
        ball1 = Ball(context)
        player1 = player1(context)
        player2 = player2(context)
        ball1.paint.color = Color.BLACK


    }


    fun start(){

        running = true
        thread = Thread(this)
        thread?.start()

    }

    fun stop(){
        running = false
        try{
            thread?.join()

        }catch (e: InterruptedException) {
            e.printStackTrace()
        }


    }

    fun update(){
        ball1.update()
        ball1.updateScore()
        player1.update()
        player2.updateP2()
        player2.startGameP2()


    }

    fun draw(){

        val canvas = mHolder?.lockCanvas()

        canvas?.let {
            canvas.drawColor(Color.WHITE)
            ball1.drawP2(canvas)
            player1.Draw(canvas)
            player2.DrawPlayer2(canvas)


            val paint = Paint()


            mHolder!!.unlockCanvasAndPost(canvas)

        }
    }

    fun intersects(b1: Ball, b2: player1, b4:player2) {
        if (b1.ballHitbox.intersects(b2.playerHitbox.left, b2.playerHitbox.top, b2.playerHitbox.right, b2.playerHitbox.bottom )){
            bounceBall(b1,b2, b4)}


            else if (b1.ballHitbox.intersects(b4.playerHitbox.left, b4.playerHitbox.top, b4.playerHitbox.right, b4.playerHitbox.bottom )) {
            bounceBall(b1,b2,b4)}


        }



    fun bounceBall(b1: Ball, b2: player1, b4: player2 ) {
        b1.speedY*= -1
        b2.speedY*= -1
        b4.speedY*= -1

        ball1.paint.color = Color.RED
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                // get the x and y coordinates of the touch event
                val touchX = event!!.x.toInt()



                // update the position of the paddle based on the touch event
                player1.playerX = touchX - player1.playerWidth / 2
                // Update the player position based on the touch coordinates
                player2.playerX = touchX - player2.playerWidth / 2

            }



        }
        // Get the touch coordinates





        // Redraw the view
        invalidate()
        return true

    }




    override fun surfaceCreated(holder: SurfaceHolder) {
        start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

        bounds = Rect(0,0,width,height)
        start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        stop()
    }



    override fun run(){
        while (running){
            update()
            draw()


            player2.checkBounds(bounds)
            player1.checkBounds(bounds)
            intersects(ball1,player1, player2)

            invalidate()


            // Capture the current time in milliseconds in startFrameTime
            val startFrameTime = System.currentTimeMillis()


            // Calculate FPS
            val timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                mFPS = 1000 / timeThisFrame
            }

    }


}
}