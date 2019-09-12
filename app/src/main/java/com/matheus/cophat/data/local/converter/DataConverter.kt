package com.matheus.cophat.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matheus.cophat.data.local.entity.*
import java.util.*

class DataConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toApplicator(applicator: String?): Applicator? {
        return applicator?.let { Gson().fromJson(applicator, Applicator::class.java) }
    }

    @TypeConverter
    fun fromApplicator(applicator: Applicator?): String? {
        return Gson().toJson(applicator)
    }

    @TypeConverter
    fun toHospital(hospital: String?): Hospital? {
        return hospital?.let { Gson().fromJson(hospital, Hospital::class.java) }
    }

    @TypeConverter
    fun fromHospital(hospital: Hospital?): String? {
        return Gson().toJson(hospital)
    }

    @TypeConverter
    fun toQuestions(questions: String?): List<Question>? {
        return questions?.let {
            val type = object : TypeToken<List<Question>>() {}.type
            Gson().fromJson<List<Question>>(questions, type)
        }
    }

    @TypeConverter
    fun fromQuestions(questions: List<Question>?): String? {
        return questions?.let {
            val type = object : TypeToken<List<Question>>() {}.type
            return Gson().toJson(questions, type)
        }
    }

    @TypeConverter
    fun toQuestionnaire(questionnaire: String?): QuestionnaireType? {
        return questionnaire?.let { QuestionnaireType.valueOf(it) }
    }

    @TypeConverter
    fun fromQuestionnaire(questionnaireType: QuestionnaireType?): String? {
        return questionnaireType?.toString()
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