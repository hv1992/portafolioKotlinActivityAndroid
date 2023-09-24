package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomDogImageBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.adapters.ListCatImageRecyclerViewAdapter
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.interfaces.DogApi
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels.RandomDogImageActivityViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.views.DogRaceSelectorFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RandomDogImageActivity : AppCompatActivity() {

    lateinit  var binding : ActivityRandomDogImageBinding

    lateinit var viewModel : RandomDogImageActivityViewModel

    //El newInstance trae el fragment instanciado del selector
    private var selectorRaceDogFragment : DogRaceSelectorFragment = DogRaceSelectorFragment.newInstance()

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

    //  Configuración del contenedor del fragmento de selector de raza de perro.
    private fun addFragmentSelectorDogToFragmentContainer() {
        //Codigo para agregar un fragment al contenedor de fragment
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.frameContainerRaceDogSelector.id, this.selectorRaceDogFragment)
        transaction.commit()
    }

    //  Aqui es donde se obtiene la razas de perros, y se setea en el view model del fragmento.
    private fun getRaceDogs() {
        //Hace la llamada para obtener el listado de perros
        viewModel.getRacesDog() {
            //Seteamos el listado de perros en el view model del fragment
            this.selectorRaceDogFragment.viewModel.setListRaceDogs(listDog = it)

            //Configuración el selector de raza principales de perro.
            this.selectorRaceDogFragment.configureMainSpinner()
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun downloadImageDog(url : String) {
        //Se crea el objeto con la cual se va a hacer la llamada
        val dogAPI = viewModel.dogRaceImageDownloader(url).create(DogApi::class.java)
        GlobalScope.launch {
            val result = dogAPI.downloadImageDog(url)
            if (result.isSuccessful) {
                // Checking the results
                Log.d("Resultado descarga","Exitoso")
                val resultBody = result.body()?.byteStream()
                val bitmapImageDog = BitmapFactory.decodeStream(resultBody)
                configureImageViewDog(bitmapImageDog)
            }
        }
    }

    private fun configureImageViewDog(image : Bitmap) {
        runOnUiThread(Runnable {
            binding.imageViewDogImage.setImageBitmap(image)

        })
    }

    //  Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    //  Se ejecuta una vez que la pantalla esten todos presentes.
    override fun onStart() {
        super.onStart()

        //Se configura aqui, porque aqui recien tenemos asignado el viewModel de fragment
        this.selectorRaceDogFragment.viewModel.actionGetImage = { url ->
            viewModel.getUrlImageDog(url, actionAfterCall = {urlImage ->
                Log.d("Url Imagen",urlImage)
                this.downloadImageDog(urlImage)
            })
        }
    }
}