package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomDogImageBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels.RandomDogImageActivityViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.views.DogRaceSelectorFragment


class RandomDogImageActivity : AppCompatActivity() {

    lateinit  var binding : ActivityRandomDogImageBinding

    lateinit var viewModel : RandomDogImageActivityViewModel

    //El newInstance trae el fragment instanciado del selector
    var selectorRaceDogFragment : DogRaceSelectorFragment = DogRaceSelectorFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se configura el binding
        binding = ActivityRandomDogImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Creación del viewModel
        viewModel = ViewModelProvider(this)[RandomDogImageActivityViewModel::class.java]

        //Se establece el titulo a mostrar en el actionBar
        title = this.viewModel.titleActionBar

        //Se agrega el botón de volver atras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Se carga el fragment de selector de raza de perro al container
        this.addFragmentSelectorDogToFragmentContainer()

        this.getRaceDogs()

    }

    //TODO: Configuración del contenedor del fragmento de selector de raza de perro.
    private fun addFragmentSelectorDogToFragmentContainer() {
        //Codigo para agregar un fragment al contenedor de fragment
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.frameContainerRaceDogSelector.id, this.selectorRaceDogFragment)
        transaction.commit()
    }

    //TODO: Aqui es donde se obtiene la razas de perros, y se setea en el view model del fragmento.
    private fun getRaceDogs() {
        //Hace la llamada para obtener el listado de perros
        viewModel.getRacesDog() {
            //Seteamos el listado de perros en el view model del fragment
            this.selectorRaceDogFragment.viewModel.setListRaceDogs(listDog = it)

            //Configuración el selector de raza principales de perro.
            this.selectorRaceDogFragment.configureMainSpinner()
        }
    }

    //TODO: Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onStart() {
        super.onStart()

    }
}