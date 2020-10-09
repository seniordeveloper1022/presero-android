package com.rapidzz.presero.Models.Source.Repository

import android.content.Context
import com.rapidzz.presero.Models.DataModels.ResponceModels.*

import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import com.rapidzz.presero.Utils.Application.*

import com.rapidzz.presero.Utils.NetworkUtils.ResultWrapper
import com.rapidzz.presero.Utils.NetworkUtils.safeApiCall


class DataRepository(ctx: Context) : BaseRepository(ctx) {


    suspend fun userLogin(
        type: String,
        preseroAppVersion: String,
        deviceID: String,
        accountID: String,
        facilityID: String
    ): ResultWrapper<LoginResponse> {
        return safeApiCall(dispatcher) {
            getApiService().loginUser(type, preseroAppVersion, deviceID, accountID, facilityID)
        }
    }

suspend fun getOperatorsList(
        type: String,
        token: String
    ): ResultWrapper<OperatorsListResponse> {
        return safeApiCall(dispatcher) {
            getApiService().getOperatorsList(type, token)
        }
    }

    suspend fun getPatientsList(
        type: String,
        token: String
    ): ResultWrapper<PatientsListResponse> {
        return safeApiCall(dispatcher) {
            getApiService().getPatientsList(type, token)
        }
    }

    suspend fun getPatientDetail(
        type: String,
        userkey: String
    ): ResultWrapper<PatientsListResponse> {
        return safeApiCall(dispatcher) {
            getApiService().getPatientDetail(type, userkey)
        }
    }
    suspend fun getEncryptedPassword(
        type: String,
        password: String
    ): ResultWrapper<GetEncryptedPasswordResponse> {
        return safeApiCall(dispatcher) {
            getApiService().getEncryptedPassword(type, password)
        }
    }



    suspend fun logoutUser(): ResultWrapper<BaseResponse> {
        return safeApiCall(dispatcher) {
            getApiService().userLogout(userId)
        }
    }



    suspend fun getBucket(
        type: String
    ): ResultWrapper<GetAllBucketResponse> {
        return safeApiCall(dispatcher) {
            getApiService().getBucketData(type)
        }
    }
    suspend fun getWoundType(
        token: String
    ): ResultWrapper<WoundTypeResponse> {
        return safeApiCall(dispatcher) {
            getApiService().getWoundType(WOUND_LISTING,token)
        }
    }
    suspend fun addOperator(
        token: String,
        new_operator: String
    ): ResultWrapper<BaseResponse> {
        return safeApiCall(dispatcher) {
            getApiService().addOperator(new_operator,AddOperator,token)
        }
    }

    suspend fun deleteOperator(
        token: String,
        delete_operator: String
    ): ResultWrapper<BaseResponse> {
        return safeApiCall(dispatcher) {
            getApiService().deleteOperator(delete_operator, DeleteOperator,token)
        }
    }
    suspend fun updateOperator(
        token: String,
        update_operator: String
    ): ResultWrapper<BaseResponse> {
        return safeApiCall(dispatcher) {
            getApiService().updateOperator(update_operator,UpdateOperator,token)
        }
    }
    suspend fun updatePatient(
        token: String,
        update_patient: String
    ): ResultWrapper<BaseResponse> {
        return safeApiCall(dispatcher) {
            getApiService().updatePatient(update_patient, UpdatePatient,token)
        }
    }

}