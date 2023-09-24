package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu

import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityAboutAppMainBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.viewModels.AboutAppMainViewModel


class AboutAppMainActivity :AppCompatActivity() {

    private lateinit var viewModel: AboutAppMainViewModel
    private lateinit var binding : ActivityAboutAppMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se configura el binding
        this.binding = ActivityAboutAppMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[AboutAppMainViewModel::class.java]

        // Con esto ocultamos la barra de navegación
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }

        configureTextOnTextViews()

        configureButtonLinkedid()

        configureCloseButton()
    }

    //TODO: Cambiar el onBackPressed a la nueva versión, ya que está deprecado
    override fun onBackPressed() {
        super.onBackPressed()

        finishAfterTransition()
    }

    private fun configureTextOnTextViews() {
        var titleTextView = this.binding.textViewTitleAbout
        var informationTextView = this.binding.textViewInformationAbout

        titleTextView.text = this.viewModel.titleAboutApp
        informationTextView.text = this.viewModel.informationAboutApp
    }

    private fun configureButtonLinkedid() {
        var buttonLinkedId = this.binding.buttonLinkedIdAbout
        buttonLinkedId.text = this.viewModel.titleLinkGoToLinkedIn

        buttonLinkedId.setOnClickListener {
            val linkUrl : Uri = Uri.parse(this.viewModel.urlLinkedin)

            val intent = Intent(Intent.ACTION_VIEW,linkUrl)

            startActivity(intent)

        }
    }

    fun configureCloseButton() {
        val closeButton = this.binding.buttonCloseAbout

        closeButton.setOnClickListener {
            finishAfterTransition();
        }
    }
}