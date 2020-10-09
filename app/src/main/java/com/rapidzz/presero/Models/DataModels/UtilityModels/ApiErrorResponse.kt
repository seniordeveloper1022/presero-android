package com.rapidzz.presero.Models.DataModels.UtilityModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiErrorResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("codeNumber")
    val codeNumber: Int,
    @SerializedName("recordCount")
    val recordCount: Int,
    @SerializedName("error")
    val error: String,
    @SerializedName("IsSuccessful")
    val IsSuccessful: Boolean
) : Serializable {

}