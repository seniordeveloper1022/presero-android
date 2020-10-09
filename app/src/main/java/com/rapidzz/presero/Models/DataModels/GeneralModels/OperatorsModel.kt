package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable

data class OperatorsModel(
    var Deleted: Boolean,
    var Email: String,
    var EncryptedPassword: String,
    var FirstName: String,
    var Id: String,
    var IsAdmin: Boolean,
    var IsPersistent: Boolean,
    var LastClientUpdate: String,
    var LastName: String,
    var LoginName: String,
    var MiddleName: String,
    var PasswordLastChanged: String,
    var ShowTips: Boolean,
    var WindowsId: String,
    var accountId: String,
    var isSelected : Boolean
): Serializable