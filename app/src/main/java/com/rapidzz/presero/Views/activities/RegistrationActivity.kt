package com.rapidzz.presero.Views.activities

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.gone
import com.rapidzz.presero.Utils.Application.visible
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.REQUEST_CODES
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_registration
    }

    override fun initViews() {
//        toolbar.setNavigationOnClickListener {
//            onBackPressed()
//        }
//        getToken()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(REQUEST_CODES.contains(requestCode)) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val childFragments = navHostFragment?.childFragmentManager?.fragments
            childFragments?.forEach { it.onActivityResult(requestCode, resultCode, data) }
        }
    }




//    private fun getToken()
//    {
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
//            sessionManager.setFCMToken(it.token)
//        }
//    }




//    fun hideToolbar(isHide:Boolean)
//    {
//        if(isHide)
//        {
//            toolbar.gone()
//        }
//        else
//        {
//            toolbar.visible()
//        }
//    }
}
