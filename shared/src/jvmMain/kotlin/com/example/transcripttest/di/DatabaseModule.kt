package com.example.transcripttest.di

import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.transcripttest.data.local.AppDatabase
import com.example.transcripttest.dataclasses.Project
import com.example.transcripttest.interfaces.Database
import kotlinx.coroutines.Dispatchers

class DatabaseModule : Database {
    override fun getDatabaseFile(currentProject: Project): AppDatabase {
        val db =
            Room
                .databaseBuilder<AppDatabase>(currentProject.getDbPath())
                .addMigrations()
                .setQueryCoroutineContext(Dispatchers.IO)
                .setDriver(BundledSQLiteDriver())
                .build();
        return db;
    }
}
