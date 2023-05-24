package com.yusufyildiz.musicapp.presentation.albumdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.albumdetailmodel.AlbumDetailsDataModel
import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.usecase.albumdetail.AlbumDetailUseCase
import com.yusufyildiz.musicapp.domain.usecase.favouritesong.AddSongToFavouritesUseCase
import com.yusufyildiz.musicapp.domain.usecase.favouritesong.DeleteSongFromFavouritesUseCase
import com.yusufyildiz.musicapp.domain.usecase.favouritesong.GetFavouriteSongListUseCase
import com.yusufyildiz.musicapp.domain.usecase.favouritesong.SearchSongUseCase
import com.yusufyildiz.musicapp.domain.usecase.playsong.PlaySongUseCase
import com.yusufyildiz.musicapp.domain.usecase.playsong.StopSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val albumDetailUseCase: AlbumDetailUseCase,
    private val playSongUseCase: PlaySongUseCase,
    private val addSongToFavouritesUseCase: AddSongToFavouritesUseCase,
    private val stopSongUseCase: StopSongUseCase,
    private val deleteSongFromFavouritesUseCase: DeleteSongFromFavouritesUseCase
): ViewModel() {

    private val _albumDetailsStateFlow = MutableStateFlow<Resource<AlbumDetailsDataModel>>(Resource.Loading)
    var albumDetailsStateFlow: StateFlow<Resource<AlbumDetailsDataModel>> = _albumDetailsStateFlow

    fun getAlbumDetailsByAlbumId(albumId: Long){
        viewModelScope.launch {
            _albumDetailsStateFlow.value = albumDetailUseCase.invoke(albumId)
        }
    }

    fun playSongWithSongURL(songURL: String){
        viewModelScope.launch {
            playSongUseCase.invoke(songURL)
        }
    }

    fun stopSong(){
        viewModelScope.launch {
            stopSongUseCase.invoke()
        }
    }
    fun favouriteSong(song: Song){
        viewModelScope.launch {
            when(song.isFavourite){
                true -> {
                    song.isFavourite = false
                    deleteSongFromFavourites(song)
                }
                false -> {
                    song.isFavourite = true
                    addSongToFavourites(song)
                }
            }
        }
    }

    private fun addSongToFavourites(song: Song){
        viewModelScope.launch {
            addSongToFavouritesUseCase.invoke(song)
        }
    }

    private fun deleteSongFromFavourites(song: Song){
        viewModelScope.launch {
            deleteSongFromFavouritesUseCase.invoke(song)
        }
    }


}