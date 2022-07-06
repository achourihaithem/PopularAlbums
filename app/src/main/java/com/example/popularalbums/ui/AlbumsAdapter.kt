package com.example.popularalbums.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularalbums.data.local.entities.Album
import com.example.popularalbums.R
import com.squareup.picasso.Picasso

class AlbumsAdapter(private val items:List<Album>):RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image=view.findViewById<ImageView>(R.id.image)
        val tvTitle=view.findViewById<TextView>(R.id.title)
        fun bind(data:Album){
            tvTitle.text=data.title
            val url=data.thumbnailUrl
             Picasso.get()
                 .load(url)
                 .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.album_item,parent,false)
    return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(
            items[position]
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

}