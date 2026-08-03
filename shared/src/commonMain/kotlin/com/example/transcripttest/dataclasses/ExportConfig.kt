package com.example.transcripttest.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class ExportConfig(
    val shouldZip: Boolean,
    val divideByCategory: Boolean,
    val omitEmpty: Boolean,
    val exportText: String,
    val textFormat: String,
)
