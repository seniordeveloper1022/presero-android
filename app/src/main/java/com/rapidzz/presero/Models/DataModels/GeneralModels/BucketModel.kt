package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable

data class BucketModel(
    val ETag: String,
    val Key: String,
    val LastModified: String,
    val Owner: Owner,
    val Size: String,
    val StorageClass: String
):Serializable