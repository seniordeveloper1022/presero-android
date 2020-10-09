package com.rapidzz.presero.Views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.rapidzz.presero.R

class ViewPagerAdapter(
    var context: Context,
    var imagesList: ArrayList<String>
) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun getCount(): Int {
        return imagesList.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_view_pager, null)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        Glide.with(context).load(imagesList[position]).centerCrop().placeholder(R.drawable.image_not_available_placeholder)
            .into(imageView)
        container.addView(view)
        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}
