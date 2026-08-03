package com.example.transcripttest.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val audioPath: String = "audio",
    val name: String = "unnamed",
    val dbName: String = "project.db",
    val lastExportConfig: ExportConfig?
)
