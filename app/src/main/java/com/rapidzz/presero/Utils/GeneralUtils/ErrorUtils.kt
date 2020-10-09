package com.rapidzz.presero.Utils.GeneralUtils

import android.util.Log
import com.rapidzz.presero.Models.DataModels.UtilityModels.ApiErrorResponse
import org.json.JSONObject

object ErrorUtils {
    fun parseError(apiError: String): ApiErrorResponse {
        Log.d("apierror",apiError)
        try {
            val json = JSONObject(apiError)
            val error = ApiErrorResponse(
                    json.optInt("code", 0),
                    json.optString("message", ""),
                    json.optInt("codeNumber", 0),
                    json.optInt("recordCount", 0),
                    json.optString("error", ""),
                    json.optBoolean("IsSuccessful", false)
                )
            return error
        }catch (ex: Exception){
            ex.printStackTrace()
            return ApiErrorResponse(
                0,
                ""
            , 0,0, "", false
            )
        }
    }



    fun parseError(t: Throwable): ApiErrorResponse {
        try {
            return ApiErrorResponse(
                0,
                t.message!!, 0,0, "", false
            )
        }catch (ex: Exception){
            ex.printStackTrace()
            return ApiErrorResponse(
                0,
                "", 0,0, "", false
            )
        }
    }
}