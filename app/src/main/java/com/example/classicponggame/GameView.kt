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

class GameView(context: Context, var mScreenX: Int, var mScreenY: Int): SurfaceView(context),  SurfaceHolder.Callback, Runnable {

    private var thread : Thread? = null
    private var running = false
    lateinit var canvas : Canvas
    private lateinit var ball1 : Ball
    private lateinit var ball2 : paddeln9

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
        ball1 = Ball(1350f, 725f, 50f, 12f, 12f)
        ball2 = paddeln9(context)
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
        ball2.update()
         }

    fun draw(){

        canvas = mHolder!!.lockCanvas()
        canvas.drawColor(Color.WHITE)
        ball1.draw(canvas)
        ball2.Draw(canvas)




        val paint = Paint()


        mHolder!!.unlockCanvasAndPost(canvas)
    }

    fun intersects(b1: Ball, b2: paddeln9)
    {if (Math.sqrt(Math.pow(b1.posX-b2.playerX.toDouble(),2.0)+Math.pow(b1.posY-b2.playerY.toDouble(), 2.0))<=b1.size+b2.playerWidth) {

        bounceBall(b1,b2)

    }

    }

    fun bounceBall(b1: Ball, b2: paddeln9) {
        b1.speedY*= -1
        b2.speedY*= -1
        ball1.paint.color = Color.RED
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        // Get the touch coordinates
        val touchX = event!!.x.toInt()
        val touchY = event!!.y.toInt()


        // Update the player position based on the touch coordinates
        ball2.playerX = touchX - ball2.playerWidth / 2
        ball2.playerY = touchY - ball2.playerHeight / 2


        // Redraw the view
        invalidate()
        return true

    }




    override fun surfaceCreated(holder: SurfaceHolder) {
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

            ball1.checkBounds(bounds)
            ball2.checkBounds(bounds)
            intersects(ball1,ball2)



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