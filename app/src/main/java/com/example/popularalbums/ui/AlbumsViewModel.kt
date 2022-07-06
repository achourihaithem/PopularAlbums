package com.example.popularalbums.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularalbums.data.local.entities.Album
import com.example.popularalbums.data.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlbumsViewModel(

) : ViewModel() {
    private lateinit var repository: AlbumRepository
    private var _albumsLiveData = MutableLiveData<List<Album>?>()
    val albumsLiveData: MutableLiveData<List<Album>?> = _albumsLiveData

    fun getAllAlbums() {

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getAlbums()
            if (data != null && data.isNotEmpty() && repository.getAllLocalAlbums().isEmpty())
                repository.insertLocalAlbums(data)

            _albumsLiveData.postValue(data)
        }
    }

    fun setRepository(repository: AlbumRepository){
        this.repository=repository
    }
}