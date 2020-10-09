package com.rapidzz.presero.Models.DataModels.GeneralModels

import java.io.Serializable

data class PatientsModel(
    val AdmissionDate: String,
    val BirthDate: String,
    val Comments: Any,
    val Facility: String,
    val ID: String,
    val IsActive: Boolean,
    val IsTransient: Any,
    val LastClientUpdate: String,
    val LastScanDate: String,
    val OperatorId: String,
    val OperatorName: String,
    val OriginalPatientId: Int,
    val PatientEndDate: String,
    val PatientId: String,
    val PatientLastName: String,
    val PatientName: String,
    val PatientType: Int,
    val accountID: String,
    var patientDetailId: String
):Serializable