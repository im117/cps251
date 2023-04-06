package com.imullison.coroutinesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.imullison.coroutinesproject.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val viewModel: MyViewModel by viewModels()

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(viewModel)
        binding.recyclerView.adapter = recyclerAdapter

    }

    fun addName(view: View) {
        val name = binding.nameInput.text.toString()
        coroutineScope.launch {
            val delay = hold().await()
            viewModel.addEntry(name, delay)
            recyclerAdapter.notifyItemInserted(recyclerAdapter.itemCount)
        }
    }

    suspend fun hold() = coroutineScope.async {
        val delayMs = (Random.nextLong(10) + 1) * 1000
        delay(delayMs)
        return@async delayMs
    }


}