package com.example.httpsampleapp.ui.screen.imagelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.httpsampleapp.databinding.ItemImageBinding
import com.example.httpsampleapp.domain.model.Image

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val items = mutableListOf<Image>()

    fun submitList(list: List<Image>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.textViewAuthor.text = image.author
            Glide.with(binding.imageViewImage).load(image.imageUrl).into(binding.imageViewImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}