package com.yusufyildiz.musicapp.domain.usecase.albumdetail

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.albumdetailmodel.AlbumDetailsDataModel
import com.yusufyildiz.musicapp.data.model.song.Song
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumDetailUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(albumId: Long): Resource<List<Song>>{
        return try {
            Resource.Success(musicRepository.getSongListByAlbumId(albumId))
        }catch (e: HttpException){
            Resource.Error(e)
        }catch (e: IOException){
            Resource.Error(e)
        }
    }
}