package com.rapidzz.presero.Views.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rapidzz.presero.Models.DataModels.GeneralModels.WoundTypeModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.loadImage
import kotlinx.android.synthetic.main.rv_brain_images.view.*

class TreatmentPlanImagesListAdapter(
    var data: ArrayList<WoundTypeModel>,
    var callback :WoundTypeCallback
) : RecyclerView.Adapter<TreatmentPlanImagesListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_brain_images,
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
                val type = data[position]
                if (type.isSelected) {
                    llDiseaseImage.setBackgroundColor(Color.parseColor("#F24D6C"))
                    tvDiseaseName.setTextColor(resources.getColor(R.color.colorPink))
                    type.isSelected = !type.isSelected
                }
                setOnClickListener { callback.onSelectedWound(position,type) }

                tvDiseaseName.text = type.name
                ivBrainImage.loadImage(type.image)
            }
        }
    }

    interface WoundTypeCallback{
        fun onSelectedWound(position: Int,model:WoundTypeModel)
    }

}