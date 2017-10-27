package com.github.dekoservidoni.androidarc.view.fragments

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.databinding.FragmentFavoriteBinding
import com.github.dekoservidoni.androidarc.view.adapters.FavoriteAdapter
import com.github.dekoservidoni.androidarc.viewmodels.DrinkViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dividerItemDecoration by lazy { DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL) }
    private val favoritesResultAdapter by lazy { FavoriteAdapter() }
    private val drinkViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DrinkViewModel::class.java) }

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

        fragmentFavoriteBinding.searchResults.addItemDecoration(dividerItemDecoration)
        fragmentFavoriteBinding.searchResults.layoutManager = LinearLayoutManager(context)
        fragmentFavoriteBinding.searchResults.adapter = favoritesResultAdapter

        fragmentFavoriteBinding.viewModel = drinkViewModel

        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(drinkViewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(drinkViewModel)
    }
}