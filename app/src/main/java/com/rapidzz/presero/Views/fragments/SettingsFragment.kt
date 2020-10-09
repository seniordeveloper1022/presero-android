package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Views.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(), View.OnClickListener {
    override fun initViews() {
        btnOperatorManagement.setOnClickListener(this)
        btnUserManagement.setOnClickListener(this)
    }

    override fun attachViewModel() {
        
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_settings
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setToolbarText("Settings")

    }

    override fun onClick(p0: View?) {
        when(p0){
            btnOperatorManagement ->{
                navigateToFragment(R.id.action_settingsFragment_to_operatorManagementFragment,null)
            }

            btnUserManagement ->{
                showToast("Under Development")
            }
        }
    }

}