package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.models

import com.google.gson.annotations.SerializedName

data class ImageDogModel(
    @SerializedName("success")
    val success : String?,
    @SerializedName("message")
    val message : String?
)
