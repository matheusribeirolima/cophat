package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.entity.*
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter

class ConfigureRepository(private val database: DatabaseReference) : BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    suspend fun getApplicators(): List<ItemApplicatorPresenter> {
        val list = ArrayList<ItemApplicatorPresenter>()
        getDatabaseChildHash(FirebaseChild.APPLICATORS, Applicator::class.java)
            .forEach { (key, value) ->
                list.add(ItemApplicatorPresenter(value.name, value.contact, key))
            }
        return list
    }

    suspend fun saveApplicator(name: String, contact: String) {
        addChild(FirebaseChild.APPLICATORS, Applicator(name, contact))
    }

    suspend fun updateApplicator(name: String, contact: String, key: String) {
        updateChild(FirebaseChild.APPLICATORS, key, Applicator(name, contact))
    }

    suspend fun removeApplicator(key: String) {
        removeChild(FirebaseChild.APPLICATORS, key)
    }

    suspend fun test() {

    }
}