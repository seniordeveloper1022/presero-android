package com.rapidzz.presero.Views.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmationDialog(message: String, confirmListener: ConfirmationListener) :
    DialogFragment() {

    var message: String = ""
    var btnText: String = "Ok"
    var confirmListener: ConfirmationListener? = null


    init {
        this.message = message
        this.confirmListener = confirmListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            builder
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton("Yes") { dialog, id ->
                    dialog.dismiss()
                    confirmListener!!.isConfirmed(true)
                }
                .setCancelable(false)
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                    confirmListener!!.isConfirmed(false)
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    interface ConfirmationListener {
        fun isConfirmed(isConfirmed: Boolean)

    }
}