package com.github.dekoservidoni.androidarc.view.adapters

import com.github.dekoservidoni.androidarc.BaseApp
import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.databinding.RowSearchBinding
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.view.adapters.listeners.DrinkActionListener
import com.github.dekoservidoni.androidarc.viewmodels.DrinkViewModel
import com.github.dekoservidoni.androidarc.viewmodels.SearchRowViewModel

class SearchAdapter constructor(private val drinkViewModel: DrinkViewModel)
    : BaseAdapter<RowSearchBinding, Drink>(R.layout.row_search) {

    override fun bind(holder: DataBindViewHolder<RowSearchBinding>, position: Int) {
        val drink = content[position]
        holder.binding.viewModel = SearchRowViewModel(drink, BaseApp.favoriteIds)
        holder.binding.listener = object : DrinkActionListener {
            override fun onUserAction(drink: Drink) {
                if(!drink.isFavorite) {
                    drinkViewModel.addToFavorites(drink)
                    holder.binding.viewModel?.favoriteDrink(true)
                } else {
                    drinkViewModel.removeFromFavorites(drink)
                    holder.binding.viewModel?.favoriteDrink(false)
                }
            }
        }
        holder.binding.executePendingBindings()
    }

    /// Public methods

    override public fun updateContent(newContent: List<Drink>) {
        content.clear()
        content.addAll(newContent)
        notifyDataSetChanged()
    }
}