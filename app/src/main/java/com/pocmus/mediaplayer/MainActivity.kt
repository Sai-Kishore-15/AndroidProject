package com.pocmus.mediaplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        btnmp.setOnClickListener {
            Log.i("MainActivity", "Button was clicked")
            Toast.makeText(this, "Re directing to Page 1", Toast.LENGTH_LONG).show()

            val msg:String = SendMsg.text.toString()
            val intent = Intent(this, Page1::class.java)
            intent.putExtra("message_to_mp", msg)

            startActivity(intent)

        }
        btnpic.setOnClickListener {
            Log.i("MainActivity", "Button was clicked")
            Toast.makeText(this, "Re directing to Page 2", Toast.LENGTH_LONG).show()

            val intent=  Intent(this, Page2::class.java)
            startActivity(intent)
        }



    }
}
