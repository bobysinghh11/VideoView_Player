package com.stuffshuf.videoview_player

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var playbackPosition=0
    private val rtspUrl="rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov"

    private  lateinit var mediaController:MediaController

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaController =MediaController(this)


        videoView.setOnPreparedListener {
            mediaController.setAnchorView(videoContainer)
            videoView.setMediaController(mediaController)
            videoView.seekTo(playbackPosition)
            videoView.start()
        }
        videoView.setOnInfoListener { player, what, extra ->

            if (what==MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                    progressBar.visibility=View.INVISIBLE
            true

         }

    }

    override fun onStart() {
        super.onStart()
        val uri=Uri.parse(rtspUrl)
        videoView.setVideoURI(uri)
        progressBar.visibility=View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
        playbackPosition=videoView.currentPosition
    }

    override fun onStop() {
        videoView.stopPlayback()

        super.onStop()


    }
}
