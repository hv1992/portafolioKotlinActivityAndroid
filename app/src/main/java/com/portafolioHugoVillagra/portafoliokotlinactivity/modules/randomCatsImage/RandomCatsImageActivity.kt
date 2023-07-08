package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityRandomCatsImageBinding

class RandomCatsImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomCatsImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se configura el binding
        binding = ActivityRandomCatsImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Se establece el titulo a mostrar en el actionBar
        setTitle("Random Cat Images")

        //Se agrega el botón de volver atras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Aqui es donde se configura la acción del botón volver del action bar.
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}