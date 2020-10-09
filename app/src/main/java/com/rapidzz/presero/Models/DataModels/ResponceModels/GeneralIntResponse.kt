package com.rapidzz.presero.Models.DataModels.ResponceModels


import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

data class GeneralIntResponse(val result: Int
) : BaseResponse(), Serializable {

}