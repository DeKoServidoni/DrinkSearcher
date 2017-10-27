package com.github.dekoservidoni.androidarc.view.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.ArrayList

abstract class BaseAdapter<T: ViewDataBinding, V>(private val layoutId: Int)
    : RecyclerView.Adapter<BaseAdapter.DataBindViewHolder<T>>() {

    val content = ArrayList<V>()

    class DataBindViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): DataBindViewHolder<T> {
        val binder = DataBindingUtil.inflate<T>(LayoutInflater.from(viewGroup.context), layoutId, viewGroup, false)
        return DataBindViewHolder(binder)
    }

    override fun onBindViewHolder(holder: DataBindViewHolder<T>, position: Int) {
        bind(holder, position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return content.size
    }

    protected abstract fun bind(holder: DataBindViewHolder<T>, position: Int)
    protected abstract fun updateContent(newContent: List<V>)
}