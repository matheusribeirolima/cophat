package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.entity.Questionnaire

class QuestionnairesRepository(private val database: DatabaseReference) : BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun getQuestionnaires(): List<Questionnaire> {
        return getDatabaseChild(FirebaseChild.QUESTIONNAIRES, Questionnaire::class.java)
    }
}