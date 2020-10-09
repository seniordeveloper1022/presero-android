package com.rapidzz.presero.Views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientWoundsModel
import com.rapidzz.presero.R
import kotlinx.android.synthetic.main.rv_wounds.view.*

class PatientsWoundsListListAdapter(
    var context: Context?,
    var data: ArrayList<PatientWoundsModel>,
    var callback: PatientsWoundsListListAdapter.itemClickCallback
): RecyclerView.Adapter<PatientsWoundsListListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_wounds,
                parent,
                false
            )
        ))
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(position)
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {

            with(itemView)
            {
                tvPatientWoundPropertyName.text = data[position].name
                tvPatientWoundPropertyValue.text = data[position].value
                itemView.setOnClickListener {
                    callback.onClick()
                }
            }
        }
    }


    interface itemClickCallback {

        fun onClick()
    }


}