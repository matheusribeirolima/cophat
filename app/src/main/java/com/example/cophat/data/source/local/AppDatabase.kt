package com.example.cophat.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cophat.data.source.local.converter.DataConverter
import com.example.cophat.data.source.local.dao.*
import com.example.cophat.data.source.local.entity.ApplicationEntity
import com.example.cophat.data.source.local.entity.Question
import com.example.cophat.data.source.local.entity.Questionnaire
import com.example.cophat.data.source.local.entity.Respondent

@Database(
    entities = [ApplicationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun applicationDao(): ApplicationDao
}