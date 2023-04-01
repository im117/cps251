package com.imullison.recyclerviewintent

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageId = Data.images[position]
        holder.titleText = Data.titles[position]
        holder.detailText = Data.details[position]

    }

    override fun getItemCount(): Int {
        return 8
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        var imageId: Int = 0
            get() = field
            set(value) {
                field = value
                imageView.setImageResource(value)
            }


        private val titleView: TextView = itemView.findViewById(R.id.titleText)

        var titleText: String = ""
            set(value) {
                field = value
                titleView.text = value
            }

        private val detailView: TextView = itemView.findViewById(R.id.detailText)
        var detailText: String = ""
            set(value) {
                field = value
                detailView.text = value
            }

        init {
            itemView.setOnClickListener {
                val intent = Intent(it.context, SecondActivity::class.java)
                intent.putExtra("imageId", imageId)
                intent.putExtra("titleText", titleText)
                intent.putExtra("detailText", detailText)
                ContextCompat.startActivity(it.context, intent, null)

            }
        }


    }


}
