package com.rapidzz.presero.Views.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.view.View
import androidx.lifecycle.Observer
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.rapidzz.presero.Models.Source.Repository.UserDataSource
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.*
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.ADDRESS_CODE
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.MOBILE_PHONE
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.PHONE_CODE
import com.rapidzz.presero.Utils.GeneralUtils.MapUtility
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.LocationPickerActivity
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            btnVerify->{
                verifyPhone()
            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up
    }


    override fun initViews() {
        btnVerify.setOnClickListener(this)
        ccp.registerCarrierNumberEditText(etPhoneNumber)
        tvAddress.setOnClickListener {
            startPlacePickerIntent()
        }

    }

    override fun attachViewModel() {

        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel)
        {
            userLiveData.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    sessionManager.setUser(it)
                    btnRegister.showSnackBar("Success")
                    requireActivity().onBackPressed()
                }
            })
        }

    }



    lateinit var viewModel: ProfileViewModel
    private var address:String ? = null
    private var latitude:String ?= null
    private var longitude:String ? = null
    private var city:String = ""
    private var country:String  = ""
    private var isAddressCompleted:Boolean=false







    private fun isExtraValidated(): Boolean {
        if (!etEmail.isEmailValid()) {
            etEmail.Error(getString(R.string.invalid_email))
            return false
        }
        else if(!isAddressCompleted)
        {
            showToast(getString(R.string.address_req))
            return false
        }
        else if (btnVerify.isVisibleToUser()) {
            etPhoneNumber.Error(getString(R.string.req_phone_verify))
            return false
        }
        else if (!etReTypePassword.text.toString().equals(etPassword.text.toString(),true)) {
            etReTypePassword.Error(getString(R.string.retype_password))
            return false
        }else
            return true
    }




    private fun verifyPhone()
    {
//        if (ccp.isValidFullNumber) {
//            val intent = Intent(context, PhoneVerificationActivity::class.java)
//            intent.putExtra(MOBILE_PHONE, ccp.fullNumberWithPlus)
//            activity!!.startActivityForResult(intent, PHONE_CODE)
//        } else {
//            showToast(getString(R.string.req_phone))
//
//        }
    }





    private fun startPlacePickerIntent()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            requireActivity()!!.openActivityForResult(LocationPickerActivity::class.java, ADDRESS_CODE)
        } else {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            Permissions.check(context!!,permissions,null,null,object: PermissionHandler(){
                override fun onGranted() {
                    requireActivity()!!.openActivityForResult(LocationPickerActivity::class.java, ADDRESS_CODE)
                }
                override fun onDenied(context: Context?, deniedPermissions: java.util.ArrayList<String>?) {
                    super.onDenied(context, deniedPermissions)
                }
            })
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==PHONE_CODE && resultCode==RESULT_OK)
        {
            btnVerify.gone()
            etPhoneNumber.isEnabled=false
            etPhoneNumber.error=null
            rlVerify.gone()
            ccp.isEnabled=false
        }
        else if(resultCode== RESULT_OK && requestCode == ADDRESS_CODE)
        {

                if (data?.getStringExtra(MapUtility.ADDRESS) != null) {
                    address = data.getStringExtra(MapUtility.ADDRESS)
                    val selectedLatitude:Double = data.getDoubleExtra(MapUtility.LATITUDE, 0.0)
                    val selectedLongitude:Double = data.getDoubleExtra(MapUtility.LONGITUDE, 0.0)
                    latitude=selectedLatitude.toString()
                    longitude=selectedLongitude.toString()
                    tvAddress.text= address
                    fetchLocationDetails(Location("").apply {
                        latitude=selectedLatitude
                        longitude=selectedLongitude
                    })
                }
        }

        super.onActivityResult(requestCode, resultCode, data)

    }








    private fun fetchLocationDetails(location: Location)
    {
        showProgressDialog(true)
        SmartLocation.with(requireContext()).geocoding().reverse(location
        ) { p0, p1 ->

            showProgressDialog(false)
            if (p1.size > 0) {
                if(p1[0].locality!=null) {
                    city = p1[0].locality
                }
                if(city.isEmpty()) {

                    if(!p1[0].adminArea.isNullOrEmpty())
                    {
                        city = p1[0].adminArea
                    }
                    else if(!p1[0].subAdminArea.isNullOrEmpty())
                    {
                        city = p1[0].subAdminArea
                    }
                    else if(p1[0].maxAddressLineIndex>0) {
                        city = p1[0].getAddressLine(0)
                    }
                }

                if(p1[0].countryName!=null) {
                    country = p1[0].countryName
                }

                isAddressCompleted=true


            }
        }


    }
}