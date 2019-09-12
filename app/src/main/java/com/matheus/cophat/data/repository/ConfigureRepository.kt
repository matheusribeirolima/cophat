package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao
import com.matheus.cophat.data.local.entity.Applicator
import kotlinx.coroutines.tasks.await

class ConfigureRepository(
    private val database: DatabaseReference,
    private val dao: ApplicationDao
) :
    BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun saveApplicator(name: String, contact: String) {
        database.child("applicator").push().setValue(Applicator(name, contact)).await()
    }

    suspend fun updateApplicator(name: String, contact: String, key: String) {
        updateChild("applicator", key, Applicator(name, contact))
    }

    suspend fun removeApplicator(key: String) {
        removeChild("applicator", key)
    }
}