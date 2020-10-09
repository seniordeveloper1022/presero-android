package com.rapidzz.presero.Views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.gone
import com.rapidzz.presero.Utils.Application.loadImage
import com.rapidzz.presero.Utils.Application.visible
import kotlinx.android.synthetic.main.nav_items_main.view.*


class UserItemMenuAdapter(context: Context) : RecyclerView.Adapter<UserItemMenuAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater
    var titles = arrayOf(
        context.getString(R.string.menu_home),
        context.getString(R.string.menu_profile),
        context.getString(R.string.faqs),
        context.getString(R.string.privacy),
        context.getString(R.string.menu_settings)
    )
    var images = intArrayOf(
        R.drawable.icon_home,
        R.drawable.icon_profile,
        R.drawable.icon_faq,
        R.drawable.icon_privacy,
        R.drawable.ic_settings
    )
    var context: Context
    var onClickListner:NavItemClickListner ?= null

    init {
        this.mInflater = LayoutInflater.from(context)
        this.context = context
        onClickListner=context as NavItemClickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.nav_items_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.loadImage(images[position])
        holder.run {
            name.setText(titles[position])

        }
        holder.itemView.setOnClickListener {
            onClickListner?.onclicked(position)
        }
    }

    override fun getItemCount(): Int {
        return titles!!.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name = itemView.tvTitle
        var image = itemView.itemImage

    }


    interface NavItemClickListner{
        fun onclicked(position: Int)
    }
}