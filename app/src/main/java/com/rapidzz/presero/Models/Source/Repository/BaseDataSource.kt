package com.rapidzz.presero.Models.DataSource

import com.rapidzz.presero.Models.DataModels.UtilityModels.ApiErrorResponse


interface BaseDataSource {
    fun onPayloadError(error: ApiErrorResponse)
}
