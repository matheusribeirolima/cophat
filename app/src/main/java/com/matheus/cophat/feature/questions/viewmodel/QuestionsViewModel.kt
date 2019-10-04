package com.matheus.cophat.feature.questions.viewmodel

import com.matheus.cophat.data.repository.QuestionsRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel

class QuestionsViewModel(
    private val repository: QuestionsRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {



    override fun initialize() {

    }
}