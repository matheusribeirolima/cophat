package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao
import com.matheus.cophat.data.local.entity.ApplicationEntity
import com.matheus.cophat.data.local.entity.Questionnaire
import com.matheus.cophat.data.local.entity.QuestionnaireType
import kotlinx.coroutines.tasks.await

class GenerateCodeRepository(
    private val database: DatabaseReference,
    private val dao: ApplicationDao
) : BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun testSave() {
        database.child("questionnaires").push()
            .setValue(
                Questionnaire(
                    "MRLHCU11092019",
                    ApplicationEntity(questionnaireType = QuestionnaireType.CHILDREN),
                    ApplicationEntity(questionnaireType = QuestionnaireType.PARENTS)
                )
            ).await()
    }
}