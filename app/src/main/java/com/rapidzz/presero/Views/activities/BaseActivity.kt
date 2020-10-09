package com.rapidzz.presero.Views.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rapidzz.presero.Utils.Application.openActivityWithExist
import com.rapidzz.presero.Utils.GeneralUtils.DialogUtils
import com.rapidzz.presero.Utils.GeneralUtils.SessionManager

abstract class BaseActivity : AppCompatActivity() {

    lateinit var sessionManager: SessionManager
    lateinit var dialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.MODE_NIGHT_YES
        setContentView(getLayoutId())
        dialog = DialogUtils.getProgressDialog(this)
        sessionManager = SessionManager(this)
        initViews()
    }




    abstract fun getLayoutId():Int
    abstract fun initViews()




    fun gotoMainActivity()
    {
        openActivityWithExist(MainActivity::class.java)

    }


    fun gotoRegActivity()
    {
        openActivityWithExist(RegistrationActivity::class.java)
    }




    fun showProgressDialog(show: Boolean) {

        if (dialog != null && show) {
            if (!dialog.isShowing)
                dialog.apply {
                    show()
                }
        } else if (dialog != null && !show) {
            if (dialog.isShowing)
                dialog.dismiss()
        }
    }

}
