package edu.wccnet.imullison.contactsproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import edu.wccnet.imullison.contactsproject.Contact
import edu.wccnet.imullison.contactsproject.ContactRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository = ContactRepository(application)
    private val allContacts: MutableLiveData<List<Contact>> = repository.allContacts
    private val searchResults: MutableLiveData<List<Contact>> = repository.searchResults


    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
    }

    fun findContact(name: String) {
        repository.findContact(name)
    }

    fun getSearchResults(): MutableLiveData<List<Contact>> {
        return searchResults
    }

    fun getAllContacts(): MutableLiveData<List<Contact>> {
        return allContacts
    }

    fun sortAsc() {
        repository.sort(ascending = true)
    }

    fun sortDsc() {
        repository.sort(ascending = false)
    }

}