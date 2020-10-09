package com.rapidzz.presero.Views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rapidzz.presero.Models.DataModels.GeneralModels.TreatmnetPlanModel
import com.rapidzz.presero.R
import kotlinx.android.synthetic.main.rv_treatment_plan.view.*

class TreatmentPlanPatientsListAdapter(
    var context: Context?,
    var data: ArrayList<TreatmnetPlanModel>,
    var callback: itemClickCallback
): RecyclerView.Adapter<TreatmentPlanPatientsListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.rv_treatment_plan,
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
                if(position == 0)
                {
                    view.visibility = View.INVISIBLE
                }
                else{
                    view.visibility = View.VISIBLE
                }

                rvTreatmentPlanName.text = data[position].name
                tvPatientNumber.text = (position + 1).toString()
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