package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.interfaces

import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.io.DataInputStream

interface CatApi {

    //Esto es el que realizar el get, con @GET
    @GET
    //El @Url sirve para colocar la url base en la cual vamos a hacer la llamada
    //El @Query son los parametros con la cual va a ir concatenado en la url
    //Se usa el coroutines para este caso
    suspend fun getCats(@Url url:String,@Query("limit") limit : Int) : Response<List<CatModel>>

    @GET
    //Originalmente se puso que, como salida, sea ResponseBody, pero si ocurre un error de llamada, no se puede captar dicho error. Es por eso que debe entregarse de la forma "Response<ResponseBody>", ya que, de esta forma se puede captar el error.
    suspend fun downloadImageCat(@Url url:String) : Response<ResponseBody>
}