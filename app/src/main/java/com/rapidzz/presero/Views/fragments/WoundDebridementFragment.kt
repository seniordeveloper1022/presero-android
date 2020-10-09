package com.rapidzz.presero.Views.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.Views.activities.MainActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_wound_debridement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class WoundDebridementFragment : BaseFragment(), View.OnClickListener {
    override fun initViews() {

    }

    override fun attachViewModel() {

    }


    override fun getLayoutId(): Int {

        return  R.layout.fragment_wound_debridement
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText(getString(R.string.wound_debridement))
        initiateClickLister()
    }

    private fun initiateClickLister() {


        btnYes.setOnClickListener (this)
        btnNo.setOnClickListener (this)
        ivScan.setOnClickListener (this)
        ivScanImage.setOnClickListener(this)
        llBack.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            ivScan ->{
                navigateToFragment(R.id.action_woundDebridementFragment_to_previewWoundFragment,  null)
            }
            ivScanImage ->{
                CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("Choose File")
                    .setCropMenuCropButtonTitle("Crop")
                    .start(activity!!)
            }

            btnNo ->{
                ivScanImage.isEnabled = false
                ivScanImage.setTextColor(resources.getColor(R.color.transparent_white))
                btnNo.setBackgroundResource(R.drawable.button_purple_background)
                btnYes.setBackgroundResource(R.drawable.button_outline_grey)
            }
            btnYes ->{
                ivScanImage.isEnabled = true
                ivScanImage.setTextColor(resources.getColor(R.color.colorWhite))
                btnNo.setBackgroundResource(R.drawable.button_outline_grey)
                btnYes.setBackgroundResource(R.drawable.button_purple_background)

            }

            llBack ->{
                (activity as MainActivity).startAnimation()
                activity?.onBackPressed()
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode === Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                getDropboxIMGSize(resultUri)
            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }

    }

    private fun getDropboxIMGSize(uri: Uri) {
        val options: BitmapFactory.Options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(File(uri?.path).absolutePath, options)
        val imageHeight: Int = options.outHeight
        val imageWidth: Int = options.outWidth
        moveToNextScreen(imageHeight,imageWidth,uri)
    }
    fun moveToNextScreen(imageHeight: Int, imageWidth: Int, uri: Uri) {
        GlobalScope.launch(Dispatchers.Main) {
            var bundle =  Bundle()
            bundle.putString(AppConstants.IMAGE, uri.toString())
            bundle.putInt(AppConstants.HEIGHT, imageHeight)
            bundle.putInt(AppConstants.WIDTH, imageWidth)
            navigateToFragment(R.id.action_woundDebridementFragment_to_previewWoundFragment,  bundle)
        }

    }
}