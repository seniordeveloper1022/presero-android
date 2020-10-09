package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DDItem(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String
) : Serializable {
    override fun toString(): String {
        return this.name
    }
}