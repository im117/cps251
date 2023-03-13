package edu.wccnet.imullison.lifecycleawareness.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

   init {
       updateSavedStatus(savedStateHandle)
   }


    val status: String
        get() = savedStatus.value ?: ""



    companion object {
        const val DATA_KEY = "LifeCycleAwareness"

        var savedStatus = MutableLiveData<String>()


         private fun updateSavedStatus(savedStateHandle: SavedStateHandle) {
            savedStatus = savedStateHandle.getLiveData(DATA_KEY)
        }

    }
}