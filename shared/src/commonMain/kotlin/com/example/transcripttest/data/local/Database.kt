package com.example.transcripttest.data.local

import androidx.room3.Database
import com.example.transcripttest.data.local.entity.Audio
import com.example.transcripttest.data.local.entity.Category
import com.example.transcripttest.data.local.entity.Transcript
import com.example.transcripttest.data.local.entity.TranscriptAudio
import com.example.transcripttest.data.local.entity.TranscriptCategory

@Database(
    entities = [
        Transcript::class,
        Category::class,
        Audio::class,
        TranscriptCategory::class,
        TranscriptAudio::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase {}