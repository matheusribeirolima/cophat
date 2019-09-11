package com.matheus.cophat.feature.configure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.presenter.ApplicatorConfigurePresenter
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import com.matheus.cophat.data.repository.ConfigureRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.helper.isValidEmail
import com.matheus.cophat.helper.visibleOrGone
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigureViewModel(
    private val repository: ConfigureRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    val applicatorsPresenter = MutableLiveData<List<ItemApplicatorPresenter>>()
    val statusApplicator = MutableLiveData<String>()

    fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                val list = ArrayList<ItemApplicatorPresenter>()
                repository.getDatabaseChildHash("applicator", Applicator::class.java)
                    .forEach { (key, value) ->
                        list.add(ItemApplicatorPresenter(value.name, value.contact, key))
                    }

                for (i in list) {
                    i.applicatorDividerVisibility = (i != list.last()).visibleOrGone()
                }

                applicatorsPresenter.postValue(list)
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun getAddApplicator(): ApplicatorConfigurePresenter {
        return ApplicatorConfigurePresenter(
            resourceManager.getString(R.string.add_applicator),
            resourceManager.getString(R.string.add_desc_applicator)
        )
    }

    fun getEditApplicator(applicator: ItemApplicatorPresenter): ApplicatorConfigurePresenter {
        return ApplicatorConfigurePresenter(
            resourceManager.getString(R.string.edit_applicator),
            resourceManager.getString(R.string.edit_desc_applicator),
            applicator.applicatorName,
            applicator.applicatorContact
        )
    }

    fun saveOrUpdateApplicator(applicator: ApplicatorConfigurePresenter?, key: String?) {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                applicator?.let {
                    if (key != null) {
                        repository.updateApplicator(it.name, it.contact, key)
                        statusApplicator.postValue(resourceManager.getString(R.string.success_update))
                    } else {
                        repository.saveApplicator(it.name, it.contact)
                        statusApplicator.postValue(resourceManager.getString(R.string.success_register))
                    }
                }
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun removeApplicator(key: String?) {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                key?.let {
                    repository.removeApplicator(key)
                    statusApplicator.postValue(resourceManager.getString(R.string.success_remove))
                }
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun verifyDialogPresenter(applicator: ApplicatorConfigurePresenter?) {
        applicator?.let {
            if (applicator.name.isNotEmpty() && applicator.contact.isValidEmail()) {
                isButtonEnabled.postValue(true)
            } else {
                isButtonEnabled.postValue(false)
            }
        }
    }
}