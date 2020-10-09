package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.rapidzz.presero.Models.DataModels.GeneralModels.OperatorsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.Error
import com.rapidzz.presero.Utils.Application.obtainViewModel
import com.rapidzz.presero.Utils.Application.showAlertDialog
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.ViewModels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_add_operator.*
import kotlinx.android.synthetic.main.fragment_add_operator.cbActive
import kotlinx.android.synthetic.main.fragment_add_operator.etFirstName
import kotlinx.android.synthetic.main.fragment_add_operator.etLastName
import kotlinx.android.synthetic.main.fragment_add_patient.*
import kotlinx.android.synthetic.main.fragment_operator_management.*
import kotlinx.android.synthetic.main.fragment_settings.*


class AddOperatorFragment : BaseFragment(), View.OnClickListener {

    override fun initViews() {
        if(operatorsModel == null)
        {
            setAddOperatorLayout()
        }
        else{
            setEditOperatorLayout()
        }

        llSaveOperator.setOnClickListener(this)
        linearLayoutCancel.setOnClickListener(this)
    }

    private fun setEditOperatorLayout() {

        etFirstName.setText(operatorsModel?.FirstName)
        etLastName.setText(operatorsModel?.LastName)
        etUserName.setText(operatorsModel?.LoginName)
        etEmail.setText(operatorsModel?.Email)
        etMiddleName.setText(operatorsModel?.MiddleName)
        etPassword.setText(operatorsModel?.EncryptedPassword)
        operatorsModel?.IsAdmin?.let {
            if(it)
            {
                cbActive.isChecked = true
            }
            else{
                cbActive.isChecked = false
            }
        }
    }

    private fun setAddOperatorLayout() {
        etFirstName.setText("")
        etLastName.setText("")
        etUserName.setText("")
        etEmail.setText("")
        etMiddleName.setText("")
        etPassword.setText("")
        cbActive.isChecked = false
    }

    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel)
        {


            generalResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if(it.code == 200)
                    {
                       showToast(it.message)
                    }
                    else{
                        showAlertDialog(it.message)
                    }
                }

            })

        }

    }

    var operatorsModel : OperatorsModel ? = null
    lateinit var viewModel: ProfileViewModel
    override fun getLayoutId(): Int {
        return  R.layout.fragment_add_operator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            operatorsModel = it[AppConstants.MODEL] as OperatorsModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            llSaveOperator ->{
                if(isValidForm())
                {
                    if(operatorsModel== null)
                    {
                        operatorsModel = OperatorsModel(
                            false, etEmail.text.toString(),etPassword.text.toString(),
                            etFirstName.text.toString(),"1", cbActive.isChecked, false, "",
                            etLastName.text.toString(), etUserName.text.toString(), etMiddleName.text.toString(),
                            "",false,"123", "12345",false
                        )

                        var gson = Gson()
                        var jsonString = gson.toJson(operatorsModel)
                        viewModel.addOperator(sessionManager.getAuthToken(), jsonString)
                    }
                    else{
                        operatorsModel = OperatorsModel(
                            false, etEmail.text.toString(),etPassword.text.toString(),
                            etFirstName.text.toString(),"1", cbActive.isChecked, false, "",
                            etLastName.text.toString(), etUserName.text.toString(), etMiddleName.text.toString(),
                            "",false,"123", "12345",false
                        )

                        var gson = Gson()
                        var jsonString = gson.toJson(operatorsModel)
                        viewModel.updateOperator(sessionManager.getAuthToken(), jsonString)
                    }
                }

            }
            linearLayoutCancel ->{

                activity?.onBackPressed()
            }




        }
    }

    private fun isValidForm(): Boolean {
        if(etFirstName.text.toString().isEmpty())
        {
            etFirstName.Error("Field Required!!")
            return false
        }

        else if(etLastName.text.toString().isEmpty())
        {
            etLastName.Error("Field Required!!")
            return false
        }
        else if(etMiddleName.text.toString().isEmpty())
        {
            etMiddleName.Error("Field Required!!")
            return false
        }
        else if(etUserName.text.toString().isEmpty())
        {
            etUserName.Error("Field Required!!")
            return false
        }

        else if(etPassword.text.toString().isEmpty())
        {
            etPassword.Error("Field Required!!")
            return false
        }

        else
            return true
    }

}