package com.matheus.cophat.data.local.entity

data class Respondent(
    var motherProfession: String? = null,
    var fatherProfession: String? = null,
    var maritalStatus: String? = null,
    var religion: String? = null,
    var patientName: String? = null,
    var medicalRecords: String? = null,
    var birthDate: String? = null,
    var age: Int? = null,
    var gender: String? = null,
    var diagnosis: String? = null,
    var diagnosticTime: String? = null,
    var internedDays: Int? = null,
    var hospitalizations: Int? = null,
    var schooling: String? = null,
    var schoolFrequency: String? = null,
    var liveInThisCity: String? = null,
    var home: String? = null,
    var monthlyIncome: Float? = null,
    var educationDegree: String? = null
)