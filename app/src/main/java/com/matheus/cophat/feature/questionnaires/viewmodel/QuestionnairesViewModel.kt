package com.matheus.cophat.feature.questionnaires.viewmodel

import com.matheus.cophat.data.repository.IntroRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel

class QuestionnairesViewModel(
    private val repository: IntroRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

}