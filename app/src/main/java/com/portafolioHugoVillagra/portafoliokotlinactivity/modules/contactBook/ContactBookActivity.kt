package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityContactBookBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.viewModels.ContactBookActivityViewModel

class ContactBookActivity : AppCompatActivity() {

    //El nombre de la clase binding es igual al layout, terminado con la palabra Binding
    private lateinit var binding : ActivityContactBookBinding
    private lateinit var viewModel : ContactBookActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se configura el binding
        binding = ActivityContactBookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Se agrega el viewModel
        viewModel = ViewModelProvider(this)[ContactBookActivityViewModel::class.java]
    }



}