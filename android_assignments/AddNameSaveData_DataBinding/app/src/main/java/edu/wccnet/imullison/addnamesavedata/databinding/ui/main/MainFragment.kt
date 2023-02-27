package edu.wccnet.imullison.addnamesavedata.databinding.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import edu.wccnet.imullison.addnamesavedata.databinding.R
import edu.wccnet.imullison.addnamesavedata.databinding.databinding.FragmentMainBinding
import edu.wccnet.imullison.addnamesavedata.databinding.BR.myViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.setVariable(myViewModel, viewModel)

        return binding.root
    }


}