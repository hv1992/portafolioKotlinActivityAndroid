package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.interfaces

import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CatApi {

    //Esto es el que realizar el get, con @GET
    @GET
    //El @Url sirve para colocar la url base en la cual vamos a hacer la llamada
    //El @Query son los parametros con la cual va a ir concatenado en la url
    //Se usa el coroutines para este caso
    suspend fun getCats(@Url url:String,@Query("limit") limit : Int) : Response<List<CatModel>>
}