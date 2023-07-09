package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models
import com.google.gson.annotations.SerializedName

data class CatModel (
    @SerializedName("_id")
    val id: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updateAt")
    val updatedAt: String
)