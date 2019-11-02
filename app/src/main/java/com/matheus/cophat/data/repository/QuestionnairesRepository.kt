package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query

class QuestionnairesRepository(private val database: DatabaseReference) : BaseRepository() {

    override fun getDatabase(): DatabaseReference {
        return database
    }

    fun getQuery(): Query {
        return database.child(FirebaseChild.QUESTIONNAIRES.pathName)
    }
}