package com.matheus.cophat.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matheus.cophat.data.local.entity.Question
import com.matheus.cophat.data.local.entity.Respondent
import java.util.*

class DataConverter {

    @TypeConverter
    fun toQuestions(questions: String?): HashMap<String, Question>? {
        return questions?.let {
            val type = object : TypeToken<HashMap<String, Question>>() {}.type
            Gson().fromJson<HashMap<String, Question>>(questions, type)
        }
    }

    @TypeConverter
    fun fromQuestions(questions: HashMap<String, Question>?): String? {
        return questions?.let {
            val type = object : TypeToken<HashMap<String, Question>>() {}.type
            return Gson().toJson(questions, type)
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
}