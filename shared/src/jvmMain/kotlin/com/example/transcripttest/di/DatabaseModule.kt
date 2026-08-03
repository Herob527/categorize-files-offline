package com.example.transcripttest.di

import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.transcripttest.data.local.AppDatabase
import com.example.transcripttest.dataclasses.Project
import com.example.transcripttest.interfaces.Database
import kotlinx.coroutines.Dispatchers
import kotlin.io.path.Path

class DatabaseModule : Database {
    override fun getDatabaseFile(currentProject: Project): AppDatabase {
        val pathToDb = Path(currentProject.absoluteRootPath, currentProject.dbName).toString()
        val db =
            Room
                .databaseBuilder<AppDatabase>(pathToDb)
                .addMigrations()
                .setQueryCoroutineContext(Dispatchers.IO)
                .setDriver(BundledSQLiteDriver())
                .build();
        return db;
    }
}
