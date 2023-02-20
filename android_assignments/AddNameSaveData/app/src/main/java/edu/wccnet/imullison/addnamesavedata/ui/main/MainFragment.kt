package edu.wccnet.imullison.addnamesavedata.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import edu.wccnet.imullison.addnamesavedata.R
import edu.wccnet.imullison.addnamesavedata.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        // Restore data from viewModel if it exists
        binding.nameListTextView.text = viewModel.getStatusText()


        // Add click listener
        binding.addButton.setOnClickListener {
            val name = binding.nameInput.text.toString()
            if (name.isNotEmpty()) {
                viewModel.addName(name)
                binding.nameListTextView.text = viewModel.getStatusText()
            } else {
                Toast.makeText(context, R.string.please_enter_name, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}