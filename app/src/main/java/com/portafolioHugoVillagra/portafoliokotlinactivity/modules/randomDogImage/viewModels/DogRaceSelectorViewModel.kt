package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels

import androidx.lifecycle.ViewModel

class DogRaceSelectorViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var listRaceDogs : ArrayList<String> = arrayListOf()
    var listSubRaceDogs : ArrayList<String> = arrayListOf()

    var dogRaceDogSelected : String = ""
    var subRaceDogSelected : String = ""
}