package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage


import android.R
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomDogImageBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.viewModels.RandomCatsImageActivityViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels.RandomDogImageActivityViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.views.DogRaceSelectorFragment


class RandomDogImageActivity : AppCompatActivity() {

    lateinit  var binding : ActivityRandomDogImageBinding

    var viewModel : RandomDogImageActivityViewModel = RandomDogImageActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se configura el binding
        binding = ActivityRandomDogImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Se establece el titulo a mostrar en el actionBar
        title = this.viewModel.titleActionBar

        //Se agrega el botón de volver atras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.addFragmentSelectorDogToFragmentContainer()
    }

    fun addFragmentSelectorDogToFragmentContainer() {
        //Codigo para agregar un fragment al contenedor de fragment
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.frameContainerRaceDogSelector.id, DogRaceSelectorFragment.newInstance())
        transaction.commit()
    }

    //Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}