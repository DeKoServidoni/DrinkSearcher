package com.github.dekoservidoni.androidarc.view.fragments

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.github.dekoservidoni.androidarc.viewmodels.DrinkViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val dividerItemDecoration by lazy { DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL) }
    protected val drinkViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DrinkViewModel::class.java) }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    protected fun dismissKeyboard(view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected abstract fun setupUI()
}