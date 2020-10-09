package com.rapidzz.presero.Models.DataModels.ResponceModels

import com.rapidzz.presero.Models.DataModels.GeneralModels.BucketModel
import com.rapidzz.presero.Models.DataModels.UtilityModels.BaseResponse
import java.io.Serializable

class GetAllBucketResponse (
    val result : ArrayList<BucketModel>
):Serializable,BaseResponse()