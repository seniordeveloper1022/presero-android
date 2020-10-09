package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.rapidzz.presero.Models.DataModels.GeneralModels.BucketModel
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientListModel
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.*
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.adapters.AllPatientsListAdapter
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), AllPatientsListAdapter.PatientCallback {
    override fun initViews() {


    }

    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel)
        {
            getpatientsListResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if (it.code == 200) {
                        filteredList.clear()
                        patientsList.clear()
                        (activity as MainActivity).etSearch.setText("")
                        patientsList.addAll(it.result)
                        if (patientsList.size > 0) {
                            if (isActive) {
                                filteredList = patientsList
                            } else {
                                filteredList.addAll(patientsList.filter { it.IsActive })
                            }

                            tvTotalPatient.text = filteredList.size.toString()
                            setAdapter()
                        }

                    } else {
                        showAlertDialog(it.message)
                    }

                }

            })
            getBucketDataResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if (it.code == 200){
                        bucketList.clear()
                        bucketList.addAll(it.result)
                        patientsModel?.patientDetailId?.let { it1 ->
                            fileteredBucketList.addAll(bucketList.filter { it.Key.contains(it1,true) })
                        }
                        fileteredBucketList.sortByDescending { it.LastModified }
                        Log.e("json","response ${Gson().toJson(fileteredBucketList)}")
                        fileteredBucketList?.let {
                            val bucketModel = it.find { it.Key.contains("Wound.json",true) }
                            Log.e("json","response ${Gson().toJson(bucketModel)}")
                            bucketModel?.let {
                             moveToDetailScreen(it)
                            }

                        }

                    }
                }
            })
        }
    }

    fun moveToDetailScreen(bucketModel: BucketModel) {
        val bundle = Bundle()
        bundle.putSerializable(AppConstants.MODEL,bucketModel)
        navigateToFragment(R.id.action_homeFragment_to_patientWoundsFragment,bundle)
    }
    private fun setAdapter() {

        rvAllPatients.layoutManager = LinearLayoutManager(context)
        rvAllPatients.adapter = AllPatientsListAdapter(context, filteredList, this)

        if (!updateQuery.isNullOrEmpty()) {
            (requireActivity() as MainActivity).findViewById<EditText>(R.id.etSearch).visible()
            (requireActivity() as MainActivity).findViewById<EditText>(R.id.linearLayoutSearchIcon)
                .gone()
            (requireActivity() as MainActivity).findViewById<EditText>(R.id.ivHideSearchBar)
                .visible()

        } else {
            (requireActivity() as MainActivity).findViewById<EditText>(R.id.etSearch).gone()
            (requireActivity() as MainActivity).findViewById<EditText>(R.id.linearLayoutSearchIcon)
                .visible()
            (requireActivity() as MainActivity).findViewById<EditText>(R.id.ivHideSearchBar).gone()
        }
        filterAdapter(updateQuery)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    lateinit var viewModel: ProfileViewModel
    var patientsList: ArrayList<PatientsModel> = ArrayList()
    var allPatientArrayList: ArrayList<PatientListModel> = ArrayList()
    var filteredList: ArrayList<PatientsModel> = ArrayList()
    var bucketList: ArrayList<BucketModel> = ArrayList()
    var fileteredBucketList: ArrayList<BucketModel> = ArrayList()
    var updateQuery = ""
    var isActive = true
    var patientsModel: PatientsModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateQuery = ""
        (activity as MainActivity).setToolbarText(getString(R.string.all_patients))

        initiateClickLister()
        setupSearch()
        swRefreshLayout.setOnRefreshListener {
            swRefreshLayout.isRefreshing = false
            updateQuery = ""
//            (activity as MainActivity).etSearch.setText("")
//            setAdapter()
            viewModel.getPatientsList(AppConstants.PATIENT_TYPE, sessionManager.getAuthToken())
        }

        if (filteredList.isNullOrEmpty()) {
            viewModel.getPatientsList(AppConstants.PATIENT_TYPE, sessionManager.getAuthToken())
        } else {
            setAdapter()
        }
    }


    private fun initiateClickLister() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                filteredList = ArrayList()
                if (tab?.position == 0) {
                    isActive = true
                    filteredList.clear()
                    filteredList = patientsList
                    tvTotalPatient.text = filteredList.size.toString()
                    setAdapter()

                } else if (tab?.position == 1) {
                    isActive = false
                    filteredList.clear()
                    filteredList.addAll(patientsList.filter { it.IsActive })
                    tvTotalPatient.text = filteredList.size.toString()
                    setAdapter()
                }

            }


        })
    }

    override fun onClickedPatient(patientsModel: PatientsModel) {
        this.patientsModel = patientsModel
        viewModel.getBucket()
    }


    private fun setupSearch() {

        (requireActivity() as MainActivity).findViewById<EditText>(R.id.etSearch)
            .addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!p0.isNullOrEmpty()) {
                        filterAdapter(p0.toString())
                    } else {

                        updateQuery = p0.toString()
                        if (!updateQuery.isNullOrEmpty()) {
                            if (isActive) {
                                filteredList = patientsList
                                tvTotalPatient.text = filteredList.size.toString()
                                setAdapter()
                            } else {
                                filteredList = ArrayList(patientsList.filter { it.IsActive })
                                tvTotalPatient.text = filteredList.size.toString()
                                setAdapter()
                            }
                        }
                    }

                }
            })
    }

    fun reSetAdapter() {
        rvAllPatients?.adapter = AllPatientsListAdapter(context, filteredList, this)
    }

    private fun filterAdapter(query: String) {
        updateQuery = query
        if (!updateQuery.isNullOrEmpty()) {

            if (isActive) {
                filteredList = ArrayList(patientsList.filter {
                    it.PatientName.toLowerCase().contains(query.toLowerCase())
                })
                tvTotalPatient.text = filteredList.size.toString()
                rvAllPatients?.adapter = AllPatientsListAdapter(context, filteredList, this)
            } else {
                filteredList = ArrayList(filteredList.filter {
                    it.PatientName.toLowerCase().contains(query.toLowerCase()) &&
                            it.IsActive
                })
                tvTotalPatient.text = filteredList.size.toString()
                rvAllPatients?.adapter = AllPatientsListAdapter(context, filteredList, this)
            }

        }


    }

}