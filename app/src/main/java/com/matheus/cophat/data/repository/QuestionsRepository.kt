package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao
import com.matheus.cophat.data.local.entity.Form
import com.matheus.cophat.data.local.entity.FormType
import com.matheus.cophat.data.local.entity.Question
import com.matheus.cophat.data.presenter.QuestionnairePresenter

class QuestionsRepository(
    private val database: DatabaseReference,
    private val dao: ApplicationDao
) : BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun getForms(formType: FormType): List<Question>? {
        return getDatabaseChild(FirebaseChild.FORMS, Form::class.java)
            .first { it.type == formType }.questions
            ?.map { entry -> entry.value }
            ?.toList()
            ?.sortedBy { it.id }
    }

    suspend fun getFamilyId(): String? {
        return dao.getApplication()?.familyId
    }

    suspend fun updateParentQuestionnaire(questionnaire: QuestionnairePresenter?) {
        questionnaire?.let {
            updateChild(
                FirebaseChild.QUESTIONNAIRES,
                questionnaire.questionnaireFirebaseKey,
                questionnaire.questionnaire
            )
        }
    }

    suspend fun updateChildrenQuestionnaire(questionnaire: QuestionnairePresenter?) {
        questionnaire?.let {
            updateChild(
                FirebaseChild.QUESTIONNAIRES,
                questionnaire.questionnaireFirebaseKey,
                questionnaire.questionnaire
            )
        }
    }
}