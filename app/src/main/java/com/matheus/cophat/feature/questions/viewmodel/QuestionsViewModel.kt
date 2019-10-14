package com.matheus.cophat.feature.questions.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.data.local.entity.FormType
import com.matheus.cophat.data.local.entity.GenderType
import com.matheus.cophat.data.local.entity.Question
import com.matheus.cophat.data.presenter.QuestionnairePresenter
import com.matheus.cophat.data.presenter.QuestionsPresenter
import com.matheus.cophat.data.repository.QuestionsRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val repository: QuestionsRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    val questions = ArrayList<Question>()
    val presenter = QuestionsPresenter()
    private var position = 0
    lateinit var gender: GenderType
    var questionnairePresenter: QuestionnairePresenter? = null

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                getQuestions()
                getQuestionnaire()
                retrieveApplicationData()
                generatePresenter()
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    private suspend fun getQuestions() {
        val form = if (isChildren) {
            repository.getForms(FormType.CHILDREN)
        } else {
            repository.getForms(FormType.PARENTS)
        }
        form?.let { questions.addAll(it) }
    }

    private suspend fun getQuestionnaire() {
        repository.getFamilyId()?.let {
            questionnairePresenter = repository.getQuestionnaireByFamilyId(it)
        }
    }

    private fun retrieveApplicationData() {
        val application = if (isChildren) {
            questionnairePresenter?.questionnaire?.childApplication
        } else {
            questionnairePresenter?.questionnaire?.parentApplication
        }
        application?.respondent?.let { respondent ->
            respondent.gender?.let {
                gender = if (it == GenderType.MALE.genderType) {
                    GenderType.MALE
                } else {
                    GenderType.FEMALE
                }
            }
        }

        application?.questions?.let {
            position = it.size - 1
        }
    }

    private fun generatePresenter() {
        presenter.code = questionnairePresenter?.questionnaire?.familyId
        presenter.state = retrieveState()
        presenter.progress = retrieveProgress()
        presenter.statement = retrieveStatementByGender()
    }

    private fun retrieveState(): String {
        return "${position + 1} / ${questions.size}"
    }

    private fun retrieveProgress(): Int {
        return ((position + 1) / questions.size.toDouble() * 100).toInt()
    }

    private fun retrieveStatementByGender(): String? {
        return if (questions[position].statement.isNullOrEmpty()) {
            if (gender == GenderType.MALE) {
                questions[position].statementMale
            } else {
                questions[position].statementFemale
            }
        } else {
            questions[position].statement
        }
    }

    fun updateApplication() {
        if (isChildren) {

        } else {

        }
    }
}