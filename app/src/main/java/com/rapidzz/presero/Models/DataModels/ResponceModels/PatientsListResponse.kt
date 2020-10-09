package com.rapidzz.presero.Models.DataModels.ResponceModels


import com.rapidzz.presero.Models.DataModels.GeneralModels.OperatorsModel
import com.rapidzz.presero.Models.DataModels.GeneralModels.PatientsModel
import com.rapidzz.presero.Models.DataModels.GeneralModels.User
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

data class PatientsListResponse(

    val result: ArrayList<PatientsModel>
): BaseResponse(), Serializable