package com.yusufyildiz.musicapp.domain.usecase.favouritesong

import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class AddSongToFavouritesUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(song: Song) = musicRepository.addSongToFavourites(song)
}