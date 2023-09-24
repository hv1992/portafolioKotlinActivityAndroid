package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu

import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
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

        configureTextOnTextViews()

        configureButtonLinkedid()
    }

    override fun onStart() {
        super.onStart()

        configureImageAvatar()
    }

    private fun configureTextOnTextViews() {
        var titleTextView = this.binding.textViewTitleAbout
        var informationTextView = this.binding.textViewInformationAbout

        titleTextView.text = this.viewModel.titleAboutApp
        informationTextView.text = this.viewModel.informationAboutApp
    }

    private fun configureImageAvatar(){
        val imageAvatar = this.binding.imageViewAvatarAbout

        val cornerRadiusCal = imageAvatar.layoutParams.width / 2

        val cornerRadius = RoundedCorners(cornerRadiusCal)
        //Aplicamos el cornerRadius, y cargamos la imagen en el ImageView, con Glide.
        Glide.with(imageAvatar.context)
            .load("@drawable/avatar_creator")
            .apply(RequestOptions.bitmapTransform(cornerRadius))
            .into(imageAvatar)
    }

    private fun configureButtonLinkedid() {
        var buttonLinkedId = this.binding.buttonLinkedIdAbout
        buttonLinkedId.text = this.viewModel.titleLinkGoToLinkedIn

        buttonLinkedId.setOnClickListener {
            val linkUrl : Uri = Uri.parse(this.viewModel.urlLinkedin)

            val intent : Intent = Intent(Intent.ACTION_VIEW,linkUrl)

            startActivity(intent)

        }
    }
}