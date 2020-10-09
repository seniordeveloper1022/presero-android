package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapidzz.presero.Models.DataModels.GeneralModels.TreatmnetPlanModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.newSendEmailIntent
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.adapters.TreatmentPlanPatientsListAdapter
import kotlinx.android.synthetic.main.fragment_treatment_plan.*

class TreatmentPlanFragment : BaseFragment(), TreatmentPlanPatientsListAdapter.itemClickCallback , View.OnClickListener{
    override fun initViews() {

    }

    override fun attachViewModel() {
    }

    override fun getLayoutId(): Int {

        return R.layout.fragment_treatment_plan
    }

    var treatmentPlanLit : ArrayList<TreatmnetPlanModel> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText(getString(R.string.treatment_plan))
        initiateAdapters()
        initiateClickListeners()
    }

    private fun initiateClickListeners() {

        linearLayoutBack.setOnClickListener(this)
        linearLayoutEmail.setOnClickListener(this)
        linearLayoutFinish.setOnClickListener(this)
    }

    private fun initiateAdapters() {

        treatmentPlanLit.add(TreatmnetPlanModel("Antimicrobial debridement agent"))
        treatmentPlanLit.add(TreatmnetPlanModel("Heavy drainage pad"))
        treatmentPlanLit.add(TreatmnetPlanModel("Gauze roll"))
        treatmentPlanLit.add(TreatmnetPlanModel("Filler: Gauze roll"))
        rvTreatmentPlanPatients.layoutManager = LinearLayoutManager(context)
        rvTreatmentPlanPatients.adapter = TreatmentPlanPatientsListAdapter(context, treatmentPlanLit, this)
    }

    override fun onClick() {

    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            linearLayoutBack ->{
                (activity as MainActivity).startAnimation()
                activity?.onBackPressed()

            }
            linearLayoutEmail ->{
                (activity as MainActivity).newSendEmailIntent("maliktoobi3@gmail.com", "", "")
            }

            linearLayoutFinish ->{
                navigateToFragment(R.id.action_treatmentPlanFragment_to_patientWoundsFragment, null)
            }
        }
    }

}