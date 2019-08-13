package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class IntroRepository(private val database: DatabaseReference) {

    suspend fun testSave() {
        database.child("test").setValue("test value").await()
    }
}