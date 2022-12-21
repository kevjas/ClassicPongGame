package com.example.classicponggame

import android.graphics.Canvas
import android.graphics.Paint

class Paddle(var posX: Float,var speedX:Float, var sizeX: Float, var sizeY: Float){

    val paint = Paint()

    fun update(){
        posX += speedX
    }

    fun draw(canvas: Canvas?){
    }



}