package com.matheus.cophat.feature.questions.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.data.local.entity.GenderType
import com.matheus.cophat.data.local.entity.SubAnswer
import com.matheus.cophat.data.presenter.SubQuestionPresenter
import com.matheus.cophat.data.repository.QuestionsRepository
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubQuestionViewModel(private val repository: QuestionsRepository) : BaseViewModel() {

    lateinit var presenter: SubQuestionPresenter
    lateinit var gender: GenderType

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

    fun getAlternatives(): List<SubAnswer>? {
        return presenter.subQuestion.alternatives
            ?.map { entry -> SubAnswer(entry.value.type) }
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

    suspend fun saveAlternatives(subAnswers: List<SubAnswer>) {
        try {
            isLoading.postValue(true)

            for (subAnswer in subAnswers) {
                repository.addChild(presenter.subAnswerPath, subAnswer)
            }
        } catch (e: DatabaseException) {
            handleError.postValue(e)
        } finally {
            isLoading.postValue(false)
        }
    }
}