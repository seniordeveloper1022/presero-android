package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.view.View
import com.rapidzz.presero.R
import com.rapidzz.presero.Views.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_wound_debridement.*
import kotlinx.android.synthetic.main.fragment_wound_drainage.*
import kotlinx.android.synthetic.main.fragment_wound_drainage.btnNo
import kotlinx.android.synthetic.main.fragment_wound_drainage.btnYes


class WoundDrainageFragment : BaseFragment(), View.OnClickListener {
    override fun initViews() {

    }

    override fun attachViewModel() {
    }

    override fun getLayoutId(): Int {

        return  R.layout.fragment_wound_drainage
    }

    var isDrainageAmount = false
    var isFillerRequired =  false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText(getString(R.string.wound_drainage))
        initiateClickListeners()
        isDrainageAmount = false
        isFillerRequired =  false
    }



    private fun initiateClickListeners() {
        linearLayoutNext.setOnClickListener(this)
        btnYes.setOnClickListener(this)
        btnNo.setOnClickListener(this)
        btnLowOrNone.setOnClickListener(this)
        btnModerate.setOnClickListener(this)
        btnHigh.setOnClickListener(this)
        linearLayoutBack.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v)
        {
            linearLayoutNext ->{
                navigateToFragment(R.id.action_woundDrainageFragment_to_woundCharacteristicsFragment, null)
            }

            btnNo ->{
                isFillerRequired = true
                if(isDrainageAmount)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnNo.setBackgroundResource(R.drawable.button_purple_background)
                btnYes.setBackgroundResource(R.drawable.button_outline_grey)
            }
            btnYes ->{
                isFillerRequired = true
                if(isDrainageAmount)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnNo.setBackgroundResource(R.drawable.button_outline_grey)
                btnYes.setBackgroundResource(R.drawable.button_purple_background)
            }
            btnLowOrNone ->{
                isDrainageAmount = true
                if(isFillerRequired)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnLowOrNone.setBackgroundResource(R.drawable.button_purple_background)
                btnHigh.setBackgroundResource(R.drawable.button_outline_grey)
                btnModerate.setBackgroundResource(R.drawable.button_outline_grey)
            }

            btnModerate ->{
                isDrainageAmount = true
                if(isFillerRequired)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnModerate.setBackgroundResource(R.drawable.button_purple_background)
                btnHigh.setBackgroundResource(R.drawable.button_outline_grey)
                btnLowOrNone.setBackgroundResource(R.drawable.button_outline_grey)
            }

            btnHigh ->{
                isDrainageAmount = true
                if(isFillerRequired)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnHigh.setBackgroundResource(R.drawable.button_purple_background)
                btnModerate.setBackgroundResource(R.drawable.button_outline_grey)
                btnLowOrNone.setBackgroundResource(R.drawable.button_outline_grey)
            }
            linearLayoutBack ->{
                (activity as MainActivity).startAnimation()
                activity?.onBackPressed()
            }

        }
    }

}