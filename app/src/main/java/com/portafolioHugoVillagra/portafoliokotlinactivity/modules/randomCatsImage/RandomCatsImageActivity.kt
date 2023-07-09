package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomCatsImageBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.interfaces.CatApi
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.viewModels.RandomCatsImageActivityViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RandomCatsImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomCatsImageBinding

    lateinit  var viewModel : RandomCatsImageActivityViewModel

    //Esto es para registrar un fragment
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se configura el binding
        binding = ActivityRandomCatsImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Creación del viewModel
        viewModel = ViewModelProvider(this)[RandomCatsImageActivityViewModel::class.java]

        //Se establece el titulo a mostrar en el actionBar
        setTitle("Random Cat Images")

        //Se agrega el botón de volver atras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Se llama para tener el listado de gatos
        this.getCats()
    }

    private fun getCats(){
        //Se crea el objeto con la cual se va a hacer la llamada
        val catApi = viewModel.catDownloader().create(CatApi::class.java)
        // launching a new coroutine para hacer asincrono.
        GlobalScope.launch {
            val result = catApi.getCats(viewModel.urlBaseCat, limit = viewModel.limitCats)
            if (result != null) {
                if (result!!.isSuccessful) {
                    // Checking the results
                    Log.d("Cats: ", result.body().toString())
                    viewModel.listCats = result.body()
                    createListCats()
                }

            }
        }
    }

    fun createListCats() {
        val reciclerView = binding.reclyclerViewImageContainer

        this.viewModel.listCats.let {
            for (cat in it!!) {
                val imageCatDownloader = viewModel.imageCatDownloader().create(CatApi::class.java)
                GlobalScope.launch {
                    val urlImageCat = viewModel.getUrlDownloadCat(cat.id)
                    //Se realiza la llamada para obtener la imagen del gato
                    val result = imageCatDownloader.downloadImageCat(urlImageCat)
                    if (result != null) {
                        if(result.isSuccessful) { //Esto es para comprobar si es exitoso o no
                            val resultBody = result.body()?.byteStream() //Aqui se obtiene los bytes de la imagen obtenida
                        }

                    }
                }
            }
        }

    }

    fun createContainerLayout(cat : CatModel) {
        var linearLayoutContainer  = LinearLayout(this)
        linearLayoutContainer.orientation = LinearLayout.VERTICAL

        var image = ImageView(this)
        image.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)

    }

    //Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}