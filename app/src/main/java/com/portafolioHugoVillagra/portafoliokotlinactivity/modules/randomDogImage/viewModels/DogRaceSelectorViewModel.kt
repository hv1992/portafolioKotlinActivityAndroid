package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels

import androidx.lifecycle.ViewModel
import kotlin.collections.ArrayList

class DogRaceSelectorViewModel : ViewModel() {

    val titleButtonGetImageDog : String = "Obtener Imagen"

    //TODO: Listado de razas de perros.
    private var listMainRace : Array<String> = emptyArray()
    private var listSubRace : Array<String> = emptyArray()

    //TODO: Raza de perros seleccionados.
    var dogRaceSelected : String = ""
    var subRaceDogSelected : String = ""

    //TODO: Listado principal de todas las razas.
    private var listRaceDogs : Map<String,Array<String>> = mapOf()


    //TODO: Obtensión del listado de razas de perros.
    fun getListMainRace() : Array<String> {
        return this.listMainRace
    }

    fun getListSubRace() : Array<String>{
        return this.listSubRace
    }

    //TODO: Creación de listado de razas de perros.
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

    //TODO: Seteo de raza/s de perro/s
    fun setListRaceDogs(listDog : Map<String,Array<String>>) {
        this.listRaceDogs = listDog
        this.createListMainRaces()
    }

    fun setSubRaceSelected(position : Int) {
        this.subRaceDogSelected = this.listSubRace[position]
    }

}