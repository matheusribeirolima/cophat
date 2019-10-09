package com.matheus.cophat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "application")
data class ApplicationEntity(
    @PrimaryKey
    @get:Exclude var familyId: String = "",
    var hospital: String? = null,
    var respondent: Respondent? = null,
    var applicator: String? = null,
    var questions: HashMap<String, Question>? = null,
    var date: String? = null,
    var startHour: String? = null,
    var endHour: String? = null
)