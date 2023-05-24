package com.yusufyildiz.musicapp.domain.usecase.artistlist

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistListDataModel
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArtistListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(categoryId: Int): Resource<ArtistListDataModel> {
        return try {
            Resource.Success(musicRepository.getArtistListByCategory(categoryId))
        }catch (e: HttpException){
            Resource.Error(e)
        }catch (e: IOException){
            Resource.Error(e)
        }
    }
}