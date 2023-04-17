package edu.wccnet.imullison.contactsproject

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ContactRepository(application: Application) {
    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    private var dbContacts: LiveData<List<Contact>>? = null
        set(value) {
            field = value
            allContacts.value = value?.value
        }

    //MutableLiveData "wrapper" for immutable LiveData coming out of database
    var allContacts = object : MutableLiveData<List<Contact>>() {
        private var lifecycleOwner: LifecycleOwner? = null
        private var observer: Observer<in List<Contact>>? = null

        override fun observe(owner: LifecycleOwner, observer: Observer<in List<Contact>>) {
            super.observe(owner, observer)
            this.lifecycleOwner = owner
            this.observer = observer
            dbContacts?.observe(owner, observer)
        }

        override fun setValue(value: List<Contact>?) {
            super.setValue(value)

            //Get observers set up again
            if (lifecycleOwner != null && observer != null) {
                dbContacts?.observe(lifecycleOwner!!, observer!!)
            }
        }

    }


    init {
        val db: ContactRoomDatabase? =
            ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        dbContacts = contactDao?.getAllContacts()
    }

    fun insertContact(newcontact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newcontact)
        }
    }

    private suspend fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }

    private suspend fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String) =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.findContact(name)
        }

    fun sort(ascending: Boolean) {
        coroutineScope.launch(Dispatchers.Main) {
            if (ascending) {
                dbContacts = asyncAscSort().await()
            } else {
                dbContacts = asyncDscSort().await()
            }
        }
    }

    private fun asyncAscSort() =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsAsc()
        }

    private fun asyncDscSort() =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsDsc()
        }
}