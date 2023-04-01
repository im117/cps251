package com.imullison.recyclerviewintent

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imullison.recyclerviewintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val viewModel: SecondActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySecondBinding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        viewModel.image.value = intent.getIntExtra("imageId", android.R.color.transparent)
        viewModel.text.value = intent.getStringExtra("titleText")
        viewModel.details.value = intent.getStringExtra("detailText")
        binding.viewModel = viewModel
    }



}