package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.Utils.GeneralUtils.Singleton
import com.rapidzz.presero.Views.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_edit_wound.*
import kotlinx.android.synthetic.main.fragment_preview_picture.*

class PreviewPictureFragment : BaseFragment() {

    override fun attachViewModel() {

    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_preview_picture
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
        (activity as MainActivity).setToolbarText(getString(R.string.preview_wound))

    }

    override fun initViews() {
        if(Singleton.getDrawable() == null)
        {
            Glide.with(requireContext()).load(imageUri).into(myZoomageView)
        }
        else{
            Glide.with(requireContext()).load(Singleton.getDrawable()).into(myZoomageView)
        }
    }


}