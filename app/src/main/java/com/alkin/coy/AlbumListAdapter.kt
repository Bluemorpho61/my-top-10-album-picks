package com.alkin.coy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AlbumListAdapter(private val listAlbums: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumListAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnitemClickCallback


    fun setOnItemClickCallback(onitemClickCallback: OnitemClickCallback) {
        this.onItemClickCallback = onitemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.album_art_item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, artist, release, description, photo) = listAlbums[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listAlbums[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listAlbums.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnitemClickCallback {
        fun onItemClicked(data:Album)
    }
}