package com.matheus.cophat.feature.questions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.*
import com.matheus.cophat.data.presenter.ItemSubQuestionPresenter
import com.matheus.cophat.data.presenter.SubQuestionPresenter
import com.matheus.cophat.data.repository.QuestionsRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.helper.visibleOrGone
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubQuestionViewModel(
    private val repository: QuestionsRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    lateinit var presenter: SubQuestionPresenter
    lateinit var gender: GenderType
    val isPrimaryButtonEnabled = MutableLiveData<Boolean>()

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                repository.getGender()?.let {
                    gender = if (it == GenderType.MALE.genderType) {
                        GenderType.MALE
                    } else {
                        GenderType.FEMALE
                    }
                }
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun getAlternatives(): List<ItemSubQuestionPresenter>? {
        return presenter.subQuestion.alternatives
            ?.map { entry ->
                ItemSubQuestionPresenter().apply {
                    type = entry.value.type
                    description = getDescriptionByType(entry.value)
                    descriptionVisibility = getDescriptionVisibility(entry.value)
                    otherVisibility = getOtherVisibility(entry.value)
                    isPrimaryButtonEnabled.postValue(isValidItem(this))
                }
            }
    }

    private fun getDescriptionByType(alternative: Alternative): String? {
        return when {
            alternative.type == SubAnswerType.OTHER -> resourceManager.getString(R.string.other)
            alternative.type == SubAnswerType.OPEN -> resourceManager.getString(R.string.describe)
            else -> alternative.description
        }
    }

    private fun getDescriptionVisibility(alternative: Alternative): Int {
        return (alternative.type != SubAnswerType.OPEN &&
                alternative.type != SubAnswerType.OTHER).visibleOrGone()
    }

    private fun getOtherVisibility(alternative: Alternative): Int {
        return (alternative.type == SubAnswerType.OPEN &&
                alternative.type == SubAnswerType.OTHER).visibleOrGone()
    }

    fun getStatement(): String? {
        return if (presenter.subQuestion.statement.isNullOrEmpty()) {
            if (gender == GenderType.MALE) {
                presenter.subQuestion.statementMale
            } else {
                presenter.subQuestion.statementFemale
            }
        } else {
            presenter.subQuestion.statement
        }
    }

    fun isValidItem(item: ItemSubQuestionPresenter): Boolean {
        return if ((item.type == SubAnswerType.OPEN ||
                    item.type == SubAnswerType.OTHER) &&
            item.chosenSubAnswer != AnswerType.NEVER
        ) {
            !item.other.isNullOrEmpty()
        } else {
            true
        }
    }

    suspend fun saveAlternatives(subAnswers: List<ItemSubQuestionPresenter>) {
        try {
            isLoading.postValue(true)

            for (subAnswer in subAnswers) {
                repository.addChild(presenter.subAnswerPath,
                    SubAnswer().apply {
                        type = subAnswer.type
                        other = subAnswer.other
                        chosenSubAnswer = subAnswer.chosenSubAnswer
                    })
            }
        } catch (e: DatabaseException) {
            handleError.postValue(e)
        } finally {
            isLoading.postValue(false)
        }
    }
}