package com.yusufyildiz.musicapp.data.model.artistlistmodel

import com.google.gson.annotations.SerializedName

data class ArtistListDataModel(
    @SerializedName("data")
    val artistList: List<ArtistModel>?=null
)