package com.example.transcripttest.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class ExportConfig(
    val shouldZip: Boolean = false,
    val uncategorizedAudiosName: String,
    /**
     * If false, prompt user (allow them to check if it should be remembered)
     * If true, overwrite without question
     */
    val shouldOverwriteExport: Boolean = false,
    val shouldExportTranscript: Boolean = true,
    /**
     * If true - every audio and texts will be in own directory according to category name
     * If false - all audios and texts will be in one directory
     *
     * In both cases [audioDirectory] still applies
     */
    val divideByCategory: Boolean = true,
    val omitEmptyTexts: Boolean = false,
    /**
     * If null, transcript and audios will be one dir
     * If not null, audio will get own directory and audioPath in [transcriptFormat] will be prepended with path if it's not
     */
    val audioDirectory: String?,
    /**
     * Planned supported formats
     *
     * audioPath
     * text
     * categoryName
     * categoryIndex
     * duration
     */
    val transcriptFormat: String = "{audioPath}|{text}",
)
