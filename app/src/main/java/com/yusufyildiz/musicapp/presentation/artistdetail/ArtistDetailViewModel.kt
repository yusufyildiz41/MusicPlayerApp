package com.yusufyildiz.musicapp.presentation.artistdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListDataModel
import com.yusufyildiz.musicapp.data.model.artistdetailmodel.ArtistDetailModel
import com.yusufyildiz.musicapp.domain.usecase.albumlist.AlbumListUseCase
import com.yusufyildiz.musicapp.domain.usecase.artistdetail.ArtistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val artistDetailUseCase: ArtistDetailUseCase,
    private val albumListUseCase: AlbumListUseCase
) : ViewModel() {

    private val _artistDetailStateFlow = MutableStateFlow<Resource<ArtistDetailModel>>(Resource.Loading)
    var artistDetailStateFlow: StateFlow<Resource<ArtistDetailModel>> = _artistDetailStateFlow

    private val _albumListStateFlow = MutableStateFlow<Resource<AlbumListDataModel>>(Resource.Loading)
    var albumListStateFlow: StateFlow<Resource<AlbumListDataModel>> = _albumListStateFlow

    fun getArtistDetailByArtistId(artistId: Int){
        viewModelScope.launch {
            _artistDetailStateFlow.value = artistDetailUseCase.invoke(artistId)
        }
    }

    fun getAlbumListByArtistId(artistId: Int){
        viewModelScope.launch {
            _albumListStateFlow.value = albumListUseCase.invoke(artistId)
        }
    }
}