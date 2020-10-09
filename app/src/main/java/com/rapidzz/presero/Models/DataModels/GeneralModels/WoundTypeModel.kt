package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable

data class WoundTypeModel(
    val id: String,
    val image: String,
    val name: String,
    var isSelected : Boolean = false
):Serializable {
    override fun toString(): String {
        return name
    }
}