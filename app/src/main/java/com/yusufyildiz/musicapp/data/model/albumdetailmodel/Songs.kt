package com.yusufyildiz.musicapp.data.model.albumdetailmodel

import com.google.gson.annotations.SerializedName

data class Songs(
    @SerializedName("data")
    val trackListData: List<SongDetailDto>?=null
)