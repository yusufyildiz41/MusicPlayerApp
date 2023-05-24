package com.yusufyildiz.musicapp.domain.usecase.albumlist

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListDataModel
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(artistId: Int): Resource<AlbumListDataModel>{
        return try {
            Resource.Success(musicRepository.getAlbumListByArtistId(artistId))
        }catch (e: HttpException){
            Resource.Error(e)
        }catch (e: IOException){
            Resource.Error(e)
        }
    }
}