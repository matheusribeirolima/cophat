package com.matheus.cophat.feature.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.BeginPresenter
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
}