package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityAddContactBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.ActivityContactBookBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.viewModels.AddContactActivityViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.contactBook.viewModels.ContactBookActivityViewModel

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddContactBinding
    private lateinit var viewModel : AddContactActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se configura el binding
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Se agrega el viewModel
        viewModel = ViewModelProvider(this)[AddContactActivityViewModel::class.java]
    }
}