package com.imullison.recyclerviewintent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondActivityViewModel : ViewModel() {
    val image = MutableLiveData<Int>()
    val text = MutableLiveData<String>()
    val details = MutableLiveData<String>()

}
