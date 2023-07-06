package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.viewModels.HeaderMainMenuViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.FragmentHeaderMainMenuBinding

class HeaderMainMenuFragment : Fragment() {

    lateinit var avatarImageView : ImageView
    lateinit var informationTextView : TextView

    //TODO : Configuración del Binding
    private var _binding: FragmentHeaderMainMenuBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = HeaderMainMenuFragment()
    }

    private lateinit var viewModel: HeaderMainMenuViewModel

    //Este se ejecuta cuando el fragmento se diseña por primera vez. Se tiene que retornar un UI, pero si no se va a mostrar interfaz, se retorna un null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeaderMainMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



    //Es la nueva versión del onActivityCreated.
    //Se ejecuta despues del onCreatedView.
    //Aqui tambien es donde se empieza a administrar los elementos de la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HeaderMainMenuViewModel::class.java)


    }

    //Esto es lo opuesto a onViewCreated
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}