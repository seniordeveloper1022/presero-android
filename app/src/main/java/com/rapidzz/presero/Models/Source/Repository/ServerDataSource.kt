package com.rapidzz.presero.Models.Source.Repository


import com.rapidzz.presero.Models.DataModels.ResponceModels.LoginResponse
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import com.rapidzz.presero.Models.DataSource.BaseDataSource

interface ServerDataSource {


    interface GetGeneralResponseCallback: BaseDataSource {
        fun onSuccess(baseResponse: BaseResponse)
    }


    interface LoginCallback:BaseDataSource{
        fun onLogin(login: LoginResponse)

    }



}