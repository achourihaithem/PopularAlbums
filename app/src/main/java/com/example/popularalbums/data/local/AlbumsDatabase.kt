package com.example.popularalbums.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularalbums.data.local.entities.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun getAlbumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: AlbumsDatabase? = null

        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AlbumsDatabase::class.java, "AlbumsDB"
            ).build()
    }
}