package com.example.transcripttest.entities

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.ForeignKey.Companion.RESTRICT

@Entity(
    primaryKeys = ["categoryId", "transcriptId"],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
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
data class TranscriptCategory(
    val categoryId: Int,
    val transcriptId: Int,
)
