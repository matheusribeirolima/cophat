package com.matheus.cophat.data.local.entity

import java.util.*

data class Respondent(
    var code: String? = null,
    var motherProfession: String? = null,
    var fatherProfession: String? = null,
    var maritalStatus: MaritalStatus? = null,
    var religion: Religion? = null,
    var patientName: String? = null,
    var medicalRecords: String? = null,
    var birthDate: Date? = null,
    var age: Int? = null,
    var gender: Gender? = null,
    var diagnosis: String? = null,
    var diagnosticTime: String? = null,
    var internedDays: Int? = null,
    var hospitalizations: Int? = null,
    var schooling: Schooling? = null,
    var schoolFrequency: SchoolFrequency? = null,
    var liveInThisCity: ResidentCity? = null,
    var home: String? = null,
    var monthlyIncome: Float? = null,
    var educationDegree: EducationDegree? = null
)