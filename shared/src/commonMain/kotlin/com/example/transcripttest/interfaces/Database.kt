package com.example.transcripttest.interfaces

import com.example.transcripttest.data.local.AppDatabase
import com.example.transcripttest.dataclasses.Project

interface Database {
    fun getDatabaseFile(currentProject: Project): AppDatabase
}