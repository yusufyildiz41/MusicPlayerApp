package com.yusufyildiz.musicapp.domain.usecase.favouritesong

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import java.lang.Exception
import javax.inject.Inject

class GetFavouriteSongListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(): Resource<List<Song>> {
        return try {
            Resource.Loading
            Resource.Success(musicRepository.getFavouriteSongList().orEmpty())
        }catch (e: Exception){
            Resource.Error(e)
        }
    }
}