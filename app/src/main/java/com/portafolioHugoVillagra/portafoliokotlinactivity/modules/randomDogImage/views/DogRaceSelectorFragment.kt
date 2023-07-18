package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.FragmentDogRaceSelectorBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels.DogRaceSelectorViewModel

class DogRaceSelectorFragment : Fragment(),AdapterView.OnItemSelectedListener {

    //El companion object es para declarar elementos pripios de la clase, es decir, elementos que no forman de una instancia
    companion object {
        //El newInstance es la instancia del fragmento, algo asi como el shared.
        fun newInstance() = DogRaceSelectorFragment()
    }

    lateinit var viewModel: DogRaceSelectorViewModel
    private lateinit var spinnerMainRace : Spinner
    private lateinit var spinnerSubRace : Spinner

    //TODO: Binding del Fragmento
    private var _binding: FragmentDogRaceSelectorBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDogRaceSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Reemplazo de onActivityCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DogRaceSelectorViewModel::class.java]

        binding.buttonGetImageDog.text = viewModel.titleButtonGetImageDog
    }

    //TODO: Se configura el spinner de razas principales de perros.
    fun configureMainSpinner() {
        this.spinnerMainRace = binding.spinnerRaceDog

        //Esto es para que funcione la obtensión de lo seleccionado del spinner, por medio del AdapterView.OnItemSelectedListener
        this.spinnerMainRace.onItemSelectedListener = this

        //Se crea el arrayAdapter para que se adapter el listado al spinner.
        //Depende del activity.baseContext, por eso usamos .let
        activity?.let {
            //Esto es para que se pueda editar el UI.
            it.runOnUiThread(Runnable {
                val adapter = ArrayAdapter(it.baseContext,android.R.layout.simple_list_item_1,viewModel.getListMainRace())
                this.spinnerMainRace.adapter = adapter
            })
        }
    }

    //TODO: Se configura el spinner de subrazas de perros.
    private fun configureSubSpinner(position: Int) {
        viewModel.createListSubRaces(position = position)

        this.spinnerSubRace = binding.spinnerSubRaceDog

        this.spinnerSubRace.onItemSelectedListener = this

        //Activa a desactiva el spinner secundario si tiene
        if(viewModel.getListSubRace().isNotEmpty()) {
            //Muestra el spinner secundario
            this.spinnerSubRace.visibility = ViewGroup.VISIBLE

            activity?.let {
                //Esto es para que se pueda editar el UI.
                it.runOnUiThread(Runnable {
                    val adapter = ArrayAdapter(it.baseContext,android.R.layout.simple_list_item_1,viewModel.getListSubRace())
                    this.spinnerSubRace.adapter = adapter
                })
            }
        } else {
            //Oculta el spinner secundario
            this.spinnerSubRace.visibility = ViewGroup.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //TODO: Esto es para obtener el resultado de lo seleccionado del spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //El parent.id sirve para verificar que spinner se está usando
        if (parent?.id == this.spinnerMainRace.id) {
            configureSubSpinner(position)
        } else if (parent?.id == this.spinnerSubRace.id) {
            this.viewModel.setSubRaceSelected(position)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("Nada","nada")
    }


}