package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.interfaces.DogApi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomDogImageActivityViewModel : ViewModel() {
    var titleActionBar = "Random Dog Image"

    val urlRequestListRace : String = "https://dog.ceo/api/breeds/list/all"

    var raceDogList : Map<String,Array<String>> = mapOf()

    private fun dogRaceDownloader() : Retrofit {
        val baseUrl = "$urlRequestListRace/"
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun getRacesDog(actionAfterCall: (Map<String, Array<String>>) -> Unit) {
        //Se crea el objeto con la cual se va a hacer la llamada
        val dogAPI = dogRaceDownloader().create(DogApi::class.java)
        GlobalScope.launch {
            val result = dogAPI.getRaceDog(urlRequestListRace)
            if (result.isSuccessful) {
                // Checking the results
                Log.d("Dogs Races: ", result.body().toString())
                //El ?: es para establecer que hacer en caso de que message sea null
                raceDogList = result.body()?.message ?: mapOf()
                actionAfterCall(raceDogList)
            }
        }
    }

}