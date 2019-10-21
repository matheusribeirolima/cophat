package com.matheus.cophat.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matheus.cophat.data.local.entity.Answer
import com.matheus.cophat.data.local.entity.ApplicationStatus
import com.matheus.cophat.data.local.entity.Question
import com.matheus.cophat.data.local.entity.Respondent
import java.util.*

class DataConverter {

    @TypeConverter
    fun toAnswers(answers: String?): HashMap<String, Answer>? {
        return answers?.let {
            val type = object : TypeToken<HashMap<String, Answer>>() {}.type
            Gson().fromJson<HashMap<String, Answer>>(answers, type)
        }
    }

    @TypeConverter
    fun fromAnswers(answers: HashMap<String, Answer>?): String? {
        return answers?.let {
            val type = object : TypeToken<HashMap<String, Answer>>() {}.type
            return Gson().toJson(answers, type)
        }
    }

    @TypeConverter
    fun toRespondent(respondent: String?): Respondent? {
        return respondent?.let { Gson().fromJson(respondent, Respondent::class.java) }
    }

    @TypeConverter
    fun fromRespondent(respondent: Respondent?): String? {
        return Gson().toJson(respondent)
    }

    @TypeConverter
    fun toApplicationStatus(status: String?): ApplicationStatus? {
        return status?.let { ApplicationStatus.valueOf(it) }
    }

    @TypeConverter
    fun fromApplicationStatus(status: ApplicationStatus?): String? {
        return status?.toString()
    }
}