package com.github.dekoservidoni.androidarc.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.github.dekoservidoni.androidarc.R
import com.github.dekoservidoni.androidarc.databinding.FragmentSearchBinding
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.view.adapters.SearchAdapter
import com.github.dekoservidoni.androidarc.viewmodels.DrinkViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SearchFragment : Fragment(), TextView.OnEditorActionListener, Observer<List<Drink>>  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dividerItemDecoration by lazy { DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL) }
    private val searchAdapter by lazy { SearchAdapter() }
    private val drinkViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DrinkViewModel::class.java) }

    private lateinit var fragmentSearchBinding: FragmentSearchBinding

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        fragmentSearchBinding.viewModel = drinkViewModel

        setupUI()

        return fragmentSearchBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drinkViewModel.getData().observe(this, this)
    }

    override fun onEditorAction(textInput: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            dismissKeyboard()
            drinkViewModel.search(textInput?.text.toString())
        }
        return true
    }

    override fun onChanged(newContent: List<Drink>?) {
        newContent?.let {
            searchAdapter.updateContent(it)
        }
    }

    /// Private methods

    private fun setupUI() {
        fragmentSearchBinding.searchResults.addItemDecoration(dividerItemDecoration)
        fragmentSearchBinding.searchResults.layoutManager = LinearLayoutManager(context)
        fragmentSearchBinding.searchResults.adapter = searchAdapter
        fragmentSearchBinding.searchInput.setOnEditorActionListener(this)
    }

    private fun dismissKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(fragmentSearchBinding.searchInput.windowToken, 0)
    }
}