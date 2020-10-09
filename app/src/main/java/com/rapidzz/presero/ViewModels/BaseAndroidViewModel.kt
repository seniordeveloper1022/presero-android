package com.rapidzz.presero.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acclivousbyte.gobblecustomer.Utils.GeneralUtils.OneShotEvent
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import com.rapidzz.presero.Models.DataModels.UtilityModels.ErrorResponse
import com.rapidzz.presero.Utils.NetworkUtils.ResultWrapper


open class BaseAndroidViewModel() : ViewModel() {


    val snackbarMessage = MutableLiveData<OneShotEvent<String>>()
    val errorResponse = MutableLiveData<OneShotEvent<ErrorResponse>>()
    val progressBar = MutableLiveData<OneShotEvent<Boolean>>()
    val loader = MutableLiveData<OneShotEvent<Boolean>>()
    val isUnauthorized = MutableLiveData<OneShotEvent<Boolean>>()





    protected fun showSnackbarMessage(message: String) {
        snackbarMessage.value = OneShotEvent(message)
    }

    protected fun showNetworkError() {
        snackbarMessage.value = OneShotEvent("Internet connection problem")
    }

    protected fun handleErrorType(error:ResultWrapper<Any>) {
        when (error) {
            is ResultWrapper.NetworkError ->
                showNetworkError()
            is ResultWrapper.GenericError ->
                showSnackbarMessage("" + error.error?.message)
        }
    }

    protected fun isSuccess(result:BaseResponse):Boolean {
        if(result.code==200)
        {
            return true
        }
        else if(result.code==401)
        {
            isUnauthorized.value=OneShotEvent(true)
            return false
        }
        else
        {
            showSnackbarMessage(result.message)
            return false
        }
    }



    protected fun showProgressBar(show: Boolean) {
        progressBar.value = OneShotEvent(show)
    }


}