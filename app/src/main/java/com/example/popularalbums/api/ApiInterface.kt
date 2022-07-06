package com.example.popularalbums.api

import com.example.popularalbums.data.local.entities.Album
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(value = "/img/shared/technical-test.json")
    suspend fun getAlbums():Response<List<Album>>
}