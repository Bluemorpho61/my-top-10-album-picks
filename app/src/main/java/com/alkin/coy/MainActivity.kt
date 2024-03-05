package com.alkin.coy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv_albums: RecyclerView
    private val list = ArrayList<Album>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        rv_albums = findViewById(R.id.rv_heroes)
        rv_albums.setHasFixedSize(true)

        list.addAll(getAlbumList())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page->{
                val intentToAboutActivity =Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentToAboutActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    private fun selectedAlbum(album: Album) {
//        Toast.makeText(this, "Kamu telah memilih" + album.name, Toast.LENGTH_SHORT).show()
//    }

    private fun getAlbumList(): ArrayList<Album> {
        val albumName = resources.getStringArray(R.array.album_title)
        val albumDesc = resources.getStringArray(R.array.data_description)
        val albumImg = resources.obtainTypedArray(R.array.data_photo)
        val albumRelease = resources.getStringArray(R.array.release_date)
        val albumArtist = resources.getStringArray(R.array.artist)
        val listAlbums = ArrayList<Album>()
        for (i in albumName.indices) {
            val album = Album(
                albumName[i],
                albumRelease[i],
                albumArtist[i],
                albumDesc[i],
                albumImg.getResourceId(i, -1)
            )
            listAlbums.add(album)
        }
        return listAlbums
    }

    private fun showRecyclerList() {
        rv_albums.layoutManager = LinearLayoutManager(this)
        val albumListAdapter = AlbumListAdapter(list)
        rv_albums.adapter = albumListAdapter

        albumListAdapter.setOnItemClickCallback(object : AlbumListAdapter.OnitemClickCallback {
            override fun onItemClicked(data: Album) {
//                selectedAlbum(data)
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
}