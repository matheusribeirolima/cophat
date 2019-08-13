package com.matheus.cophat.data.source.local.dao

import androidx.room.*
import com.matheus.cophat.data.source.local.entity.ApplicationEntity

@Dao
interface ApplicationDao {

    @Query("SELECT * FROM application")
    suspend fun getAllApplications(): List<ApplicationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(applicationEntity: ApplicationEntity)
}