package com.rapidzz.presero.ViewModels


import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acclivousbyte.gobblecustomer.Utils.GeneralUtils.OneShotEvent
import com.rapidzz.presero.Models.DataModels.GeneralModels.User
import com.rapidzz.presero.Models.DataModels.ResponceModels.*
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import com.rapidzz.presero.Models.Source.Repository.DataRepository
import com.rapidzz.presero.Utils.Application.BUCKET_DATA
import com.rapidzz.presero.Utils.GeneralUtils.SessionManager
import com.rapidzz.presero.Utils.NetworkUtils.ResultWrapper
import kotlinx.coroutines.launch


class ProfileViewModel(private val dataRepositery: DataRepository) :
    BaseAndroidViewModel() {


    //Signup , Login , Profile
    var userLiveData: MutableLiveData<OneShotEvent<User>> = MutableLiveData()
    var getOperatorsListResponse: MutableLiveData<OneShotEvent<OperatorsListResponse>> =
        MutableLiveData()
    var getpatientsListResponse: MutableLiveData<OneShotEvent<PatientsListResponse>> =
        MutableLiveData()
    var getPatientDetailResponse: MutableLiveData<OneShotEvent<PatientsListResponse>> =
        MutableLiveData()
    var getEncryptedPasswordResponse: MutableLiveData<OneShotEvent<GetEncryptedPasswordResponse>> =
        MutableLiveData()
    var updatePasswordLiveData: MutableLiveData<OneShotEvent<Boolean>> = MutableLiveData()
    var changePasswordLiveData: MutableLiveData<OneShotEvent<Boolean>> = MutableLiveData()
    var userLogoutLiveData: MutableLiveData<OneShotEvent<Boolean>> = MutableLiveData()
    var getBucketDataResponse: MutableLiveData<OneShotEvent<GetAllBucketResponse>> = MutableLiveData()
    var getWoundTypesResponse: MutableLiveData<OneShotEvent<WoundTypeResponse>> = MutableLiveData()
    var generalResponse: MutableLiveData<OneShotEvent<BaseResponse>> = MutableLiveData()


    fun loginUser(
        type: String,
        preseroAppVersion: String,
        deviceID: String,
        accountID: String,
        facilityID: String
    ) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.userLogin(type, preseroAppVersion, deviceID, accountID, facilityID)
                ?.let { response ->
                    showProgressBar(false)
                    when (response) {
                        is ResultWrapper.Success ->
                            if (isSuccess(response.value)) {
                                userLiveData.value = OneShotEvent(response.value.result)
                            }
                        else -> handleErrorType(response)
                    }
                }

        }

    }

    fun getOperatorsList(
        type: String,
        token: String
    ) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.getOperatorsList(type, token)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            getOperatorsListResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }

    fun getPatientsList(
        type: String,
        token: String
    ) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.getPatientsList(type, token)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            getpatientsListResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }

    fun getPatientDetail(
        type: String,
        userkey: String
    ) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.getPatientDetail(type, userkey)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            getPatientDetailResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }

    fun getEncryptedPassword(
        type: String,
        password: String
    ) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.getEncryptedPassword(type, password)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            getEncryptedPasswordResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }


    fun getBucket() {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.getBucket(BUCKET_DATA)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            getBucketDataResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }

    fun getWoundTypes(token: String) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.getWoundType(token)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            getWoundTypesResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }


    fun userLogout() {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.logoutUser()?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            userLogoutLiveData.value = OneShotEvent(true)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }
    fun addOperator(token: String,new_operator:String) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.addOperator(token, new_operator)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            generalResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }

    fun deleteOperator(token: String,delete_operator:String) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.deleteOperator(token, delete_operator)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            generalResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }
    fun updateOperator(token: String,updateOperator:String) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.updateOperator(token, updateOperator)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            generalResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }
    fun updatePatient(token: String,updatePatient:String) {
        showProgressBar(true)
        viewModelScope.launch {
            dataRepositery.updatePatient(token, updatePatient)?.let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (isSuccess(response.value)) {
                            generalResponse.value = OneShotEvent(response.value)
                        }
                    else -> handleErrorType(response)
                }
            }

        }

    }

}