package com.matheus.cophat.feature.questions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.data.local.entity.*
import com.matheus.cophat.data.presenter.QuestionnairePresenter
import com.matheus.cophat.data.presenter.QuestionsPresenter
import com.matheus.cophat.data.presenter.SubQuestionPresenter
import com.matheus.cophat.data.repository.FirebaseChild
import com.matheus.cophat.data.repository.QuestionsRepository
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionsViewModel(private val repository: QuestionsRepository) : BaseViewModel() {

    val questions = ArrayList<Question>()
    val presenter = QuestionsPresenter()
    val subQuestion = MutableLiveData<SubQuestionPresenter>()
    private var position = 0
    private var subQuestionPosition = 0
    lateinit var gender: GenderType
    private var questionnairePresenter: QuestionnairePresenter? = null

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                getQuestions()
                getUpdatedQuestionnaire()
                retrieveApplicationData(getApplication())
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

    private suspend fun getUpdatedQuestionnaire() {
        repository.getFamilyId()?.let {
            questionnairePresenter = repository.getQuestionnaireByFamilyId(it)
        }
    }

    private fun getApplication() : ApplicationEntity? {
        return if (isChildren) {
            questionnairePresenter?.questionnaire?.childApplication
        } else {
            questionnairePresenter?.questionnaire?.parentApplication
        }
    }

    private fun retrieveApplicationData(application: ApplicationEntity?) {
        application?.respondent?.let { respondent ->
            respondent.gender?.let {
                gender = if (it == GenderType.MALE.genderType) {
                    GenderType.MALE
                } else {
                    GenderType.FEMALE
                }
            }
        }
        application?.answers?.let {
            position = it.size
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
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                repository.addChild(getCurrentAnswerPath(), generateAnswer())
                getUpdatedQuestionnaire()
                if (questions[position].subQuestions != null &&
                    presenter.answer != AnswerType.NEVER) {
                    generateSubQuestionPresenter()
                } else {
                    continueQuestionnaire()
                }
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    private fun getCurrentAnswerPath(): String {
        return if (isChildren) {
            FirebaseChild.QUESTIONNAIRES.pathName +
                    "/" +
                    questionnairePresenter?.questionnaireFirebaseKey +
                    "/childApplication/answers"
        } else {
            FirebaseChild.QUESTIONNAIRES.pathName +
                    "/" +
                    questionnairePresenter?.questionnaireFirebaseKey +
                    "/parentApplication/answers"
        }
    }

    private fun generateAnswer(): Answer {
        return Answer(position + 1, presenter.answer)
    }

    private suspend fun continueQuestionnaire() {
        if (position + 1 < questions.size) {
            position++
            generatePresenter()
        } else {
            questionnairePresenter?.let {
                if (isChildren) {
                    it.questionnaire.childApplication?.status = ApplicationStatus.COMPLETED
                    repository.updateChildrenQuestionnaire(it)
                } else {
                    it.questionnaire.parentApplication?.status = ApplicationStatus.COMPLETED
                    repository.updateParentQuestionnaire(it)
                }
            }
        }
    }

    private fun generateSubQuestionPresenter() {
        val answerKey = getApplication()
            ?.answers
            ?.entries
            ?.firstOrNull { it.value.id == position + 1 }
            ?.key

        val subQuestion = questions[position]
            .subQuestions
            ?.entries
            ?.firstOrNull{ it.value.id == subQuestionPosition + 1 }
            ?.value

        answerKey?.let {
            subQuestion?.let {
                this.subQuestion.postValue(SubQuestionPresenter(
                    getCurrentAnswerPath() + "/" + answerKey + "/subAnswers",
                    subQuestion))
            }
        }
    }
}