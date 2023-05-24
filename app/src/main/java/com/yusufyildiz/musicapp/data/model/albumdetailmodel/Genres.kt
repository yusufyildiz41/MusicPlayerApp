package com.yusufyildiz.musicapp.data.model.albumdetailmodel

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("data")
    val data: List<Data>?=null
)