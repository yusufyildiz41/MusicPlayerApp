package com.yusufyildiz.musicapp.domain.usecase.favouritesong

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class SearchSongUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(songId: Long): Resource<List<Song>> {
        return try {
            Resource.Loading
            Resource.Success(musicRepository.searchSongWithSongId(songId).orEmpty())
        }catch (e: Exception){
            Resource.Error(e)
        }
    }
}