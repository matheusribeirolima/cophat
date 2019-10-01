package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao
import com.matheus.cophat.data.local.entity.ApplicationEntity
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital
import com.matheus.cophat.data.local.entity.Questionnaire
import com.matheus.cophat.data.presenter.QuestionnairePresenter

class GenerateCodeRepository(
    private val database: DatabaseReference,
    private val dao: ApplicationDao
) : BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun getHospitals(): List<Hospital> {
        return getDatabaseChild(FirebaseChild.HOSPITALS, Hospital::class.java)
    }

    suspend fun getApplicators(): List<Applicator> {
        return getDatabaseChild(FirebaseChild.APPLICATORS, Applicator::class.java)
    }

    suspend fun saveApplicationLocally(application: ApplicationEntity) {
        dao.insertApplication(application)
    }

    suspend fun getQuestionnaireByFamilyId(familyId: String): QuestionnairePresenter? {
        return try {
            getDatabaseChildHash(FirebaseChild.QUESTIONNAIRES, Questionnaire::class.java)
                .filter { it.value.familyId == familyId }
                .map { (key, value) -> QuestionnairePresenter(value, key) }
                .first()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addOrUpdateChildQuestionnaire(
        familyId: String,
        application: ApplicationEntity,
        questionnaire: QuestionnairePresenter? = null
    ) {
        if (questionnaire == null) {
            addChild(
                FirebaseChild.QUESTIONNAIRES,
                Questionnaire(familyId, childApplication = application)
            )
        } else {
            questionnaire.questionnaire.childApplication = application
            updateChild(
                FirebaseChild.QUESTIONNAIRES,
                questionnaire.questionnaireFirebaseKey,
                questionnaire.questionnaire
            )
        }
    }

    suspend fun addOrUpdateParentQuestionnaire(
        familyId: String,
        application: ApplicationEntity,
        questionnaire: QuestionnairePresenter? = null
    ) {
        if (questionnaire == null) {
            addChild(
                FirebaseChild.QUESTIONNAIRES,
                Questionnaire(familyId, parentApplication = application)
            )
        } else {
            questionnaire.questionnaire.parentApplication = application
            updateChild(
                FirebaseChild.QUESTIONNAIRES,
                questionnaire.questionnaireFirebaseKey,
                questionnaire.questionnaire
            )
        }
    }
}