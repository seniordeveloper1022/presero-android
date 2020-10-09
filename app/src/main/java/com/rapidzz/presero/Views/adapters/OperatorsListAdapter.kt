package com.rapidzz.presero.Views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rapidzz.presero.Models.DataModels.GeneralModels.OperatorsModel
import com.rapidzz.presero.R
import kotlinx.android.synthetic.main.rv_operators_list.view.*
import kotlin.collections.ArrayList

class OperatorsListAdapter(
    var context: Context?,
    var data: ArrayList<OperatorsModel>,
    var callback: itemClickCallback
): RecyclerView.Adapter<OperatorsListAdapter.ViewHolder>(){

    var isChecked = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.rv_operators_list,
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
            var operatorModel = data[position]
            with(itemView)
            {


                if(operatorModel.isSelected)
                {
                    cbActive.isChecked = true
                    operatorModel.isSelected = false
                }
                else{
                    cbActive.isChecked = false
                }
                tvUserName.text = operatorModel.LoginName
                tvFullName.text = operatorModel.FirstName+ operatorModel.LastName
                cbPrivileged.isChecked = operatorModel.IsAdmin
                itemView.setOnClickListener {

                    operatorModel.isSelected = !operatorModel.isSelected
                    if(operatorModel.isSelected)
                    {
                        cbActive.isChecked = false

                    }
                    callback.onOperatorClick(position, operatorModel)
                    notifyDataSetChanged()
                }
            }
        }
    }




    interface itemClickCallback {

        fun onOperatorClick(position: Int, operatorModel: OperatorsModel)
    }
}