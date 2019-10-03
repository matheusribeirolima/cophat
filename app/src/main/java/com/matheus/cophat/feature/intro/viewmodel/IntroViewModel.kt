package com.matheus.cophat.feature.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.ApplicationEntity
import com.matheus.cophat.data.presenter.BeginPresenter
import com.matheus.cophat.data.presenter.StepsPresenter
import com.matheus.cophat.data.repository.IntroRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IntroViewModel(
    private val repository: IntroRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    val beginPresenter = MutableLiveData<BeginPresenter>()
    private var application: ApplicationEntity? = null

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                val presenterImage: Int
                val presenterTitle: String
                val presenterSubtitle: String
                val presenterButton: String = if (repository.isEmpty())
                    resourceManager.getString(R.string.initiate_questionnaire) else
                    resourceManager.getString(R.string.continue_questionnaire)

                if (isChildren) {
                    presenterImage = R.drawable.ic_launcher
                    presenterTitle = resourceManager.getString(R.string.cophat_ca)
                    presenterSubtitle = resourceManager.getString(R.string.cophat_ca_desc)
                } else {
                    presenterImage = R.drawable.ic_launcher
                    presenterTitle = resourceManager.getString(R.string.cophat_p)
                    presenterSubtitle = resourceManager.getString(R.string.cophat_p_desc)
                }

                beginPresenter.postValue(
                    BeginPresenter(
                        presenterImage,
                        presenterTitle,
                        presenterSubtitle,
                        presenterButton
                    )
                )
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    suspend fun chooseNavigation(): StepsPresenter? {
        try {
            isLoading.postValue(true)

            application = repository.getApplication()
            return when {
                application == null ->
                    StepsPresenter.GENERATE_CODE_STEP_0
                isChildren ->
                    StepsPresenter.CHILD_QUESTIONS
                application?.respondent?.motherProfession == null ->
                    StepsPresenter.REGISTER_PARENTS_STEP_1
                application?.respondent?.medicalRecords == null ->
                    StepsPresenter.REGISTER_PATIENT_STEP_2
                application?.respondent?.diagnosis == null ->
                    StepsPresenter.REGISTER_INTERNAL_STEP_3
                application?.respondent?.schooling == null ->
                    StepsPresenter.REGISTER_SCHOOL_STEP_4
                else ->
                    StepsPresenter.GENERATE_CODE_STEP_0
            }
        } catch (e: DatabaseException) {
            handleError.postValue(e)
            return null
        } finally {
            isLoading.postValue(false)
        }
    }
}