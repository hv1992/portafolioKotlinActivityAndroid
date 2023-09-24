package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.collections.ArrayList

class DogRaceSelectorViewModel : ViewModel() {

    val titleButtonGetImageDog : String = "Obtener Imagen"
    val titleTextViewMainRace : String = "Raza principal"
    val titleTextViewSubRace : String = "Raza secundaria"

    //El Unit es como decir que no vas a retornar nada.
    var actionGetImage : ((String) -> Unit)? = null


    //  Listado de razas de perros.
    private var listMainRace : Array<String> = emptyArray()
    private var listSubRace : Array<String> = emptyArray()

    //  Raza de perros seleccionados.
    var dogRaceSelected : String = ""
    var subRaceDogSelected : String = ""

    //  Listado principal de todas las razas.
    private var listRaceDogs : Map<String,Array<String>> = mapOf()


    //  Obtensión del listado de razas de perros.
    fun getListMainRace() : Array<String> {
        return this.listMainRace
    }

    fun getListSubRace() : Array<String>{
        return this.listSubRace
    }

    //  Creación de listado de razas de perros.
    private fun createListMainRaces() {
        this.listMainRace = emptyArray()
        this.listSubRace = emptyArray()
        val listDogTemp : ArrayList<String> = arrayListOf()

        //Con esto lo que se hace es obtener los keys del diccionario (que son las razas principales de perros).
        for (raceDog in this.listRaceDogs.keys) {
            listDogTemp.add(raceDog)
        }

        //Establece el listado de razas principales de perro
        this.listMainRace = listDogTemp.toTypedArray()
    }

    fun createListSubRaces(position: Int) {
        this.dogRaceSelected = this.listMainRace[position]
        this.listSubRace = emptyArray()

        //Esto lo que hace es poner un array vacio en caso de que no exista sub raza de perro
        this.listSubRace = this.listRaceDogs[this.dogRaceSelected] ?: emptyArray()
    }

    //  Seteo de raza/s de perro/s
    fun setListRaceDogs(listDog : Map<String,Array<String>>) {
        this.listRaceDogs = listDog
        this.createListMainRaces()
    }

    fun setSubRaceSelected(position : Int) {
        this.subRaceDogSelected = this.listSubRace[position]
    }

    fun emptySubRaceDog() {
        this.subRaceDogSelected = ""
    }

    fun getUrlImageDog() {
        //No hace falta inicializar
        var parametersRace : String
        var urlCreated : String
        if(this.dogRaceSelected.isNotEmpty() && this.subRaceDogSelected.isNotEmpty()) {
            parametersRace = "${this.dogRaceSelected}/${this.subRaceDogSelected}"
            urlCreated = "https://dog.ceo/api/breed/$parametersRace/images/random"
            Log.d("Url descarga url imagen",urlCreated)
            this.actionGetImage?.let {
                it(urlCreated)
            }
        } else if (this.dogRaceSelected.isNotEmpty()) {
            parametersRace = this.dogRaceSelected
            urlCreated = "https://dog.ceo/api/breed/$parametersRace/images/random"
            Log.d("Url descarga url imagen",urlCreated)
            this.actionGetImage?.let {
                it(urlCreated)
            }
        }
    }

}