package com.matheus.cophat.feature.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital
import com.matheus.cophat.data.presenter.BeginPresenter
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
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
    val applicators = MutableLiveData<List<Applicator>>()
    val hospitals = MutableLiveData<List<Hospital>>()
    val applicatorsPresenter = MutableLiveData<List<ItemApplicatorPresenter>>()

    fun initializeBegin() {
        viewModelScope.launch(context = Dispatchers.IO) {
            handleLoading.postValue(true)

            val presenterImage: Int
            val presenterTitle: String
            val presenterSubtitle: String
            val presenterButton: String

            try {
                if (isChildren) {
                    presenterImage = R.drawable.ic_launcher
                    presenterTitle = resourceManager.getString(R.string.cophat_ca)
                    presenterSubtitle = resourceManager.getString(R.string.cophat_ca_desc)
                } else {
                    presenterImage = R.drawable.ic_launcher
                    presenterTitle = resourceManager.getString(R.string.cophat_p)
                    presenterSubtitle = resourceManager.getString(R.string.cophat_p_desc)
                }

                presenterButton = if (repository.isEmpty())
                    resourceManager.getString(R.string.initiate_questionnaire) else
                    resourceManager.getString(R.string.continue_questionnaire)

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
                handleLoading.postValue(false)
            }
        }
    }

    fun initializeGenerateCode() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                handleLoading.postValue(true)
                hospitals.postValue(repository.getDatabaseChild("hospital", Hospital::class.java))
                applicators.postValue(
                    repository.getDatabaseChild("applicator", Applicator::class.java)
                )
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                handleLoading.postValue(false)
            }
        }
    }

    fun initializeConfigure() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                handleLoading.postValue(true)

                val list = ArrayList<ItemApplicatorPresenter>()
                repository.getDatabaseChildHash("applicator", Applicator::class.java)
                    .forEach { (key, value) ->
                        list.add(ItemApplicatorPresenter(value.name, value.contact, 0, key))
                    }

                applicatorsPresenter.postValue(list)
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                handleLoading.postValue(false)
            }
        }
    }

    fun teste(item: ItemApplicatorPresenter) {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                handleLoading.postValue(true)

                repository.updateChild(
                    "applicator",
                    item.applicatorDatabaseKey,
                    Applicator("Matheus Ribeiro Lima", "matheus_ribeirolima@hotmail.com")
                )
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                handleLoading.postValue(false)
            }
        }
    }

    fun chooseNav(): Int {
        return if (isChildren) {
            R.id.action_generateCodeFragment_to_children_navigation
        } else {
            R.id.action_generateCodeFragment_to_parents_navigation
        }
    }
}