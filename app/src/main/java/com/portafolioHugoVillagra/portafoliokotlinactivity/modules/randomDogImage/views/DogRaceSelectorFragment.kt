package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.FragmentDogRaceSelectorBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomDogImage.viewModels.DogRaceSelectorViewModel

class DogRaceSelectorFragment : Fragment() {

    companion object {
        fun newInstance() = DogRaceSelectorFragment()
    }

    private lateinit var viewModel: DogRaceSelectorViewModel

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
        // TODO: Use the ViewModel

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this)[DogRaceSelectorViewModel::class.java]
//        // TODO: Use the ViewModel
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}