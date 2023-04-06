package com.imullison.coroutinesproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val viewModel: MyViewModel) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: String = ""
            set(value) {
                field = value
                if (value != "") updateView()
            }

        var delay: Long = -1
            set(value) {
                field = value
                if (value > -1) updateView()
            }

        private fun updateView() {
            itemView.findViewById<TextView>(R.id.cardText).text =
                "The name is $name and the delay is $delay milliseconds"
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.name = viewModel.names[position]
        holder.delay = viewModel.delays[position]
    }

    override fun getItemCount(): Int {
        return viewModel.names.size
    }

}
