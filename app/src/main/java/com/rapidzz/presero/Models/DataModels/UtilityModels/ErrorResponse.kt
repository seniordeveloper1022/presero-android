package com.rapidzz.presero.Models.DataModels.UtilityModels

import java.io.Serializable

class ErrorResponse(
    val IsSuccessful: Boolean ,
    val codeNumber: Int,
    val error: String ,
    val recordCount: Int ,
    var message: String,
    var code: Int
) : Serializable {

}