package edu.wccnet.imullison.lifecycleawareness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.wccnet.imullison.lifecycleawareness.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}