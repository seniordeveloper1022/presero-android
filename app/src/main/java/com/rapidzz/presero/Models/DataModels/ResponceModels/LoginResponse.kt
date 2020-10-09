package com.rapidzz.presero.Models.DataModels.ResponceModels


import com.rapidzz.presero.Models.DataModels.GeneralModels.User
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

data class LoginResponse(

    val result: User
): BaseResponse(), Serializable