package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientWoundsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.adapters.PatientsWoundsListListAdapter
import kotlinx.android.synthetic.main.fragment_patient_wounds.*
import kotlinx.android.synthetic.main.fragment_preview_wound.*

class PreviewWoundFragment : BaseFragment(), PatientsWoundsListListAdapter.itemClickCallback,
    View.OnClickListener {
    override fun initViews() {


    }

    override fun attachViewModel() {
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_preview_wound
    }

    var arrayList : ArrayList<PatientWoundsModel> = ArrayList()
    var imageUri = ""
    var imageWidth = 0
    var imageHeight = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            imageUri = it[AppConstants.IMAGE] as String
            if (it.containsKey(AppConstants.WIDTH)){
                imageWidth = it[AppConstants.WIDTH] as Int
            }
            if (it.containsKey(AppConstants.HEIGHT)){
                imageHeight = it[AppConstants.HEIGHT] as Int
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setToolbarText(getString(R.string.preview_wound))
        setLayout()
        initiateClickLister()
        initiateAdapters()
    }

    private fun setLayout() {
        if(imageUri.isEmpty())
        {
            ivEditImage.setImageResource(R.drawable.image_not_available_placeholder)
            ivPreviewImage.setImageResource(R.drawable.image_not_available_placeholder)
        }
        else{
            Glide.with(requireContext()).load(imageUri).into(ivEditImage)
            Glide.with(requireContext()).load(imageUri).into(ivPreviewImage)
        }

    }

    private fun initiateClickLister() {

        ivDelete.setOnClickListener (this)
        ivTrue.setOnClickListener (this)
        tvEditWoundImage.setOnClickListener (this)
    }

    fun initiateAdapters()
    {
        var area = imageHeight * imageWidth
        arrayList.clear()
        arrayList.add(PatientWoundsModel("Area (L x W) : ", area.toString()))
        arrayList.add(PatientWoundsModel("Length : ", imageHeight.toString()))
        arrayList.add(PatientWoundsModel("Width : ", imageWidth.toString()))
        arrayList.add(PatientWoundsModel("Exact Area : ", "88.8 cm"))
        arrayList.add(PatientWoundsModel("Max Depth : ", "3.3 cm"))
        arrayList.add(PatientWoundsModel("Volume : ", "111.3 cc"))
        arrayList.add(PatientWoundsModel("Reduction in exact area, from first scan : ", "0%"))
        arrayList.add(PatientWoundsModel("Reduction in volume, from first scan : ", "0%"))

        rvPreviewWounds.layoutManager = LinearLayoutManager(context)
        rvPreviewWounds.adapter = PatientsWoundsListListAdapter( context, arrayList, this)
    }

    override fun onClick() {

    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            ivTrue ->{
                navigateToFragment(R.id.action_previewWoundFragment_to_woundDrainageFragment, null)
            }
            ivDelete ->{
                (activity as MainActivity).startAnimation()
                activity?.onBackPressed()
            }

            tvEditWoundImage ->{
                if(imageUri.isEmpty())
                {
                    showToast("Not Editable")
                }
                else{
                    var bundle =  Bundle()
                    bundle.putString(AppConstants.IMAGE, imageUri)
                    navigateToFragment(R.id.action_previewWoundFragment_to_editWoundPictureFragment2, bundle)
                }

            }
        }
    }

}