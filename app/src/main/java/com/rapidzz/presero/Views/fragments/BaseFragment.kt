package com.rapidzz.presero.Views.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.afollestad.vvalidator.form
import com.rapidzz.presero.Models.Source.Repository.UserDataSource
import com.rapidzz.presero.R
import com.rapidzz.presero.Utils.Application.gone
import com.rapidzz.presero.Utils.Application.showAlertDialog
import com.rapidzz.presero.Utils.Application.showToast
import com.rapidzz.presero.Utils.Application.visible
import com.rapidzz.presero.Utils.GeneralUtils.DialogUtils
import com.rapidzz.presero.Utils.GeneralUtils.SessionManager
import com.rapidzz.presero.ViewModels.BaseAndroidViewModel
import com.rapidzz.presero.Views.activities.MainActivity
import com.rapidzz.presero.Views.activities.RegistrationActivity
import kotlinx.android.synthetic.main.app_bar_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
abstract class BaseFragment : Fragment() {
    lateinit var dialog: AlertDialog
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogUtils.getProgressDialog(requireContext())
        sessionManager = SessionManager(requireContext())
    }
    abstract fun initViews()
    abstract fun attachViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        attachViewModel()
        if(activity is MainActivity)
        {
            if(this is HomeFragment)
            {
                (activity as MainActivity).ivBack.gone()
                (activity as MainActivity).ivHideSearchBar.gone()
                (activity as MainActivity).etSearch.gone()
                (activity as MainActivity).ivMenu.visible()
                (activity as MainActivity).linearLayoutSearchIcon.visible()
                (activity as MainActivity).ivOptionMenu.gone()
            }
            else if(this is PatientWoundsFragment)
            {
                (activity as MainActivity).ivBack.visible()
                (activity as MainActivity).ivMenu.gone()
                (activity as MainActivity).etSearch.gone()
                (activity as MainActivity).ivHideSearchBar.gone()
                (activity as MainActivity).linearLayoutSearchIcon.gone()
                (activity as MainActivity).ivOptionMenu.visible()
            }
            else{
                (activity as MainActivity).ivBack.visible()
                (activity as MainActivity).ivHideSearchBar.gone()
                (activity as MainActivity).ivMenu.gone()
                (activity as MainActivity).etSearch.gone()
                (activity as MainActivity).linearLayoutSearchIcon.gone()
                (activity as MainActivity).ivOptionMenu.gone()
            }
        }
    }
    abstract fun getLayoutId():Int
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
    fun applyValidations(
        viewList: ArrayList<View>,
        buttonView: Button,
        callback: UserDataSource.OnValidationCallback
    ) {
        form {
            for (view in viewList) {
                if (view is EditText) {
                    input(view) {
                        isNotEmpty().description(getString(R.string.field_req))
                    }
                } else if (view is Spinner) {
                    spinner(view) {
                        selection().greaterThan(0)
                        onErrors { view, errors ->
                            if (errors.isNotEmpty()) {
                                showToast("Some selections are missing")
                            }
                        }
                    }
                }
            }
            submitWith(buttonView) { result ->
                var results: ArrayList<String> = ArrayList()
                for (value in result.values()) {
                    results.add(value.asString())
                }
                callback.onApplied(true, results)
            }
        }
    }
    fun navigateToFragment(action: Int, bundle: Bundle?) {
        if(activity is RegistrationActivity) {
            val navController = Navigation.findNavController(
                activity as RegistrationActivity,
                R.id.nav_host_fragment
            )
            navController.navigate(action, bundle)
        }
        else {
            val navController = Navigation.findNavController(
                activity as MainActivity,
                R.id.nav_host_fragment
            )
            navController.navigate(action, bundle)
        }
    }
    protected fun setupGeneralViewModel(generalViewModel: BaseAndroidViewModel)
    {
        with(generalViewModel)
        {
            snackbarMessage.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    showAlertDialog(it)
                }
            })
            progressBar.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    showProgressDialog(it)
                }
            })
            isUnauthorized?.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    SessionManager(requireContext()).logout()
                    var intent = Intent(context, RegistrationActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            })
        }
    }
    open fun changeDateFormat(time: String?): String? {
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val outputPattern = "mm/dd/yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}