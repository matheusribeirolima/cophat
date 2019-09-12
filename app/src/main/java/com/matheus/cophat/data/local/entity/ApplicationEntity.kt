package com.matheus.cophat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "application")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true)
    var applicationId: Int = 0,
    var hospital: Hospital? = null,
    var respondent: Respondent? = null,
    var applicator: Applicator? = null,
    var questionnaireType: QuestionnaireType? = null,
    var questions: List<Question>? = null,
    var date: Date? = null,
    var startHour: Date? = null,
    var endHour: Date? = null
)