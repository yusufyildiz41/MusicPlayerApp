package com.yusufyildiz.musicapp.domain.usecase.playsong

import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import javax.inject.Inject


class StopSongUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(){
        musicRepository.stopSong()
    }
}