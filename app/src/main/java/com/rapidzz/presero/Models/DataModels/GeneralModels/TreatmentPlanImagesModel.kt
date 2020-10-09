package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TreatmentPlanImagesModel(

    var name :  String,
    var image :  Int,
    var isSelected : Boolean
) : Serializable