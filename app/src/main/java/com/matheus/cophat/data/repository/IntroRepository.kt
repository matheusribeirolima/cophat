package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao
import kotlinx.coroutines.tasks.await

class IntroRepository(private val database: DatabaseReference, private val dao: ApplicationDao) {

    suspend fun testSave() {
        database.child("test").setValue("test value").await()
    }

    suspend fun isEmpty(): Boolean {
        return dao.getAllApplications().isEmpty()
    }
}