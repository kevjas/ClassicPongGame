package com.example.classicponggame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread : Thread? = null
    private var running = false
    lateinit var canvas : Canvas
    private lateinit var ball1 : Ball
    private lateinit var  : Ball
    private var bounds = Rect()
    var mHolder: SurfaceHolder? = holder

    init {
        if (mHolder != null) {
            mHolder?.addCallback(this)
        }
        setup()
    }

    fun setup() {
        ball1 = Ball(100f, 100f, 50f, 12f, 12f)
        ball2 = Ball(100f, 400f, 50f, 12f, 12f)

        ball1.paint.color = Color.BLACK
        ball2.paint.color = Color.GRAY

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
        ball2.draw(canvas)
        mHolder!!.unlockCanvasAndPost(canvas)
    }

    fun intersects(b1: Ball, b2:Ball)
    {if (Math.sqrt(Math.pow(b1.posX-b2.posX.toDouble(),2.0)
                +Math.pow(b1.posY-b2.posY.toDouble(), 2.0)) <=b1.size+b2.size) {

        bounceBall(b1,b2)

    }

    }

    fun bounceBall(b1: Ball, b2: Ball) {
        b1.speedY*= -1
        b2.speedY = 0f
        ball1.paint.color = Color.RED
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        ball2.posX = event!!.x
        ball2.posY = event!!.y
        return true
    }
    override fun run(){
        while (running){
            update()
            draw()

            ball1.checkBounds(bounds)
            ball2.checkBounds(bounds)
            intersects(ball1,ball2)
        }


    }
}