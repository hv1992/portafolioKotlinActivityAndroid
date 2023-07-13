package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.Alignment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityMainMenuBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.constants.OptionMenuCode
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.viewModels.MainMenuViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.RandomCatsImageActivity
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.RandomDogImageActivity

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var viewModel: MainMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se configura el binding
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainMenuViewModel::class.java]

        // Con esto ocultamos la barra de navegación
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }


        setupHeader()
        setupMenu()
        setupOptions()
    }

    fun setupHeader() {
        binding.informationHeaderTextView.text = viewModel.informationHeader
    }

    fun setupMenu() {
       binding.menuTitleTextField.text = viewModel.titleMainMenu
    }

    fun setupOptions() {
        for (optionMenu in this.viewModel.listOptionMenu) {
            //Se prepara el contenedor de la opción
            val linearLayout = LinearLayout(this)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200)
            linearLayout.layoutParams = layoutParams
            linearLayout.orientation = LinearLayout.HORIZONTAL

            //Se prepara la imagen
            var image = ImageView(this)
            image.setImageResource(resources.getIdentifier(optionMenu.iconName,"drawable",packageName))
            val layoutParamsImage = LinearLayout.LayoutParams(200,200)
            image.layoutParams = layoutParamsImage
            linearLayout.addView(image)

            //Se prepara el texto
            var text = TextView(this)
            text.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200)
            text.text = optionMenu.informationOption
            text.gravity = Gravity.CENTER
            text.textAlignment = View.TEXT_ALIGNMENT_CENTER
            text.textSize = 20F
            text.setTextColor(Color.WHITE)
            linearLayout.addView(text)

            binding.optionMenuContainerLayout.addView(linearLayout)

            //Se prepara la acción a donde lleva dicho botón
            linearLayout.setOnClickListener {
                when (optionMenu.optionCode) {
                    OptionMenuCode.catImage -> {
                        val intent = Intent(this,RandomCatsImageActivity::class.java)
                        startActivity(intent)
                    }
                    OptionMenuCode.dogImage -> {
                        val intent = Intent(this,RandomDogImageActivity::class.java)
                        startActivity(intent)
                    }
                }

            }
        }
    }
}