package com.yusufyildiz.musicapp.domain.usecase.artistdetail

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.artistdetailmodel.ArtistDetailModel
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArtistDetailUseCase @Inject constructor(
    private val musicRepository: MusicRepository
){
    suspend operator fun invoke(artistId: Int): Resource<ArtistDetailModel>{
        return try {
            Resource.Success(musicRepository.getArtistDetailByArtistId(artistId))
        }catch (e: HttpException){
            Resource.Error(e)
        }catch (e: IOException){
            Resource.Error(e)
        }
    }
}