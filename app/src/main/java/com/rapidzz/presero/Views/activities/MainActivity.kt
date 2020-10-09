package com.rapidzz.presero.Views.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.*
import com.rapidzz.presero.ViewModels.ProfileViewModel
import com.rapidzz.presero.Views.adapters.UserItemMenuAdapter
import com.rapidzz.presero.Views.dialog.ConfirmationDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.navigation_layout.*


class MainActivity : BaseActivity(),UserItemMenuAdapter.NavItemClickListner, View.OnClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var toolbar: Toolbar ?= null
    var navController:NavController ?= null
    var needBackMove:Boolean = false
    var viewModel:ProfileViewModel ?=null
//    var  layout : View ? = null
    var llDocument : LinearLayout ? = null
    var isSearchBarVisible = false


    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }


    override fun onclicked(position: Int) {
        when(position)
        {
            0 -> {
                navController!!.navigate(R.id.homeFragment)
            }
            1 -> {
                showToast("In process")
//                navController!!.navigate(R.id.changePasswordFragment)
            }
            2 -> {
                showToast("In process")
            }
            3 -> {
                showToast("In process")
            }
            4 ->{
                navController!!.navigate(R.id.settingsFragment)
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
    }




    
    override fun initViews()
    {
        etSearch.gone()
        ivHideSearchBar.gone()
        loadNavigationMenu()
        val navView: NavigationView = findViewById(R.id.nav_view)
         navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController!!.graph, drawer_layout)
        navView.setupWithNavController(navController!!)
        initiateClickListener()
    }

    private fun initiateClickListener() {
        ivBack.setOnClickListener(this)
        ivMenu.setOnClickListener(this)
        ivOptionMenu.setOnClickListener(this)
        llDocument?.setOnClickListener(this)
        llLogout.setOnClickListener(this)
        ivSearch.setOnClickListener(this)
        ivHideSearchBar.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setToolbarText(string: String)
    {
        tvToolbar.text = string
    }

    private fun loadNavigationMenu() {
        var adapter = UserItemMenuAdapter(this)
        rlItemsMain?.let {
            it.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            it.adapter = adapter
        }

    }

    private fun logout()
    {

        ConfirmationDialog("Are you sure to logout from app ?",
            object : ConfirmationDialog.ConfirmationListener {
                override fun isConfirmed(isConfirmed: Boolean) {
                    if (isConfirmed) {
                        sessionManager.logout()
                        val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
//                    gotoRegActivity()
//                    viewModel?.userLogout()
                    }
                }
            }).show(supportFragmentManager, "")

    }

    override fun onClick(p0: View?) {
        when(p0)
        {
            ivMenu -> {
                drawer_layout.openDrawer(GravityCompat.START)
            }
            ivBack -> {
                startAnimation()
                onBackPressed()
            }

            ivOptionMenu -> {
                val inflater =
                    applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val v: View = inflater.inflate(R.layout.option_menu_layout, null)

                var popupWindow = PopupWindow(
                    v,
                    300,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    true
                )

                popupWindow.showAsDropDown(p0, 0, 0)

                val document = v.findViewById(R.id.llDocument) as LinearLayout
                document.setOnClickListener {
                    navController!!.navigate(R.id.treatmentPlanHomeFragment)
                    popupWindow.dismiss()
                }
            }
//            llDocument -> {
//                showToast("In process")
//            }


            llLogout -> {
                logout()
            }

            ivSearch -> {
                etSearch.visible()
                ivHideSearchBar.visible()
                linearLayoutSearchIcon.gone()
            }
            ivHideSearchBar -> {
                p0?.hideKeyboard()
                etSearch.setText("")
                linearLayoutSearchIcon.visible()
                etSearch.gone()
                ivHideSearchBar.gone()

            }

        }

    }


    fun startAnimation()
    {
        val enterFromRight = AnimationUtils.loadAnimation(this, R.anim.enter_from_right)
        contentMain.startAnimation(enterFromRight)
    }
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val childFragments = navHostFragment?.childFragmentManager?.fragments
        childFragments?.forEach { it.onActivityResult(requestCode, resultCode, data) }

    }



}
