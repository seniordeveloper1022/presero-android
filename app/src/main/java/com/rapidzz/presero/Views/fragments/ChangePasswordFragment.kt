package com.rapidzz.presero.Views.fragments

import androidx.lifecycle.Observer
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.*
import com.rapidzz.presero.ViewModels.ProfileViewModel

import kotlinx.android.synthetic.main.fragment_forgot.*

class ChangePasswordFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_change_password
    }

    lateinit var viewModel: ProfileViewModel


    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel) {

            changePasswordLiveData.observe(viewLifecycleOwner, Observer {

            })

        }
    }


    override fun initViews() {

//        btnChangePassword.setOnClickListener {
//
//        }
    }
}