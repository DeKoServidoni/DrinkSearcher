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

class DrinkViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var dataModel: DrinkDataModel

    @Bindable
    var errorMessage = ""

    var errorVisibility = ObservableInt(View.GONE)
    val loadingVisibility = ObservableInt(View.GONE)
    val contentVisibility = ObservableInt(View.GONE)

    private var data = MediatorLiveData<List<Drink>>()

    /// Public methods

    fun getData(): LiveData<List<Drink>> {
        return data
    }

    fun search(term: String) {
        showLoading(true)
        showContent(false)

        data.addSource(dataModel.getDataFromAPI(term), {
            it?.let {
                refreshUI(it)
                data.value = it.data?.drinks
            }
        })
    }

    fun load() {
        showLoading(true)
        showContent(false)

        data.addSource(dataModel.getDataFromDatabase(), {
            it?.let {
                refreshUI(it)
                data.value = it.data?.drinks
            }
        })
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