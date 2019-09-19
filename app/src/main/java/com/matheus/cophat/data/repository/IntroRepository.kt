package com.matheus.cophat.data.repository

import com.matheus.cophat.data.local.dao.ApplicationDao

class IntroRepository(private val dao: ApplicationDao) {

    suspend fun isEmpty(): Boolean {
        return dao.getAllApplications().isEmpty()
    }
}