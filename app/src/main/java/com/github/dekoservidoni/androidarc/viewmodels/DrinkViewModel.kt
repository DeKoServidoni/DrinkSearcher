package com.github.dekoservidoni.androidarc.viewmodels

import android.arch.lifecycle.*
import android.databinding.Bindable
import android.databinding.ObservableInt
import android.view.View
import com.github.dekoservidoni.androidarc.BR
import com.github.dekoservidoni.androidarc.datamodels.LoadStatus
import com.github.dekoservidoni.androidarc.datamodels.DrinkDataModel
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.datamodels.models.Resource
import com.github.dekoservidoni.androidarc.datamodels.models.ResponseDrink
import javax.inject.Inject

class DrinkViewModel @Inject constructor(private var dataModel: DrinkDataModel): BaseViewModel() {

    @Bindable
    var errorMessage = ""

    var errorVisibility = ObservableInt(View.GONE)
    val loadingVisibility = ObservableInt(View.GONE)
    val contentVisibility = ObservableInt(View.GONE)

    /// Public methods

    fun addToFavorites(drink: Drink) {
        dataModel.addFavorite(drink)
    }

    fun removeFromFavorites(drink: Drink) {
        dataModel.removeFavorite(drink)
    }

    fun search(term: String): LiveData<List<Drink>> {
        val data = MediatorLiveData<List<Drink>>()

        showLoading(true)
        showContent(false)

        data.addSource(dataModel.getDataFromAPI(term), {
            it?.let {
                refreshUI(it)
                data.value = it.data?.drinks
            }
        })

        return data
    }

    fun load(): LiveData<List<Drink>> {
        val data = MediatorLiveData<List<Drink>>()

        showLoading(true)
        showContent(false)

        data.addSource(dataModel.getDataFromDatabase(), {
            it?.let {
                refreshUI(it)
                data.value = it.data?.drinks
            }
        })

        return data
    }

    private fun refreshUI(newValue: Resource<ResponseDrink>?) {

        when(newValue?.status) {

            LoadStatus.ERROR -> {
                showLoading(false)
                showContent(false)

                newValue.message?.let {
                    showError(it)
                }
            }

            LoadStatus.SUCCESS -> {
                showLoading(false)
                showContent(true)
            }

            else -> {
                // do nothing
            }
        }
    }

    private fun showLoading(show: Boolean) {
        loadingVisibility.set(if(show) View.VISIBLE else View.GONE)

        if(show) {
            errorVisibility.set(View.GONE)
        }
    }

    private fun showContent(show: Boolean) {
        contentVisibility.set(if(show) View.VISIBLE else View.GONE)
    }

    private fun showError(message: String) {
        errorVisibility.set(View.VISIBLE)
        errorMessage = message
        notifyPropertyChanged(BR.errorMessage)
    }

}