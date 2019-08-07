package com.example.cophat.data.source.local.entity

import java.util.*

data class Respondent(
    val code: String,
    val motherProfession: String,
    val fatherProfession: String,
    val maritalStatus: MaritalStatus,
    val religion: Religion,
    val patientName: String,
    val medicalRecords: String,
    val birthDate: Date,
    val age: Int,
    val gender: Gender,
    val diagnosis: String,
    val diagnosticTime: String,
    val internedDays: Int,
    val hospitalizations: Int,
    val schooling: Schooling,
    val schoolFrequency: SchoolFrequency,
    val liveInThisCity: ResidentCity,
    val home: String,
    val monthlyIncome: Double,
    val educationDegree: EducationDegree
)