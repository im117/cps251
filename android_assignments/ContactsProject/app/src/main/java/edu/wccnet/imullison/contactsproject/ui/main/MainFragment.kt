package edu.wccnet.imullison.contactsproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.wccnet.imullison.contactsproject.Contact
import edu.wccnet.imullison.contactsproject.R
import edu.wccnet.imullison.contactsproject.databinding.FragmentMainBinding

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
        binding.addButton.setOnClickListener {
            val name = binding.contactName.text.toString()
            val phone = binding.contactPhone.text.toString()

            if (name != "" && phone != "") {
                val contact = Contact(name, phone)
                viewModel.insertContact(contact)
                clearFields()
            } else {
                Toast.makeText(
                    context?.applicationContext,
                    "You must enter a name and a phone number",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.findButton.setOnClickListener {
            val query = binding.contactName.text.toString()
            if (query != "") {
                viewModel.findContact(query)
            } else {
                Toast.makeText(
                    context?.applicationContext,
                    "You must enter a search criteria in the name field",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.ascButton.setOnClickListener {
            viewModel.sortAsc()

        }
        binding.dscButton.setOnClickListener {
            viewModel.sortDsc()
        }

    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        })
        viewModel.getSearchResults().observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    adapter?.setContactList(it)
                } else {
                    Toast.makeText(
                        context?.applicationContext,
                        "There are no contacts that match your search",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return@Observer
            }
            viewModel.getAllContacts()?.value?.let {
                adapter?.setContactList(it)
            }
        })
    }

    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.contact_list_item) { contactId ->
            viewModel.deleteContact(contactId)
        }
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }

}