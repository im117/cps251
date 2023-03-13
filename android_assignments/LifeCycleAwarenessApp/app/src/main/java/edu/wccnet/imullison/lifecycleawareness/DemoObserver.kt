package edu.wccnet.imullison.lifecycleawareness

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import edu.wccnet.imullison.lifecycleawareness.ui.main.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class DemoObserver : DefaultLifecycleObserver {
    private val formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        val time: String = formatter.format(Date()) // format current time
        val status = MainViewModel.savedStatus.value ?: "" // If null, use empty string instead
        MainViewModel.savedStatus.value = status + "onCreate was fired on $time\n"
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        val time: String = formatter.format(Date()) // format current time
        val status = MainViewModel.savedStatus.value ?: "" // If null, use empty string instead
        MainViewModel.savedStatus.value = status + "onStart was fired on $time\n"
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        val time: String = formatter.format(Date()) // format current time
        val status = MainViewModel.savedStatus.value ?: "" // If null, use empty string instead
        MainViewModel.savedStatus.value = status + "onResume was fired on $time\n" +
                "***************\n"



    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        val time: String = formatter.format(Date()) // format current time
        val status = MainViewModel.savedStatus.value ?: "" // If null, use empty string instead
        MainViewModel.savedStatus.value = status + "onPause was fired on $time\n" +
                "***************\n"
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        val time: String = formatter.format(Date()) // format current time
        val status = MainViewModel.savedStatus.value ?: "" // If null, use empty string instead
        MainViewModel.savedStatus.value = status + "onStop was fired on $time\n"
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        val time: String = formatter.format(Date()) // format current time
        val status = MainViewModel.savedStatus.value ?: "" // If null, use empty string instead
        MainViewModel.savedStatus.value = status + "onDestroy was fired on $time\n" +
                "***************\n"
    }


}