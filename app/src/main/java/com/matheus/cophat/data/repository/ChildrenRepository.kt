package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao

class ChildrenRepository(private val database: DatabaseReference, private val dao: ApplicationDao) :
    BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun getPatientName(): String? {
        return dao.getApplication()?.respondent?.patientName
    }
}