package com.rapidzz.presero.Views.activities

import android.os.Handler
import com.rapidzz.presero.R

class SplashActivity : BaseActivity() {
    companion object {
        val SPLASH_DELAY: Long = 3000
    }


    override fun initViews() {

        Handler().postDelayed(Runnable {
            if(sessionManager.isLoggedIn()) {
                gotoMainActivity()
            }
            else {
                gotoRegActivity()
            }
        }, SPLASH_DELAY)
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_splash
    }
}
