package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import kotlinx.android.synthetic.main.fragment_edit_wound.*

class EditWoundPictureFragment : BaseFragment() {

    override fun attachViewModel() {

    }

    override fun getLayoutId(): Int {

        return  R.layout.fragment_edit_wound
    }

    var imageUri = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            imageUri = it[AppConstants.IMAGE] as String
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initViews() {
        Glide.with(requireContext()).load(imageUri).into(finger)
        initiateClickListener()
    }

    private fun initiateClickListener() {
        tvClearImage.setOnClickListener {
            finger.clear()
        }


    }

}