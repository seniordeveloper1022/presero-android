package com.rapidzz.presero.Models.Source.Repository

import android.content.Context
import com.rapidzz.presero.Models.DataSource.BaseDataSource
import com.rapidzz.presero.Models.Source.ServerConnection.ApiService
import com.rapidzz.presero.Models.Source.ServerConnection.RetrofitClientInstance
import com.rapidzz.presero.Utils.GeneralUtils.ErrorUtils

import com.google.gson.Gson
import com.rapidzz.presero.Utils.GeneralUtils.SessionManager
import com.rapidzz.presero.Utils.NetworkUtils.isOnline
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


open class BaseRepository(ctx: Context) {

    var context: Context
    var sessionManager: SessionManager? = null
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
    var userId:String =""

    var gson = Gson()

    init {
        context = ctx
        sessionManager = SessionManager(context)
        userId=sessionManager!!.getUserId()

    }




    fun getApiService(): ApiService {
        return RetrofitClientInstance.getInstance(context)!!.getService()
    }





    fun checkInternetConnection(callback: BaseDataSource): Boolean {
        if (!isOnline(context)) {
            callback.onPayloadError(
                ErrorUtils.parseError(
                    "{\"message\":\"Please check internet connection and try again\",\"code\":\"0\",\"name\":\"error\"}"
                )
            )
            return true
        }
        return false
    }







}