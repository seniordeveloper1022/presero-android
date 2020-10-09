package com.rapidzz.presero.Views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.hide
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.hideKeyboard
import com.rapidzz.presero.Utils.GeneralUtils.DateTimeUtils.changeDateTimeFormat
import kotlinx.android.synthetic.main.rv_all_patients.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AllPatientsListAdapter(
    var context: Context?,
    var data: ArrayList<PatientsModel>,
    var callback: PatientCallback
) : RecyclerView.Adapter<AllPatientsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_all_patients,
                parent,
                false
            )
        ))
    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(position)

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            var patientModel = data[position]
            with(itemView)
            {
                if (position % 2 == 0) {
                    tvOperator.setTextColor(resources.getColor(R.color.colorPurple))
                } else {
                    tvOperator.setTextColor(resources.getColor(R.color.colorPink))
                }

                if (!patientModel.BirthDate.isNullOrEmpty()) {
                    val time =
                        patientModel.BirthDate.let { changeDateTimeFormat(it.substring(0, 9)) }
                    tvDateOfBirth.text = time
                } else {
                    tvDateOfBirth.text = ""
                }
                if (!patientModel.LastScanDate.isNullOrEmpty()) {
                    val time =
                        patientModel.LastScanDate.let { changeDateTimeFormat(it.substring(0, 9)) }
                    tvLasScanDate.text = time
                } else {
                    tvLasScanDate.text = ""
                }

                tvPatientName.text = patientModel.PatientName + " " + patientModel.PatientLastName
                tvPatientId.text = patientModel.PatientId
                tvOperatorName.text = patientModel.OperatorName
                patientModel.patientDetailId = "Data/"+patientModel.accountID+"/"+patientModel.Facility+"/Patients/"+patientModel.ID
                setOnClickListener {
                    hideKeyboard()
                    callback.onClickedPatient(patientModel)
                }
            }
        }
    }


    interface PatientCallback {

        fun onClickedPatient(patientModel: PatientsModel)
    }
}