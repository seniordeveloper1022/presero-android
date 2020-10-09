package com.rapidzz.presero.Views.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.rapidzz.presero.Models.DataModels.GeneralModels.BucketModel
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientWoundsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.PATIENT_DETAIL
import com.rapidzz.presero.Utils.Application.obtainViewModel
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.adapters.PatientsWoundsListListAdapter
import com.rapidzz.presero.Views.adapters.ViewPagerAdapter
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_patient_wounds.*

class PatientDetailFragment : BaseFragment() , View.OnClickListener,
    PatientsWoundsListListAdapter.itemClickCallback {
    override fun initViews() {

    }

    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        with(viewModel){
            setupGeneralViewModel(this)
            getPatientDetailResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if (it.code == 200){

                    }
                }
            })
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_patient_wounds



    var patientModel : BucketModel?= null

    var  arrayList :ArrayList<PatientWoundsModel>  = ArrayList()
    var images : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            patientModel = it[AppConstants.MODEL] as BucketModel
        }
    }
    lateinit var viewModel:ProfileViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText(getString(R.string.patient_wound))
        initiateAdapters()
        initiateClickLister()

        patientModel?.Key?.let {
            viewModel.getPatientDetail(PATIENT_DETAIL, it)
        }
    }

    private fun initiateAdapters() {
        arrayList.clear()
        arrayList.add(PatientWoundsModel("Wound Name : ", "Sacral Wound"))
        arrayList.add(PatientWoundsModel("Wound Type : ", "Pressure Uicer/Injury"))
        arrayList.add(PatientWoundsModel("Last Scan : ", "07/17/2020"))
        arrayList.add(PatientWoundsModel("Area (L x W) : ", "8.25 cm"))
        arrayList.add(PatientWoundsModel("Length : ", "10.0 cm"))
        arrayList.add(PatientWoundsModel("Width : ", "7.8 cm"))
        arrayList.add(PatientWoundsModel("Exact Area : ", "88.8 cm"))
        arrayList.add(PatientWoundsModel("Max Depth : ", "3.3 cm"))
        arrayList.add(PatientWoundsModel("Volume : ", "111.3 cc"))
        arrayList.add(PatientWoundsModel("Reduction in exact area, from first scan : ", "0%"))
        arrayList.add(PatientWoundsModel("Reduction in volume, from first scan : ", "0%"))
        arrayList.add(PatientWoundsModel("Scan Taken : ", "1%"))

        rvWounds.layoutManager = LinearLayoutManager(context)
        rvWounds.adapter  = PatientsWoundsListListAdapter(context, arrayList, this)

        // viewpager adapter
        viewPager.adapter = ViewPagerAdapter(context as MainActivity, images)
        dots.attachViewPager(viewPager)
    }

    private fun initiateClickLister() {
        ivAddPic.setOnClickListener (this)
        llPatientWoundProperties.setOnClickListener (this)
    }

    override fun onClick() {
//        navigateToFragment(R.id.action_patientWoundsFragment_to_treatmentPlanHomeFragment, null)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
               images.add(resultUri.toString())
                viewPager.adapter?.notifyDataSetChanged()
                dots.attachViewPager(viewPager)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.getError()
                showToast(error.message.toString())
            }
        }
    }

    private fun takePic() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        Permissions.check(requireContext(), permissions, null, null, object : PermissionHandler() {
            override fun onGranted() {
                CropImage.activity()
                    .setCropMenuCropButtonTitle("Done")
                    .setActivityTitle("Choose File")
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .start(activity!!)
            }

            override fun onDenied(context: Context?, deniedPermissions: ArrayList<String>?) {
                super.onDenied(context, deniedPermissions)
            }
        })
    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            ivAddPic ->{
                takePic()
            }

            llPatientWoundProperties ->{
                navigateToFragment(R.id.action_patientWoundsFragment_to_treatmentPlanHomeFragment, null)
            }
        }
    }


}