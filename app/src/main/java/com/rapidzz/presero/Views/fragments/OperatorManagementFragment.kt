package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rapidzz.presero.Models.DataModels.GeneralModels.OperatorsModel
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.obtainViewModel
import com.rapidzz.presero.Utils.Application.showAlertDialog
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.adapters.OperatorsListAdapter
import kotlinx.android.synthetic.main.fragment_operator_management.*

class OperatorManagementFragment : BaseFragment(), OperatorsListAdapter.itemClickCallback,
    View.OnClickListener {
    override fun initViews() {
        llBack.setOnClickListener(this)
        llAddOperator.setOnClickListener(this)
        tvEditOperator.setOnClickListener(this)
        llDeleteOperator.setOnClickListener(this)
    }

    private fun setAdapter() {
        rvOperatorsList.layoutManager = LinearLayoutManager(context)
        rvOperatorsList.adapter = OperatorsListAdapter(context, operatorsList, this)
    }


    override fun getLayoutId(): Int {
       return  R.layout.fragment_operator_management
    }

    lateinit var viewModel: ProfileViewModel
    var operatorModel : OperatorsModel ? = null

    var operatorsList : ArrayList<OperatorsModel> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText("Operator Management")
        viewModel.getOperatorsList(AppConstants.OPERATOR_TYPE, sessionManager.getAuthToken())

    }

    override fun attachViewModel() {
        viewModel = obtainViewModel(ProfileViewModel::class.java)
        setupGeneralViewModel(viewModel)
        with(viewModel)
        {


            getOperatorsListResponse.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    if(it.code == 200)
                    {
                        operatorsList.clear()
                        operatorsList.addAll(it.result)
                        if (operatorsList.size > 0) {
                            setAdapter()
                        }
                    }
                    else{
                        showAlertDialog(it.message)
                    }
                }

            })

        }

    }

    override fun onOperatorClick(position: Int, model: OperatorsModel) {
        operatorModel = model
        tvEditOperator.isEnabled = true
        tvEditOperator.setTextColor(resources.getColor(R.color.colorWhite))
        tvDeleteOperator.setTextColor(resources.getColor(R.color.colorWhite))
        tvDeleteOperator.isEnabled = true
        for(i in 0 until operatorsList.size)
        {
            if(i == position)
            {
                operatorsList[i].isSelected = true

            }
            else{
                operatorsList[i].isSelected = false
            }
        }

        rvOperatorsList?.adapter?.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            llBack ->{
                activity?.onBackPressed()
            }

            llAddOperator ->{
                navigateToFragment(R.id.action_operatorManagementFragment_to_addOperatorFragment, null)
            }

            tvEditOperator ->{
                var bundle = Bundle()
                bundle.putSerializable(AppConstants.MODEL, operatorModel)
                navigateToFragment(R.id.action_operatorManagementFragment_to_addOperatorFragment, bundle)
            }

            llDeleteOperator ->{
                if(operatorModel != null)
                {
                    operatorModel?.Deleted = true

                    var gson = Gson()
                    var jsonString = gson.toJson(operatorModel)
                    viewModel.deleteOperator(sessionManager.getAuthToken(), jsonString)
                }
                else{
                    showToast("Please select Operator")
                }
            }
            llBack ->{
                activity?.onBackPressed()
            }
        }
    }


}