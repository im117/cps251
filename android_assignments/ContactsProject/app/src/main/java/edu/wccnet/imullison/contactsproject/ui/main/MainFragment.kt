package edu.wccnet.imullison.contactsproject.ui.main

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.wccnet.imullison.contactsproject.Contact
import edu.wccnet.imullison.contactsproject.R
import edu.wccnet.imullison.contactsproject.databinding.FragmentMainBinding
import java.util.*

class MainFragment : Fragment() {

    private var adapter: ContactListAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        listenerSetup()
        observerSetup()
        recyclerSetup()


        return binding.root
    }

    private fun clearFields() {
        binding.contactName.setText("")
        binding.contactPhone.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener{
            val name = binding.contactName.text.toString()
            val phone = binding.contactPhone.text.toString()

            if (name != "" && phone != "") {
                val digits = PhoneNumberUtils.normalizeNumber(phone)
                val contact = Contact(name, digits.toLong())
                viewModel.insertContact(contact)
                clearFields()
            }
        }
        binding.findButton.setOnClickListener {
            viewModel.findContact(binding.contactName.text.toString())
        }
        binding.ascButton.setOnClickListener {
            viewModel.sortAsc()

        }
        binding.dscButton.setOnClickListener {
            viewModel.sortDsc()
        }

    }

    private fun observerSetup() {
        viewModel.getAllContacts().observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        })
        viewModel.getSearchResults().observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    binding.contactName.setText(it[0].contactName)
                    binding.contactPhone.setText(String.format(Locale.US, "%d", it[0].contactPhone))
                }
            }
        })
    }

    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.contact_list_item)
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }

}