package com.matheus.cophat.data.repository

import com.google.firebase.database.DatabaseReference
import com.matheus.cophat.data.local.dao.ApplicationDao

class ParentsRepository(private val database: DatabaseReference, private val dao: ApplicationDao) {
}