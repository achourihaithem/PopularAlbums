package com.example.popularalbums.data.remote

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.popularalbums.data.local.entities.Album
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumRepositoryTest {

    fun getRemoteData(): List<Album>? {
        return Utils.getJson("albums.json") as List<Album>
    }

    @Test
    fun getRemteDataTest() = runBlockingTest {
        var listAlbums: List<Album>? = getRemoteData()

        Truth.assertThat(listAlbums).isNotEmpty()
        Truth.assertThat(listAlbums?.size).isAtLeast(4)
        Truth.assertThat(listAlbums?.get(0)?.title).matches("accusamus beatae ad facilis cum similique qui sunt")
        Truth.assertThat(listAlbums?.get(1)?.id).isNotNull()
    }
}