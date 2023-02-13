package edu.wccnet.imullison.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.wccnet.imullison.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @Suppress("UNUSED_PARAMETER")
    @SuppressLint("SetTextI18n")
    fun calculateTip(view: View) {
        val billTotalString = binding.billTotal.text.toString()
        if (billTotalString.isEmpty()) {
            binding.textView.text = getString(R.string.enter_bill_amount)
        } else {
            val billTotal = billTotalString.toDouble()
            binding.textView.text = """The tips are as follows:
                        |
                        |10% = ${billTotal + billTotal * 0.10}
                        |15% = ${billTotal + billTotal * 0.15}
                        |20% = ${billTotal + billTotal * 0.20}
                        |
                    """.trimMargin()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("status", binding.textView.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.textView.text = savedInstanceState.getCharSequence("status")
    }
}