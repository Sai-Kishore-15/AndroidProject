package com.pocmus.mediaplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.android.synthetic.main.page2_layout.*

class Page2: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page2_layout)

        send_button.setOnClickListener {
            val mesg = send_msg.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, mesg)

            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Choose something mofo"))

        }

    }
}