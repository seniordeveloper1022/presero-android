package com.rapidzz.presero.Views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter constructor(
    protected var itemList: List<kotlin.Any>,
    protected var size: Int,
    private val layoutResId: Int)
    : RecyclerView.Adapter<BaseAdapter.Holder>() {

    override fun getItemCount() = size

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return Holder(view)
    }

//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val item = itemList[position]
//        holder.itemView.bind(item) }
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.itemView.bind( position)


    protected abstract fun onItemClick(itemView: View, position: Int)

    protected open fun View.bind(position: Int) {
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}