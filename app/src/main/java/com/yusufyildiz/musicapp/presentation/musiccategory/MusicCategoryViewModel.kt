package com.yusufyildiz.musicapp.presentation.musiccategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryListModel
import com.yusufyildiz.musicapp.domain.usecase.musiccategory.MusicCategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicCategoryViewModel @Inject constructor(
    private val getMusicCategoryListUseCase: MusicCategoryListUseCase
): ViewModel(){

    private val _musicCategoryListStateFlow = MutableStateFlow<Resource<MusicCategoryListModel>>(Resource.Loading)
    var musicCategoryListStateFlow: StateFlow<Resource<MusicCategoryListModel>> = _musicCategoryListStateFlow

    fun getMusicCategoryList(){
        viewModelScope.launch {
            _musicCategoryListStateFlow.value = getMusicCategoryListUseCase()
        }
    }
}