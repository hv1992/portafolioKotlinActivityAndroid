package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class DogRaceSelectorViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var listMainRace : Array<String> = emptyArray()
    private var listSubRace : Array<String> = emptyArray()

    private var dogRaceSelected : String = ""
    private var subRaceDogSelected : String = ""

    private var listRaceDogs : Map<String,Array<String>> = mapOf()

    public fun setListRaceDogs(listDog : Map<String,Array<String>>) {
        this.listRaceDogs = listDog
        this.getMainRaces()
    }

    private fun getMainRaces() {
        this.listMainRace = emptyArray()
        this.listSubRace = emptyArray()
        val listDogTemp : ArrayList<String> = arrayListOf()

        for (raceDog in this.listRaceDogs.keys) {
            listDogTemp.add(raceDog)
        }

        //Establece el listado de razas principales de perro
        this.listMainRace = listDogTemp.toTypedArray()


    }

    public fun getSubRaces(mainRace : String) {
        this.dogRaceSelected = mainRace
        this.listSubRace = emptyArray()

        //Esto lo que hace es poner un array vacio en caso de que no exista sub raza de perro
        this.listSubRace = this.listRaceDogs[mainRace] ?: emptyArray()


    }

}