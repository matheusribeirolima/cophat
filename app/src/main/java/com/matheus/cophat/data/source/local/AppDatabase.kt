package com.matheus.cophat.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.matheus.cophat.data.source.local.converter.DataConverter
import com.matheus.cophat.data.source.local.dao.*
import com.matheus.cophat.data.source.local.entity.ApplicationEntity

@Database(
    entities = [ApplicationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun applicationDao(): ApplicationDao
}