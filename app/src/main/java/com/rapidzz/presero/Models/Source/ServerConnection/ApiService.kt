package com.rapidzz.presero.Models.Source.ServerConnection


import com.rapidzz.presero.Models.DataModels.ResponceModels.*
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.CHANGE_PASSWORD
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.GENERAL_API
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.GET_BUCKET_DATA
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.GET_ENCRYPTED_PASSWORD_API
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.GET_OPERATORS_LIST_API
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.GET_PROFILE
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.LOGOUT_API
import com.rapidzz.presero.Utils.GeneralUtils.AppConstants.Companion.SIGN_IN_API
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

@JvmSuppressWildcards
interface ApiService {


    @FormUrlEncoded
    @POST(SIGN_IN_API)
    suspend fun loginUser(
        @Field(AppConstants.TYPE) type: String,
        @Field(AppConstants.APP_VERSION) preseroAppVersion: String,
        @Field(AppConstants.DEVICE_ID) deviceID: String,
        @Field(AppConstants.ACCOUNT_ID) accountID: String,
        @Field(AppConstants.FACILITY_ID) facilityID: String

    ): LoginResponse


    @FormUrlEncoded
    @POST(GET_OPERATORS_LIST_API)
    suspend fun getOperatorsList(
        @Field(AppConstants.TYPE) type: String,
        @Field(AppConstants.TOKEN) token: String

    ): OperatorsListResponse

    @FormUrlEncoded
    @POST(GET_OPERATORS_LIST_API)
    suspend fun getPatientsList(
        @Field(AppConstants.TYPE) type: String,
        @Field(AppConstants.TOKEN) token: String

    ): PatientsListResponse

    @FormUrlEncoded
    @POST(GET_OPERATORS_LIST_API)
    suspend fun getPatientDetail(
        @Field(AppConstants.TYPE) type: String,
        @Field(AppConstants.USER_KEY) userkey: String

    ): PatientsListResponse


    @FormUrlEncoded
    @POST(GET_BUCKET_DATA)
    suspend fun getBucketData(
        @Field("type") type: String
    ): GetAllBucketResponse

    @FormUrlEncoded
    @POST(GET_ENCRYPTED_PASSWORD_API)
    suspend fun getEncryptedPassword(
        @Field(AppConstants.TYPE) type: String,
        @Field(AppConstants.PASSWORD) password: String

    ): GetEncryptedPasswordResponse




    @FormUrlEncoded
    @POST(LOGOUT_API)
    suspend fun userLogout(@Field("user_id") user_id: String): BaseResponse


    @FormUrlEncoded
    @POST(CHANGE_PASSWORD)
    suspend fun changePassword(
        @Field("user_id") user_id: String,
        @Field("current_password") current_password: String,
        @Field("new_password") new_password: String
    ): BaseResponse


    @FormUrlEncoded
    @POST(GENERAL_API)
    suspend fun getWoundType(
        @Field("type") type: String,
        @Field("token") token: String
    ): WoundTypeResponse

    @FormUrlEncoded
    @POST(GENERAL_API)
    suspend fun addOperator(
        @Field("new_operator") new_operator: String,
        @Field("type") type: String,
        @Field("token") token: String
    ): BaseResponse

    @FormUrlEncoded
    @POST(GENERAL_API)
    suspend fun deleteOperator(
        @Field("delete_operator") delete_operator: String,
        @Field("type") type: String,
        @Field("token") token: String
    ): BaseResponse

    @FormUrlEncoded
    @POST(GENERAL_API)
    suspend fun updateOperator(
        @Field("update_operator") update_operator: String,
        @Field("type") type: String,
        @Field("token") token: String
    ): BaseResponse

    @FormUrlEncoded
    @POST(GENERAL_API)
    suspend fun updatePatient(
        @Field("update_patient") update_patient: String,
        @Field("type") type: String,
        @Field("token") token: String
    ): BaseResponse

}