package com.github.dekoservidoni.androidarc.view.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.dekoservidoni.androidarc.databinding.RowSearchBinding
import com.github.dekoservidoni.androidarc.viewmodels.SearchRowViewModel


class SearchViewHolder(view: View, private var binding: RowSearchBinding) : RecyclerView.ViewHolder(view) {

    fun bindingContent(searchRowViewModel: SearchRowViewModel) {
        binding.viewModel = searchRowViewModel
        binding.executePendingBindings()
    }
}