package com.example.popularalbums.data.local

import androidx.room.*
import com.example.popularalbums.data.local.entities.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album")
    fun getAllAlbums(): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbums( albums: List<Album>)

}