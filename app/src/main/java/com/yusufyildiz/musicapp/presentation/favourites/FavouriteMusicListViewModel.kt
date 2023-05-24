package com.yusufyildiz.musicapp.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.usecase.favouritesong.DeleteSongFromFavouritesUseCase
import com.yusufyildiz.musicapp.domain.usecase.favouritesong.GetFavouriteSongListUseCase
import com.yusufyildiz.musicapp.domain.usecase.playsong.PlaySongUseCase
import com.yusufyildiz.musicapp.domain.usecase.playsong.StopSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteMusicListViewModel @Inject constructor(
    private val getFavouriteSongListUseCase: GetFavouriteSongListUseCase,
    private val playSongUseCase: PlaySongUseCase,
    private val stopSongUseCase: StopSongUseCase,
    private val deleteSongFromFavouritesUseCase: DeleteSongFromFavouritesUseCase
) : ViewModel(){

    private val _favouriteSongListStateFlow = MutableStateFlow<Resource<List<Song>>>(Resource.Loading)
    var favouriteSongListStateFlow: StateFlow<Resource<List<Song>>> = _favouriteSongListStateFlow

    init {
        viewModelScope.launch {
            getFavouriteSongList()
        }
    }

    fun getFavouriteSongList(){
        viewModelScope.launch {
            _favouriteSongListStateFlow.value = getFavouriteSongListUseCase.invoke()
        }
    }

    fun deleteSongFromFavourites(song: Song){
        viewModelScope.launch {
            deleteSongFromFavouritesUseCase.invoke(song).also {
                getFavouriteSongList()
            }
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
}