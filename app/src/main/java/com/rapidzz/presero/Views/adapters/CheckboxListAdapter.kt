//package com.rapidzz.presero.Views.adapters
//
//import android.content.Context
//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.rapidzz.presero.Models.DataModels.GeneralModels.CheckboxListModel
//import com.rapidzz.presero.Models.DataModels.GeneralModels.TreatmentPlanImagesModel
//import com.rapidzz.presero.R
//import kotlinx.android.synthetic.main.rv_brain_images.view.*
//
//class CheckboxListAdapter(
//    var context: Context?,
//    var data: ArrayList<CheckboxListModel>,
//    var callback: itemClickCallback
//): RecyclerView.Adapter<CheckboxListAdapter.ViewHolder>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return (ViewHolder(
//                LayoutInflater.from(context).inflate(
//                        R.layout.rv_brain_images,
//                        parent,
//                        false
//                )
//        ))
//    }
//
//
//    override fun getItemCount() = data.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(position)
//
//    inner class ViewHolder internal constructor(itemView: View) :
//            RecyclerView.ViewHolder(itemView) {
//
//        fun onBind(position: Int) {
//
//            with(itemView)
//            {
//
//                if(data[position].isSelected)
//                {
//                    llDiseaseImage.setBackgroundColor(Color.parseColor("#F24D6C"))
//                    tvDiseaseName.setTextColor(resources.getColor(R.color.colorPink))
//                }
//                else{
//                    llDiseaseImage.setBackgroundColor(Color.parseColor("#495762"))
//                    tvDiseaseName.setTextColor(resources.getColor(R.color.colorWhite))
//                }
//
//                tvDiseaseName.text = data[position].name
//                Glide.with(context).load(data[position].image).into(ivBrainImage)
//                itemView.setOnClickListener {
//
//                    callback.onClick(position)
//                }
//            }
//        }
//    }
//
//
//
//
//    interface itemClickCallback {
//
//        fun onClick(position: Int)
//    }
//}