package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import kotlinx.coroutines.tasks.await

class IntroRepository(private val database: DatabaseReference, private val dao: ApplicationDao) :
    BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun testSave() {
        database.child("hospital").push()
            .setValue(Hospital("HCU", "Hospital do Câncer de Uberlândia")).await()
    }

    suspend fun isEmpty(): Boolean {
        return dao.getAllApplications().isEmpty()
    }
}