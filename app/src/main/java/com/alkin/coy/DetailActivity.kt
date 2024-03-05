package com.alkin.coy

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class DetailActivity : AppCompatActivity() {
    private lateinit var albumTitle: TextView
    private lateinit var albumDesc: TextView
    private lateinit var albumArt: ImageView
    private lateinit var albumArtist:TextView
    private lateinit var albumRelease: TextView

    companion object {
        const val details = "DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        albumTitle = findViewById(R.id.album_title_detail)
        albumDesc = findViewById(R.id.album_desc_detail)
        albumRelease=findViewById(R.id.album_release)
        albumArtist=findViewById(R.id.artist)
        albumArt = findViewById(R.id.album_art_detail)

        val receivedParcelable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Album>("DATA")
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Album>(details)
        }
        Log.d("Detail data:", receivedParcelable?.name.toString())
        albumTitle.text = receivedParcelable?.name.toString()
        albumArt.setImageResource(receivedParcelable?.photo?:R.drawable.verve)
        albumRelease.text=receivedParcelable?.release.toString()
        albumArtist.text=receivedParcelable?.artist.toString()
        albumDesc.text = receivedParcelable?.description.toString()
    }
}