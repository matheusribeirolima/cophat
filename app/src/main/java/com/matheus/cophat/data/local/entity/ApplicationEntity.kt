package com.matheus.cophat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "application")
data class ApplicationEntity(
    @PrimaryKey
    val applicationId: String,
    val hospital: Hospital,
    val respondent: Respondent,
    val applicator: Applicator,
    val questionnaire: Questionnaire,
    val questions: List<Question>,
    val date: Date,
    val startHour: Date,
    val endHour: Date
)