package com.rapidzz.presero.Views.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.rapidzz.presero.R


abstract class BaseDialog(): DialogFragment() {


    @SuppressLint("ResourceAsColor")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.GeneralDialogStyle);
        val builder = AlertDialog.Builder(requireContext(), R.style.GeneralDialogStyle)
        val inflater = activity!!.layoutInflater

        val dialog = inflater.inflate(getLayoutId(), null)
        initViews(dialog)
        builder.setView(dialog)
        return builder.create()
    }






    abstract fun initViews(view:View)

    abstract fun getLayoutId():Int

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val windowParams = window?.attributes
        windowParams?.dimAmount = 0.8f
        window?.attributes = windowParams
    }




}