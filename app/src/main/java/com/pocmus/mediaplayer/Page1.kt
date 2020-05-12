package com.pocmus.mediaplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.page1_layout.*

class Page1: AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page1_layout)
        mp = MediaPlayer.create(this, R.raw.roja)
        mp.isLooping = false
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

//        val bundle: Bundle? = intent.extras
//       val msg = bundle!!.getString("message_to_mp")
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        var volNum = progress / 100.0f
                        mp.setVolume(volNum, volNum)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )
        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )
        //Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mp!=null && mp.isPlaying){
            mp.release()
        }

    }

    @SuppressLint("HandlerLeak")
    var handler  = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var currentPosition = msg.what

            //Position Bar Update
            positionBar.progress = currentPosition
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"

        }
    }




    fun createTimeLabel(time: Int): String{
        var timeLabel = " "
        var min = time/1000/60
        var sec = time/1000 % 60
        timeLabel = "$min"
        if (sec<10) timeLabel+="0"
        timeLabel+=sec
        return timeLabel
        }

    fun playBtnClick(v: View){
        if (mp.isPlaying){
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.play)
        }
        else{
            mp.start()
            playBtn.setBackgroundResource(R.drawable.stop)

        }

    }



}