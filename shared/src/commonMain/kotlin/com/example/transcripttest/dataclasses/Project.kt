package com.example.transcripttest.dataclasses

import kotlinx.serialization.Serializable
import kotlin.io.path.Path

@Serializable
data class Project(
    val audioPath: String = "audio",
    val absoluteRootPath: String = "",
    val dbName: String = "project.db",
    val lastExportConfig: ExportConfig?,
)
