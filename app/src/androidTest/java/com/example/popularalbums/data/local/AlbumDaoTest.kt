package com.example.popularalbums.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.example.popularalbums.data.local.entities.Album
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumDaoTest {
    private lateinit var database: AlbumsDatabase
    private lateinit var dao: AlbumDao

    @Before
    fun setup(){

        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AlbumsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao=database.getAlbumDao()
    }



    @After
    fun teardown(){
        database.close()
    }


    @Test
    fun insertAlums()= runBlockingTest()  {
        val album= Album(1,2,"title1","url","thumbnailUrl")
        val album1= Album(2,3,"title2","url","thumbnailUrl")
        var albums= listOf<Album>(album,album1)

         dao.insertAllAlbums(albums)

        val allAlums= dao.getAllAlbums()

        assertThat(allAlums.size==2)
    }
}