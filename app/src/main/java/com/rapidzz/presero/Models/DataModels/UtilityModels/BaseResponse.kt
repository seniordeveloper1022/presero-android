package com.rapidzz.presero.Models.DataModels.UtilityModels

import java.io.Serializable

open class BaseResponse(
    val IsSuccessful: Boolean = false,
    val code: Int = 0,
    val codeNumber: Int = 0,
    val error: String = "",
    val message: String = "",
    val recordCount: Int = 0
) : Serializable


