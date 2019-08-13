package com.matheus.cophat.feature.intro.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.data.repository.IntroRepository
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IntroViewModel(private val repository: IntroRepository) : BaseViewModel() {

    fun testSave() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                repository.testSave()
            } catch (e: DatabaseException) {
                handleError.postValue(e)
            }
        }
    }
}