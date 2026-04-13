package dam.pmdm.spyrothedragon

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        supportActionBar?.hide()

        val videoView= findViewById<VideoView>(R.id.videoView)

        val videoPath = "android.resource://$packageName/${R.raw.video_easter}"
        val uri = Uri.parse(videoPath)

        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener {mediaPlayer ->
            mediaPlayer.isLooping= false
            videoView.start()
        }

        videoView.setOnCompletionListener{
            finish()
        }
    }
}