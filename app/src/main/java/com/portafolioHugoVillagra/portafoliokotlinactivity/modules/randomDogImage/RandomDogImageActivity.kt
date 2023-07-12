package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomDogImageBinding

class RandomDogImageActivity : AppCompatActivity() {

    lateinit  var binding : ActivityRandomDogImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se configura el binding
        binding = ActivityRandomDogImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add(binding.frameContainerRaceDogSelector,DogRaceSelectorFragment)
//                addToBackStack("FragmentSelectorDog")
//            }
//        }

    }
}