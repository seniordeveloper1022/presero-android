package com.rapidzz.presero.Models.DataModels.ResponceModels


import com.rapidzz.presero.Models.DataModels.GeneralModels.DDItem
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

data class QualificationsResponse(val detail: ArrayList<DDItem>
): BaseResponse(), Serializable