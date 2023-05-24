package com.yusufyildiz.musicapp.presentation.artistlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistListDataModel
import com.yusufyildiz.musicapp.domain.usecase.artistlist.ArtistListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistListViewModel @Inject constructor(
    private val getArtistListByCategoryUseCase: ArtistListUseCase
): ViewModel() {

    private val _artistListStateFlow = MutableStateFlow<Resource<ArtistListDataModel>>(Resource.Loading)
    var artistListStateFlow: StateFlow<Resource<ArtistListDataModel>> = _artistListStateFlow

    fun getArtistListByCategory(categoryId: Int){
        viewModelScope.launch {
            _artistListStateFlow.value = getArtistListByCategoryUseCase.invoke(categoryId)
        }
    }
}