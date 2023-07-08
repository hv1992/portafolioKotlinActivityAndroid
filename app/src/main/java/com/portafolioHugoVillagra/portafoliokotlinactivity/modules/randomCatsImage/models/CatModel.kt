package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models

data class CatModel (
    val id: String,
    val tags: List<String>,
    val owner: String,
    val createdAt: String,
    val updatedAt: String
)