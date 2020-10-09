package com.rapidzz.presero.Views.fragments

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.gone
import com.rapidzz.presero.Utils.Application.showAlertDialog
import com.rapidzz.presero.Utils.Application.visible
import com.rapidzz.presero.Views.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_wound_characteristics.*
import kotlinx.android.synthetic.main.fragment_wound_characteristics.btnNo
import kotlinx.android.synthetic.main.fragment_wound_characteristics.btnYes
import kotlinx.android.synthetic.main.fragment_wound_characteristics.linearLayoutBack
import kotlinx.android.synthetic.main.fragment_wound_characteristics.linearLayoutNext
import kotlinx.android.synthetic.main.fragment_wound_drainage.*


class WoundCharacteristicsFragment : BaseFragment() , View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    override fun initViews() {
    }

    override fun attachViewModel() {
    }

    override fun getLayoutId(): Int {
        return  R.layout.fragment_wound_characteristics
    }

    var count = 0
    var isAntimicrobial = false
    var isCharacteristics = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarText(getString(R.string.wound_characteristics))
        count = 0
        isAntimicrobial = false
        isCharacteristics = false
        initiateClickListeners()
        setCheckboxAdapter()
    }

    private fun setCheckboxAdapter() {
//        rvCheckbox.layoutManager = LinearLayoutManager()
    }

    private fun initiateClickListeners() {

        linearLayoutNext.setOnClickListener (this)
        btnYes.setOnClickListener (this)
        btnNo.setOnClickListener (this)
        btnNoLastVisit.setOnClickListener (this)
        btnYesLastVisit.setOnClickListener (this)
        linearLayoutBack.setOnClickListener (this)
        rbOrder.setOnCheckedChangeListener(this)
        rbOther.setOnCheckedChangeListener(this)
        rbPain.setOnCheckedChangeListener(this)
        rbRedness.setOnCheckedChangeListener(this)
        rvSignificant.setOnCheckedChangeListener(this)
        rbTissue.setOnCheckedChangeListener(this)
    }

    override fun onClick(v: View?) {
        when(v)
        {
            linearLayoutNext ->{
                navigateToFragment(R.id.action_woundCharacteristicsFragment_to_treatmentPlanFragment, null)
            }
            linearLayoutBack ->{
                (activity as MainActivity).startAnimation()
                activity?.onBackPressed()
            }

            btnNo ->{

                isAntimicrobial = true
                if(isCharacteristics)
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
                isAntimicrobial = true
                if(isCharacteristics)
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

            btnNoLastVisit ->{
                llCharacteristics.visible()
                isCharacteristics = true
                if(isAntimicrobial)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnNoLastVisit.setBackgroundResource(R.drawable.button_purple_background)
                btnYesLastVisit.setBackgroundResource(R.drawable.button_outline_grey)
            }
            btnYesLastVisit ->{
                llCharacteristics.gone()
                isCharacteristics = true
                if(isAntimicrobial)
                {
                    linearLayoutNext.setTextColor(resources.getColor(R.color.colorWhite))
                    linearLayoutNext.isEnabled = true
                }
                else{
                    linearLayoutNext.setTextColor(resources.getColor(R.color.transparent_white))
                    linearLayoutNext.isEnabled = false
                }
                btnNoLastVisit.setBackgroundResource(R.drawable.button_outline_grey)
                btnYesLastVisit.setBackgroundResource(R.drawable.button_purple_background)
            }
        }
    }

    override fun onCheckedChanged(button: CompoundButton, isChecked: Boolean) {


//        else if(count < 4)
//        {
//            button.isChecked = true
//            count = count + 1
//        }
//        else{
//            button.isChecked = false
//            count = count - 1
//        }
       when(button.id)
       {

           R.id.rbOrder ->{

               if(isChecked)
               {

                   if(count == 4)
                   {
                       showAlertDialog("You can select only 4 characteristics")
                       button.isChecked = false
                       return
                   }
                   else
                   {
                       button.isChecked = true
                       count = count + 1
                   }

               }
               else{
                   button.isChecked = false
                   count = count - 1
               }
           }
           R.id.rbTissue ->{
               if(isChecked)
               {

                   if(count == 4)
                   {
                       showAlertDialog("You can select only 4 characteristics")
                       button.isChecked = false
                       return
                   }
                   else
                   {
                       button.isChecked = true
                       count = count + 1
                   }

               }
               else{
                   button.isChecked = false
                   count = count - 1
               }
           }
           R.id.rvSignificant ->{
               if(isChecked)
               {

                   if(count == 4)
                   {
                       showAlertDialog("You can select only 4 characteristics")
                       button.isChecked = false
                       return
                   }
                   else
                   {
                       button.isChecked = true
                       count = count + 1
                   }

               }
               else{
                   button.isChecked = false
                   count = count - 1
               }
           }

           R.id.rbOther ->{
              if(isChecked)
               {

                   if(count == 4)
                   {
                       showAlertDialog("You can select only 4 characteristics")
                       button.isChecked = false
                       return
                   }
                   else
                   {
                       button.isChecked = true
                       count = count + 1
                   }

               }
               else{
                   button.isChecked = false
                   count = count - 1
               }
           }
           R.id.rbPain ->{
               if(isChecked)
               {

                   if(count == 4)
                   {
                       showAlertDialog("You can select only 4 characteristics")
                       button.isChecked = false
                       return
                   }
                   else
                   {
                       button.isChecked = true
                       count = count + 1
                   }

               }
               else{
                   button.isChecked = false
                   count = count - 1
               }
           }
           R.id.rbRedness ->{
               if(isChecked)
               {

                   if(count == 4)
                   {
                       showAlertDialog("You can select only 4 characteristics")
                       button.isChecked = false
                       return
                   }
                   else
                   {
                       button.isChecked = true
                       count = count + 1
                   }

               }
               else{
                   button.isChecked = false
                   count = count - 1
               }
           }

       }


    }

}