package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PatientListModel(

    var name :  String,
    var id :  String,
    var date_of_birth :  String,
    var last_scan :  String,
    var operator :  String
) : Serializable