package com.example.transcripttest.entities

import androidx.room3.Embedded
import androidx.room3.Entity
import androidx.room3.ForeignKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Audio::class,
            parentColumns = ["id"],
            childColumns = ["audioId"]
        ),
        ForeignKey(
            entity = Transcript::class,
            parentColumns = ["id"],
            childColumns = ["transcriptId"]
        )
    ]
)
data class TranscriptAudio(
    val audioId: Int,
    val transcriptId: Int,

    @Embedded val audio: Audio,
    @Embedded val transcript: Transcript
)
