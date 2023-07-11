package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomCatsImageBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.adapters.ListCatImageRecyclerViewAdapter
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.interfaces.CatApi
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatImageRecyclerModel
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

    private fun createListCats() {
        this.viewModel.listCats.let {
            GlobalScope.launch {
                for (cat in it!!) {
                    val imageCatDownloader = viewModel.imageCatDownloader().create(CatApi::class.java)

                    val urlImageCat = viewModel.getUrlDownloadCat(cat.id)
                    //Se realiza la llamada para obtener la imagen del gato
                    val result = imageCatDownloader.downloadImageCat(urlImageCat)
                    if (result != null) {
                        if(result.isSuccessful) { //Esto es para comprobar si es exitoso o no
                            val resultBody = result.body()?.byteStream() //Aqui se obtiene los bytes de la imagen obtenida
                            val bitmapImageCat = BitmapFactory.decodeStream(resultBody)
                            viewModel.addInformationCatToNewRowRecyclerView(bitmapImageCat,cat.owner)
                            Log.d("Cantidad de gatos asincrono",viewModel.listRowCatForRecycler.size.toString())
                        }
                    }
                }
                Log.d("Cantidad de gatos",viewModel.listRowCatForRecycler.size.toString())
                createAdaptarAndAddToRecyclerView()
            }
        }
    }

    private fun createAdaptarAndAddToRecyclerView() {
        //Esto es para forzar a ejecutar las acciones en plano principal, porque van a tocar elementos ui principales.
        runOnUiThread(Runnable {
            //Con esto se dice basicamente que establece donde se va a usar el recyclerView
            binding.reclyclerViewImageContainer.layoutManager = LinearLayoutManager(this@RandomCatsImageActivity)

            //Con esto se establecer un adaptador, es decir, una clase donde se establece como se va a cargar y como dibujar los elementos de recyclerView
            //Seria que contiene los delegates
            val imageCatRecyclerAdapter = ListCatImageRecyclerViewAdapter(viewModel.listRowCatForRecycler)
            binding.reclyclerViewImageContainer.adapter = imageCatRecyclerAdapter
            binding.linearLayoutBarContainer.visibility = View.GONE
        })
    }


    //Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}