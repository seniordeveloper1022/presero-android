package com.rapidzz.presero.Views.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.Error
import com.rapidzz.presero.Utils.Application.obtainViewModel
import com.rapidzz.presero.Utils.Application.showAlertDialog
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_add_patient.*
import java.text.SimpleDateFormat
import java.util.*


class AddPatientFragment : BaseFragment(), View.OnClickListener {
    override fun initViews() {
        etDateOfBirth.setOnClickListener(this)
        llSavePatient.setOnClickListener(this)
        llCancel.setOnClickListener(this)
    }

    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel)
        {

           /* addPatientsResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if (it.code == 200) {
                        activity?.onBackPressed()
                        showToast(it.message)
                    } else {
                        showAlertDialog(it.message)
                    }

                }

            })*/

        }
    }

    override fun getLayoutId(): Int {

        return R.layout.fragment_add_patient
    }

    var patientsModel : PatientsModel ? = null
    var date: DatePickerDialog.OnDateSetListener? = null
    val myCalendar = Calendar.getInstance()
    var isActive = false
    lateinit var viewModel : ProfileViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as MainActivity).setToolbarText(getString(R.string.add_patient))
        showCalendar()
    }


    private fun showCalendar() {
        date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etDateOfBirth.setText(sdf.format(myCalendar.time))
    }
    fun setCalendarDate() {
        DatePickerDialog(
            requireContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(
                Calendar.MONTH
            ),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }


    private fun doSignUp() {
        if(cbActive.isChecked)
        {
            isActive = true
        }
        else{
            isActive = false
        }

        patientsModel = PatientsModel(
            getCurrentDate(),
            etDateOfBirth.text.toString(),
            "",
            "", "1", isActive, false, getCurrentDate(), "",
            "ae4f6994-3e28-4a2c-52d5-bf8479f3c576","Test Operator",
            -1, "", etPatientId.text.toString(),
            etLastName.text.toString(), etFirstName.text.toString() + " " +etLastName.text.toString(),
            0,"",""
        )

        val gson = Gson()
        var jsonString  = gson.toJson(patientsModel)
       /* viewModel.addPatients(
            AppConstants.ADD_PATIENT_TYPE,
            sessionManager.getAuthToken(),
            jsonString
        )*/

    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return  df.format(c)
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
        else if(etPatientId.text.toString().isEmpty())
        {
            etPatientId.Error("Field Required!!")
            return false
        }
        else if(etDateOfBirth.text.toString().isEmpty())
        {
            etDateOfBirth.Error("Field Required!!")
            return false
        }
       else
            return true
    }
    override fun onClick(p0: View?) {
        when (p0) {
            etDateOfBirth -> {
                setCalendarDate()
            }

            llSavePatient -> {

                if (isValidForm()) { doSignUp() }
            }

            llCancel -> {
                activity?.onBackPressed()
            }
        }
    }


}