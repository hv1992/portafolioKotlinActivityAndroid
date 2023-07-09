package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomCatsImageBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.interfaces.CatApi
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.models.CatModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.viewModels.RandomCatsImageActivityViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RandomCatsImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomCatsImageBinding

    lateinit  var viewModel : RandomCatsImageActivityViewModel

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
        val catApi = viewModel.getCats().create(CatApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = catApi.getCats(viewModel.urlBaseCat, limit = viewModel.limitCats)
            if (result != null) {
                if (result!!.isSuccessful) {
                    // Checking the results
                    Log.d("Cats: ", result.body().toString())
                    viewModel.listCats = result.body()
                }

            }
        }
    }

    //Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}