package com.yusufyildiz.musicapp.domain.usecase.musiccategory

import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryListModel
import com.yusufyildiz.musicapp.domain.repository.MusicRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MusicCategoryListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(): Resource<MusicCategoryListModel> {
        return try {
            Resource.Success(musicRepository.getMusicCategoryList())
        }catch (e: HttpException){
            Resource.Error(e)
        } catch (e: IOException){
            Resource.Error(e)
        }
    }
}