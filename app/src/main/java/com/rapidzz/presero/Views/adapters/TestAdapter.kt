package com.rapidzz.presero.Views.adapters

import android.view.View
import com.rapidzz.presero.R
import kotlinx.android.synthetic.main.info_window_layout.view.*

class TestAdapter(
    var arar:ArrayList<String>,
    var callback: TestAdapter.itemClickCallback
):BaseAdapter(arar,7 , R.layout.info_window_layout) {
    override fun View.bind( position: Int) {
//        (item as String)?.let {
//            this.address.text=it
//        }
    }

    override fun onItemClick(itemView: View, position: Int) {
        with(itemView)
        {
            itemView.setOnClickListener {
                callback.onClick()
            }
        }
    }

    interface itemClickCallback {

        fun onClick()
    }


}
