package edu.wccnet.imullison.addnamesavedata.databinding.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val names = ArrayList<String>()
    val nameToAdd: MutableLiveData<String> = MutableLiveData()
    val statusText: MutableLiveData<String> = MutableLiveData()
    val errorVisibility: MutableLiveData<Int> = MutableLiveData(View.INVISIBLE)

    init {
        updateStatusText()
    }

    fun addName() {
        val userInput = nameToAdd.value
        if (userInput == null || userInput.isEmpty()) {
            errorVisibility.value = View.VISIBLE

        } else {
            errorVisibility.value = View.INVISIBLE
            names.add(userInput)
            updateStatusText()

        }
    }

    private fun updateStatusText() {
        if (names.isEmpty()) {
            statusText.value = "No names to display"
        } else {
            statusText.value = names.reduce { acc: String, s: String ->
                acc + "\n" + s
            }
        }
    }

}