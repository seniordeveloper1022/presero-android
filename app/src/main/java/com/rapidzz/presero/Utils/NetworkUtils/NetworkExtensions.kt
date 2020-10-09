package com.rapidzz.presero.Utils.NetworkUtils

import android.content.Context
import android.net.ConnectivityManager
import com.rapidzz.presero.Models.DataModels.UtilityModels.ApiErrorResponse
import com.rapidzz.presero.Utils.GeneralUtils.ErrorUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): ApiErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {
            return ErrorUtils.parseError(it.string())
        }
    } catch (exception: Exception) {
        null
    }
}


fun isOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    //should check null because in airplane mode it will be null
    return netInfo != null && netInfo.isConnected
}