package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class DogRaceSelectorViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var listMainRace : ArrayList<String> = arrayListOf()
    private var listSubRace : ArrayList<String> = arrayListOf()

    private var dogRaceSelected : String = ""
    private var subRaceDogSelected : String = ""

    private var listRaceDogs : Map<String,Array<String>> = mapOf()

    public fun setListRaceDogs(listDog : Map<String,Array<String>>) {
        this.listRaceDogs = listDog
        this.getMainRaces()
    }

    private fun getMainRaces() {
        this.listMainRace = arrayListOf()
        this.listSubRace = arrayListOf()
        for(mainRace in this.listRaceDogs.keys) {
            this.listMainRace.add(mainRace)
        }
    }

    public fun getSubRaces(mainRace : String) {
        this.dogRaceSelected = mainRace
        this.listSubRace = arrayListOf()

        //Esto lo que hace es poner un array vacio en caso de que no exista sub raza de perro
        val listSubRace = this.listRaceDogs[mainRace] ?: emptyArray()
        for(subRace in listSubRace) {
            this.listSubRace.add(subRace)
        }

    }

}