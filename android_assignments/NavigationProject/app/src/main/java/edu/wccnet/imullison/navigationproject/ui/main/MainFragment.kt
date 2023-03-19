package edu.wccnet.imullison.navigationproject.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.wccnet.imullison.navigationproject.R
import edu.wccnet.imullison.navigationproject.SecondFragmentArgs
import edu.wccnet.imullison.navigationproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.button1.setOnClickListener {
            val action = MainFragmentDirections.mainToSecond()
            action.image = R.drawable.android_image_1
            action.name = "Image 1"
            findNavController().navigate(action)
        }
        binding.button2.setOnClickListener {
            val action = MainFragmentDirections.mainToSecond()
            action.image = R.drawable.android_image_2
            action.name = "Image 2"
            findNavController().navigate(action)
        }
        binding.button3.setOnClickListener {
            val action = MainFragmentDirections.mainToSecond()
            action.image = R.drawable.android_image_3
            action.name = "Image 3"
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}