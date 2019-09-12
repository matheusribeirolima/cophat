package com.matheus.cophat.feature.generate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital
import com.matheus.cophat.data.presenter.GenerateCodePresenter
import com.matheus.cophat.data.repository.GenerateCodeRepository
import com.matheus.cophat.data.repository.IntroRepository
import com.matheus.cophat.helper.ResourceManager
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateCodeViewModel(
    private val repository: GenerateCodeRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    val applicators = MutableLiveData<List<Applicator>>()
    val hospitals = MutableLiveData<List<Hospital>>()
    val generateCodePresenter = GenerateCodePresenter()

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)
                hospitals.postValue(
                    repository.getDatabaseChild("hospital", Hospital::class.java)
                )
                applicators.postValue(
                    repository.getDatabaseChild("applicator", Applicator::class.java)
                )
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
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

    fun validatePresenter(presenter: GenerateCodePresenter?) {
        presenter?.let {
            if (presenter.child.isNotEmpty() &&
                presenter.applicator.isNotEmpty() &&
                presenter.hospital.isNotEmpty()
            ) {
                isButtonEnabled.postValue(true)
            } else {
                isButtonEnabled.postValue(false)
            }
        }
    }

    fun test() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                repository.testSave()
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}