package com.example.popularalbums.data.repositories

import com.example.popularalbums.api.ApiInterface
import com.example.popularalbums.api.ApiUtilities
import com.example.popularalbums.data.local.AlbumsDatabase
import com.example.popularalbums.data.local.entities.Album

class AlbumRepository(
    private val db: AlbumsDatabase
) {
    suspend fun insertLocalAlbums(items: List<Album>) = db.getAlbumDao().insertAllAlbums(items)

    fun getAllLocalAlbums() = db.getAlbumDao().getAllAlbums()

    suspend fun getRemoteData(): List<Album>? {
        val retroInstance = ApiUtilities.getRetrofitInstance().create(ApiInterface::class.java)
        return retroInstance.getAlbums().body()
    }

    suspend fun getAlbums(): List<Album>? {
        val localData = getAllLocalAlbums()
        return localData.ifEmpty { getRemoteData() }

    }
}