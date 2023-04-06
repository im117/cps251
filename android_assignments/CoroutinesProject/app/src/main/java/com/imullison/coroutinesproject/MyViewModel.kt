package com.imullison.coroutinesproject

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    val names = ArrayList<String>()
    val delays = ArrayList<Long>()




    fun addEntry(name: String, delay: Long) {
        names += name
        delays += delay
    }

}