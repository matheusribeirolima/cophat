package com.matheus.cophat.feature.generate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.ApplicationEntity
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital
import com.matheus.cophat.data.local.entity.Respondent
import com.matheus.cophat.data.presenter.GenerateCodePresenter
import com.matheus.cophat.data.repository.GenerateCodeRepository
import com.matheus.cophat.helper.toString
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class GenerateCodeViewModel(private val repository: GenerateCodeRepository) : BaseViewModel() {

    val applicators = MutableLiveData<List<Applicator>>()
    val hospitals = MutableLiveData<List<Hospital>>()
    val presenter = GenerateCodePresenter()
    val navigate = MutableLiveData<Int>()

    override fun initialize() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                isLoading.postValue(true)

                hospitals.postValue(repository.getHospitals())
                applicators.postValue(repository.getApplicators())
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun validatePresenter() {
        if (presenter.child.isNotEmpty() &&
            presenter.applicator.name.isNotEmpty() &&
            presenter.hospital.name.isNotEmpty()
        ) {
            isButtonEnabled.postValue(true)
        } else {
            isButtonEnabled.postValue(false)
        }
    }

    suspend fun initiateQuestionnaire() {
        try {
            isLoading.postValue(true)

            val familyId = generateFamilyId()
            val application = generateApplicationEntity()
            val questionnaire = repository.getQuestionnaireByFamilyId(familyId)

            if (isChildren) {
                repository.addOrUpdateChildQuestionnaire(familyId, application, questionnaire)
            } else {
                repository.addOrUpdateParentQuestionnaire(familyId, application, questionnaire)
            }
            repository.saveApplicationLocally(application)
            chooseDestination()
        } catch (e: DatabaseException) {
            handleError.postValue(e)
        } finally {
            isLoading.postValue(false)
        }
    }

    private fun generateFamilyId(): String {
        val childInitials = presenter.child
            .split(' ')
            .mapNotNull { it.firstOrNull()?.toString() }
            .reduce { acc, s -> acc + s }

        val nowFormatted = Calendar.getInstance().toString("ddMMyyyy")

        return childInitials + presenter.hospital.code + nowFormatted
    }

    private fun generateApplicationEntity(): ApplicationEntity {
        return ApplicationEntity(
            respondent = generateRespondent(),
            hospital = presenter.hospital.name,
            applicator = presenter.applicator.name,
            date = Calendar.getInstance().toString("dd/MM/yyyy")
        )
    }

    private fun generateRespondent(): Respondent {
        return Respondent(
            patientName = presenter.child,
            gender = presenter.gender.genderType
        )
    }

    private fun chooseDestination() {
        if (isChildren) {
            navigate.postValue(R.id.action_generateCodeFragment_to_children_navigation)
        } else {
            navigate.postValue(R.id.action_generateCodeFragment_to_parents_navigation)
        }
    }
}