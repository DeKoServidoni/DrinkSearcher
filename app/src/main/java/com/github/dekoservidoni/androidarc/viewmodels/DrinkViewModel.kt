package com.github.dekoservidoni.androidarc.viewmodels

import android.arch.lifecycle.*
import android.databinding.Bindable
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import com.github.dekoservidoni.androidarc.BR
import com.github.dekoservidoni.androidarc.datamodels.LoadStatus
import com.github.dekoservidoni.androidarc.datamodels.RepositoryDataModel
import com.github.dekoservidoni.androidarc.datamodels.models.Drink
import com.github.dekoservidoni.androidarc.datamodels.models.Resource
import com.github.dekoservidoni.androidarc.datamodels.models.ResponseDrink
import javax.inject.Inject

class DrinkViewModel @Inject constructor(): ObservableViewModel(),
        Observer<Resource<ResponseDrink>>, LifecycleObserver {

    @Inject
    lateinit var dataModel: RepositoryDataModel

    @Bindable
    var contentList = ArrayList<Drink>()

    @Bindable
    var errorMessage = ""

    var errorVisibility = ObservableInt(View.GONE)
    val loadingVisibility = ObservableInt(View.GONE)
    val contentVisibility = ObservableInt(View.GONE)

    /// Public methods

    fun search(term: String) {
        showLoading(true)
        showContent(false)

        dataModel.getDataFromAPI(term)
    }

    fun load() {
        showLoading(true)
        showContent(false)

        dataModel.getDataFromDatabase()
    }

    override fun onChanged(newValue: Resource<ResponseDrink>?) {

        when(newValue?.status) {

            LoadStatus.ERROR -> {
                showLoading(false)
                showContent(false)

                newValue.message?.let {
                    showError(it)
                }

                notifyContentList(ArrayList())
            }

            LoadStatus.SUCCESS -> {
                newValue.data?.let {
                    notifyContentList(newValue.data.drinks)
                }

                showLoading(false)
                showContent(true)
            }

            LoadStatus.IDLE -> {
                // do nothing
            }

            LoadStatus.LOADING -> {
                // do nothing
            }
        }
    }

    /// Lifecycle methods

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("ANDROID-ARCH", "ViewModel instance created, adding observer!")
        dataModel.getData().observeForever(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("ANDROID-ARCH", "Activity/Fragment destroyed, remove observer!")
        dataModel.getData().removeObserver(this)
    }

    /// Data Binding methods

    private fun notifyContentList(newContent: List<Drink>) {
        contentList.clear()
        contentList.addAll(newContent)
        notifyPropertyChanged(BR.contentList)
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