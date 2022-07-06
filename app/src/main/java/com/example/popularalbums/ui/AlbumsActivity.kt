package com.example.popularalbums.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularalbums.R
import com.example.popularalbums.data.local.AlbumsDatabase
import com.example.popularalbums.data.local.entities.Album
import com.example.popularalbums.data.repositories.AlbumRepository

 class AlbumsActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private val LIST_STATE_KEY = "key_Position"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        recyclerview = findViewById(R.id.recycleview_albums)
        getAlbums(savedInstanceState)

    }

    private fun getAlbums( savedInstanceState: Bundle?) {
        val db = AlbumsDatabase.invoke(this)
        val repository = AlbumRepository(db)
        val model: AlbumsViewModel by viewModels<AlbumsViewModel>()
        model.setRepository(repository)
        model.getAllAlbums()
        model.albumsLiveData.observe(this) { albums ->

            recyclerview.layoutManager = LinearLayoutManager(this)
            recyclerview.adapter = albums?.let { AlbumsAdapter(it) }
            if (savedInstanceState != null) {
                val position = savedInstanceState.getInt(LIST_STATE_KEY, 0)
                recyclerview.scrollToPosition(position)
            }
        }
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
           state.putInt(LIST_STATE_KEY,recyclerview.getCurrentPosition() )

    }

    fun RecyclerView?.getCurrentPosition(): Int {
        return (this?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }
}
