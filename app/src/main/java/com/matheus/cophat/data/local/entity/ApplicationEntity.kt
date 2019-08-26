package com.matheus.cophat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "application")
data class ApplicationEntity(
    @PrimaryKey
    var applicationId: String,
    var hospital: Hospital,
    var respondent: Respondent,
    var applicator: Applicator,
    var questionnaire: Questionnaire,
    var questions: List<Question>,
    var date: Date,
    var startHour: Date,
    var endHour: Date
)