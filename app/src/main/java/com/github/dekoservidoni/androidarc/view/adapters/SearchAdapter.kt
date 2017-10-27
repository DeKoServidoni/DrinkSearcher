package com.github.dekoservidoni.androidarc.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.dekoservidoni.androidarc.databinding.RowSearchBinding
import com.github.dekoservidoni.androidarc.view.adapters.viewholders.SearchViewHolder
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.viewmodels.SearchRowViewModel
import java.util.*

class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>() {

    private val content = ArrayList<Drink>()

    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        val drink = content[position]
        val viewModel = SearchRowViewModel(drink)
        holder?.bindingContent(viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RowSearchBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    /// Public methods

    fun updateContent(newData: List<Drink>) {
        content.clear()
        content.addAll(newData)
        notifyDataSetChanged()
    }
}