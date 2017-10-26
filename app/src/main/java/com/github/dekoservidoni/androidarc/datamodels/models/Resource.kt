package com.github.dekoservidoni.androidarc.datamodels.models

import com.github.dekoservidoni.androidarc.datamodels.LoadStatus

class Resource<out T> internal constructor(val status: LoadStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(LoadStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(LoadStatus.ERROR, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(LoadStatus.LOADING, null, null)
        }
    }
}