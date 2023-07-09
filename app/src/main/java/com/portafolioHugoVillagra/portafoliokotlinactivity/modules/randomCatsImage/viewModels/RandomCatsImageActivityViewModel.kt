package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.viewModels

import androidx.lifecycle.ViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomCatsImageActivityViewModel : ViewModel(){

    //Existen dos urls: uno es para el builder, y otro es para hacer la llamada
    //El url para el builder exige que si o si tenga finalizado un /, pero para nuestra llamada, no termina en /.
    //Para eso se usa el url de la llamada (urlBaseCat), que este si la url correcta para hacer la llamada
    val urlBaseCat : String = "https://cataas.com/api/cats"
    private val urlBaseBuilder : String = "https://cataas.com/api/"
    private val urlDownloadCat : String = "https://cataas.com/cat/"
    val limitCats : Int = 10

    var listCats : List<CatModel>? = null

    fun catDownloader() : Retrofit {
        return Retrofit.Builder().baseUrl(urlBaseBuilder)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }

    fun imageCatDownloader() : Retrofit{
        val urlBaseDownloadImage = "$urlBaseCat/"
        return Retrofit.Builder().baseUrl(urlBaseDownloadImage).build()
    }

    fun getUrlDownloadCat(idCat : String) : String {
        return this.urlDownloadCat + idCat
    }
}