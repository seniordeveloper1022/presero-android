package com.rapidzz.presero.Models.DataModels.ResponceModels


import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

data class GeneralStringResponse(var result: String
): BaseResponse(), Serializable {

}