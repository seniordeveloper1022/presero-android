package com.rapidzz.presero.Views.fragments

import androidx.lifecycle.Observer
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.*
import com.rapidzz.presero.ViewModels.ProfileViewModel

import kotlinx.android.synthetic.main.fragment_forgot.*

class ForgotPasswordFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_forgot
    }

//    lateinit var viewModel: ProfileViewModel


    override fun attachViewModel() {
//        viewModel = obtainViewModel(ProfileViewModel::class.java)
//        setupGeneralViewModel(viewModel)
//        with(viewModel) {
//            forgotPassLiveData.observe(viewLifecycleOwner, Observer {
//
//            })
//        }
    }


    override fun initViews() {


        btnSendRequest.setOnClickListener {
            if (etEmailOrUsername.string().isNullOrEmpty()) {
                etEmailOrUsername.Error(getString(R.string.req_email))
            } else {
                navigateToFragment(R.id.action_forgotPasswordFragment_to_signInFragment, null)
              //  viewModel.forgotPassword(etResetEmail.text.toString())
            }
        }

    }
}