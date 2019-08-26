package com.matheus.cophat.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.matheus.cophat.data.local.dao.ApplicationDao
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class IntroRepository(private val database: DatabaseReference, private val dao: ApplicationDao) {

    suspend fun testSave() {
        database.child("hosptial").push()
            .setValue(Hospital("HCU", "Hospital do Câncer de Uberlândia")).await()
    }

    suspend fun isEmpty(): Boolean {
        return dao.getAllApplications().isEmpty()
    }

    suspend fun getApplicators(): List<Applicator> = suspendCoroutine { d ->
        val applicators = ArrayList<Applicator>()
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                d.resumeWithException(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    for (dataSnapshot in snapshot.child("applicator").children) {
                        dataSnapshot.getValue(Applicator::class.java)?.let { applicators.add(it) }
                    }

                    d.resume(applicators)
                } catch (e: Exception) {
                    d.resumeWithException(e)
                }
            }
        })
    }
}