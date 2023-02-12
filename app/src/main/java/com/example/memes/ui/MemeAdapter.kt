package com.example.memes.ui

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.memes.databinding.MemeItemBinding
import com.example.memes.model.Meme


class MemeAdapter : ListAdapter<Meme, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meme>() {

            override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
                return oldItem.postLink == newItem.postLink
            }

            override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
                return oldItem.title== newItem.title&& oldItem.author == newItem.author
                        && oldItem.postLink == newItem.postLink
            }

        }
    }

    private lateinit var binding: MemeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        binding = MemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = getItem(position)
            holder.bind(item, position)
        }
    }

    inner class ViewHolder(private val itemBinding: MemeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Meme, position: Int) {
            itemBinding.autherTV.text = item.author
            itemBinding.titleTV.text = item.title
            itemBinding.memeImg.load(item.url)
        }
    }
}
