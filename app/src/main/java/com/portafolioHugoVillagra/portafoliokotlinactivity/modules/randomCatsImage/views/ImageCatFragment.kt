package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.portafolioHugoVillagra.portafoliokotlinactivity.R
import com.portafolioHugoVillagra.portafoliokotlinactivity.databinding.FragmentImageCatBinding
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.randomCatsImage.viewModels.ImageCatViewModel

class ImageCatFragment : Fragment() {

    companion object {
        fun newInstance() = ImageCatFragment()
    }

    private lateinit var viewModel: ImageCatViewModel

    private var _binding: FragmentImageCatBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageCatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ImageCatViewModel::class.java]
        // TODO: Use the ViewModel

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}