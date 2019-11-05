package com.matheus.cophat.feature.questionnaires.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.data.local.entity.Category
import com.matheus.cophat.data.local.entity.FormType
import com.matheus.cophat.data.local.entity.Question
import com.matheus.cophat.data.repository.QuestionnairesRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExportExcelViewModel(
    private val repository: QuestionnairesRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    suspend fun getCategories() : List<Category> {
        return repository.getCategories()
    }

    suspend fun getQuestions() : List<Question>? {
        return if (isChildren) {
            repository.getForms(FormType.CHILDREN)
        } else {
            repository.getForms(FormType.PARENTS)
        }
    }
}