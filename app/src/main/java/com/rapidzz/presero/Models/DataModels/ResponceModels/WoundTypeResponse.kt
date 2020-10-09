package com.rapidzz.presero.Models.DataModels.ResponceModels

import com.rapidzz.presero.Models.DataModels.GeneralModels.WoundTypeModel
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

data class WoundTypeResponse (
    var result : ArrayList<WoundTypeModel>
):Serializable,BaseResponse()