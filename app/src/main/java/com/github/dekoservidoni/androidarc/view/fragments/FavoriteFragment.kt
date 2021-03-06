package com.github.dekoservidoni.androidarc.view.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.databinding.FragmentFavoriteBinding
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.view.adapters.FavoriteAdapter
import dagger.android.support.AndroidSupportInjection

class FavoriteFragment : BaseFragment(), Observer<List<Drink>> {

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding
    private val favoritesResultAdapter by lazy { FavoriteAdapter(drinkViewModel) }

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFavoriteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        fragmentFavoriteBinding.viewModel = drinkViewModel

        setupUI()

        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drinkViewModel.load().observe(this, this)
    }

    override fun onChanged(newContent: List<Drink>?) {
        newContent?.let {
            favoritesResultAdapter.updateContent(it)
        }
    }

    /// Private methods

    override fun setupUI() {
        fragmentFavoriteBinding.favoriteResults.addItemDecoration(dividerItemDecoration)
        fragmentFavoriteBinding.favoriteResults.layoutManager = LinearLayoutManager(context)
        fragmentFavoriteBinding.favoriteResults.adapter = favoritesResultAdapter
    }
}