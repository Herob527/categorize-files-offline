package com.example.transcripttest.entities

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.ForeignKey.Companion.RESTRICT

@Entity(
    primaryKeys = ["audioId", "transcriptId"],
    foreignKeys = [
        ForeignKey(
            entity = Audio::class,
            parentColumns = ["id"],
            childColumns = ["audioId"],
            onUpdate = RESTRICT,
            onDelete = RESTRICT
        ),
        ForeignKey(
            entity = Transcript::class,
            parentColumns = ["id"],
            childColumns = ["transcriptId"],
            onUpdate = RESTRICT,
            onDelete = RESTRICT
        )
    ]
)
data class TranscriptAudio(
    val audioId: Int,
    val transcriptId: Int,
)
