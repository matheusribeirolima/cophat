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

                application = repository.getApplication()

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

    fun chooseNavigation(): StepsPresenter {
        return when {
            application == null ->
                StepsPresenter.GENERATE_CODE_STEP_0
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
    }
}