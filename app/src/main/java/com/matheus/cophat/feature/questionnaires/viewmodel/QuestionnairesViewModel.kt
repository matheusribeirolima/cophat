package com.matheus.cophat.feature.questionnaires.viewmodel

import com.matheus.cophat.data.repository.QuestionnairesRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel

class QuestionnairesViewModel(
    private val repository: QuestionnairesRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    override fun initialize() {

    }
}