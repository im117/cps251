package edu.wccnet.imullison.addnamesavedata.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val names = ArrayList<String>()



    fun addName(name: String) {
        names.add(name)
    }

    fun getStatusText(): String {
        if (names.isEmpty()) {
            return "No names to display"
        } else {
            return names.reduce { acc: String, s: String ->
                acc + "\n" + s
            }
        }
    }

}