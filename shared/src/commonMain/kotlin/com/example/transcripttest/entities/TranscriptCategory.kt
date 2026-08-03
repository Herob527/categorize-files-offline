package com.example.transcripttest.entities

import androidx.room3.Embedded
import androidx.room3.Entity
import androidx.room3.ForeignKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        ),
        ForeignKey(
            entity = Transcript::class,
            parentColumns = ["id"],
            childColumns = ["transcriptId"]
        )
    ]
)
data class TranscriptCategory(
    val categoryId: Int,
    val transcriptId: Int,

    @Embedded val category: Category,
    @Embedded val transcript: Transcript
)
