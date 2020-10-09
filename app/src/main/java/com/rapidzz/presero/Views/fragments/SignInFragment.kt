package com.rapidzz.presero.Views.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Secure
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.get
import androidx.lifecycle.Observer
import com.rapidzz.presero.BuildConfig
import com.rapidzz.presero.Models.DataModels.GeneralModels.OperatorsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.obtainViewModel
import com.rapidzz.presero.Utils.Application.showAlertDialog
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.activities.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_sigin_in.*


class SignInFragment : BaseFragment(), AdapterView.OnItemSelectedListener {



    override fun getLayoutId(): Int {
        return R.layout.fragment_sigin_in
    }

    lateinit var viewModel: ProfileViewModel
    var versionName: String = ""
    var  device_id: String = ""
    var  operatorName: String = ""
    var operatorsModel : OperatorsModel ? = null
    var operatorsListAdapter: ArrayAdapter<CharSequence>? = null
    var operatorsList : ArrayList<OperatorsModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiCall()

    }


    private fun apiCall() {
        device_id = Secure.getString(context?.contentResolver, Secure.ANDROID_ID)
        versionName = BuildConfig.VERSION_NAME
        // get auth token api call
        viewModel.loginUser(AppConstants.LOGIN_TYPE, "2.3.0", "2067", "1094", "1055")

    }


    private fun applyValidations()
    {

        btnLogin.setOnClickListener {
            if(operatorName.equals("Select Operator"))
            {
                showToast("Please select operator")
            }
           else{
                if(etPassword.text.toString().isEmpty())
                {
                    etPassword.error = "Password Required!!"
                }
                else{
                    viewModel.getEncryptedPassword(AppConstants.ENCRYPTED_PASSWORD_TYPE, etPassword.text.toString())
                }
            }
        }

    }
    private fun setSpinner() {
        var list : ArrayList<CharSequence> = ArrayList()
        list.add("Select Operator")
        for(i in 0 until operatorsList.size)
        {
            list.add(operatorsList[i].FirstName + " " + operatorsList[i].LastName)
        }
        operatorsListAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, list
        )
        operatorsListAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spOperatorLists.adapter = operatorsListAdapter
        spOperatorLists.setOnItemSelectedListener(this)
    }


    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel)
        {

            userLiveData.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    sessionManager.setAuthToken(it.authorizationToken)
                    viewModel.getOperatorsList(AppConstants.OPERATOR_TYPE, sessionManager.getAuthToken())
                }

            })

            getOperatorsListResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                   if(it.code == 200)
                   {
                       operatorsList.clear()
                       operatorsList.addAll(it.result)
                       if (operatorsList.size > 0) {
                           setSpinner()
                       }
                   }
                    else{
                       showAlertDialog(it.message)
                   }
                }

            })

            getEncryptedPasswordResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                   if(it.code == 200)
                   {
                       if(operatorsModel != null)
                       {
                           var operator_name = operatorsModel?.FirstName + " " +operatorsModel?.LastName
                           if(operator_name.equals(operatorName) && operatorsModel?.EncryptedPassword.equals(it.result))
                           {
                               sessionManager.setLoggedIn(true)
                               val intent = Intent(requireContext(), MainActivity::class.java)
                               startActivity(intent)
                               requireActivity().finish()
                           }
                           else{
                               showAlertDialog("User not found")
                           }
                       }
                   }
                    else{
                       showAlertDialog(it.message)
                   }
                }

            })
        }

    }



    override fun initViews() {
        tvForgetPassword.setOnClickListener{
            navigateToFragment(
                R.id.action_signInFragment_to_forgotPasswordFragment,
                null
            )
        }
        applyValidations()
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        (parent?.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.white))
        operatorName = parent.getItemAtPosition(p2).toString()
        operatorsModel = operatorsList.find { (it.FirstName + " "+ it.LastName).equals(operatorName) }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}