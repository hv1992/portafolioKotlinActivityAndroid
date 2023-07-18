package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.interfaces

import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.models.ImageDogModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.models.RaceDogModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogApi {

    //TODO : Obtiene listado de razas de perro
    @GET
    suspend fun getRaceDog(@Url url : String) : Response<RaceDogModel>

    //TODO : Obtiene la url de la imagen del perro para descargar
    @GET
    suspend fun getUrlImageDog(@Url url : String) : Response<ImageDogModel>

    //TODO : Obtiene la imagen del perro
    @GET
    suspend fun downloadImageDog(@Url url : String) : Response<ResponseBody>
}