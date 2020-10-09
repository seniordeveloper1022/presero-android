package com.rapidzz.presero.Views.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Observer
import com.rapidzz.presero.Models.DataModels.GeneralModels.TreatmentPlanImagesModel
import com.rapidzz.presero.Models.DataModels.GeneralModels.WoundTypeModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.obtainViewModel
import com.rapidzz.presero.Utils.Application.start
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.adapters.TreatmentPlanImagesListAdapter
import kotlinx.android.synthetic.main.fragment_treatment_plan_home.*
import java.util.*
import kotlin.collections.ArrayList


class TreatmentPlanHomeFragment : BaseFragment(), View.OnClickListener,
    TreatmentPlanImagesListAdapter.WoundTypeCallback {

    override fun initViews() {
        spWoundTypes.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                view?.let {
                    (view as TextView).setTextColor(Color.WHITE)
                    if (woundTypes.size > 0) {
                        woundTypes[position].isSelected = true
                        Collections.swap(woundTypes, position, 0)
                        initiateAdapters()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        with(viewModel) {
            setupGeneralViewModel(this)
            getWoundTypesResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if (it.code == 200) {
                        woundTypes.clear()
                        woundTypes.addAll(it.result)
                        rvDiseaseImages.adapter?.notifyDataSetChanged()
                        setWoundTypesAdapter()
                    }
                }
            })
        }
    }

    private fun setWoundTypesAdapter() {
        val adapter: ArrayAdapter<WoundTypeModel> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, woundTypes)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spWoundTypes.adapter = adapter
    }

    override fun getLayoutId(): Int = R.layout.fragment_treatment_plan_home


    var treatmentPlanImagesList: ArrayList<TreatmentPlanImagesModel> = ArrayList()
    var woundTypes: ArrayList<WoundTypeModel> = ArrayList()
    lateinit var viewModel: ProfileViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText(getString(R.string.treatment_plan))
        spWoundTypes.start()
        initiateAdapters()
        initiateClickLister()

        viewModel.getWoundTypes(sessionManager.getAuthToken())
    }

    private fun initiateAdapters() {
        rvDiseaseImages.adapter = TreatmentPlanImagesListAdapter( woundTypes,this)
    }

    private fun initiateClickLister() {

        linearLayoutNext.setOnClickListener(this)
        linearLayoutCancel.setOnClickListener(this)

    }



    override fun onClick(p0: View?) {
        when (p0) {
            linearLayoutCancel -> {
                (activity as MainActivity).startAnimation()
                activity?.onBackPressed()
            }

            linearLayoutNext -> {
                navigateToFragment(R.id.action_treatmentPlanHomeFragment_to_woundDebridementFragment, null)
            }
        }
    }

    override fun onSelectedWound(position: Int, model: WoundTypeModel) {

    }

}