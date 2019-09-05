package com.matheus.cophat.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

abstract class BaseRepository {

    abstract fun getDatabase(): DatabaseReference

    suspend fun <T> getDatabaseChild(child: String, dataType: Class<T>): List<T> =
        suspendCoroutine { d ->
            val children = ArrayList<T>()
            getDatabase().addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    d.resumeWithException(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        for (dataSnapshot in snapshot.child(child).children) {
                            dataSnapshot.getValue(dataType)?.let { children.add(it) }
                        }

                        d.resume(children)
                    } catch (e: Exception) {
                        d.resumeWithException(e)
                    }
                }
            })
        }

    suspend fun <T> getDatabaseChildHash(child: String, dataType: Class<T>): HashMap<String, T> =
        suspendCoroutine { d ->
            val children = HashMap<String, T>()
            getDatabase().addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    d.resumeWithException(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        for (dataSnapshot in snapshot.child(child).children) {
                            dataSnapshot.getValue(dataType)?.let { value ->
                                dataSnapshot.key?.let { key ->
                                    children[key] = value
                                }
                            }
                        }

                        d.resume(children)
                    } catch (e: Exception) {
                        d.resumeWithException(e)
                    }
                }
            })
        }

    suspend fun updateChild(child: String, key: String, updated: Any): Void? =
        suspendCoroutine { d ->
            val childUpdates = HashMap<String, Any>()
            childUpdates["/$child/$key"] = updated

            getDatabase().updateChildren(childUpdates)
                .addOnCompleteListener { d.resume(null) }
                .addOnFailureListener { d.resumeWithException(it) }
        }

    suspend fun removeChild(child: String, key: String): Void? =
        suspendCoroutine { d ->
            val childUpdates = HashMap<String, Any?>()
            childUpdates["/$child/$key"] = null

            getDatabase().updateChildren(childUpdates)
                .addOnCompleteListener { d.resume(null) }
                .addOnFailureListener { d.resumeWithException(it) }
        }
}