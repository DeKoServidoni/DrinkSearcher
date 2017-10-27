package com.github.dekoservidoni.androidarc.view.adapters

import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.databinding.RowFavoriteBinding
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.viewmodels.FavoriteRowViewModel

class FavoriteAdapter : BaseAdapter<RowFavoriteBinding, Drink>(R.layout.row_favorite) {

    override fun bind(holder: BaseAdapter.DataBindViewHolder<RowFavoriteBinding>, position: Int) {
        val drink = content[position]
        holder.binding.viewModel = FavoriteRowViewModel(drink)
        holder.binding.executePendingBindings()
    }

    /// Public methods

    override public fun updateContent(newContent: List<Drink>) {
        content.clear()
        content.addAll(newContent)
        notifyDataSetChanged()
    }
}