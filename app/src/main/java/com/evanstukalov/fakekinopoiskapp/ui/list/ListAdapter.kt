package com.evanstukalov.fakekinopoiskapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evanstukalov.fakekinopoiskapp.databinding.FilmItemBinding
import com.evanstukalov.fakekinopoiskapp.domain.Film

class MyListAdapter(private val onClick: (Film) -> Unit) :
    ListAdapter<Film, MyListAdapter.FilmViewHolder>(FilmDiffCallBack) {


    class FilmViewHolder(private val binding: FilmItemBinding,
                          val onClick: (Film) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.film = film
            binding.executePendingBindings()

            itemView.setOnClickListener {
                onClick(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = FilmItemBinding.inflate(LayoutInflater.from(parent.context))
        return FilmViewHolder(view, onClick)
    }


    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
    }
}

object FilmDiffCallBack : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }
}