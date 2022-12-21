package com.example.classicponggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class GameActivity : AppCompatActivity() {
//he


    val mode = arrayOf("One Player","Two Players")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //hide bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN )

        val spinner = findViewById<Spinner>(R.id.SpinnerMode)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,mode)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }


    fun Play(view: View?){
        val intent= Intent(this,InGameActivity::class.java)
        startActivity(intent)



    }
}

