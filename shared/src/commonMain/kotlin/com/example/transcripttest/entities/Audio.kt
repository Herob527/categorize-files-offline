package com.example.transcripttest.entities

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import kotlin.time.Duration

@Entity(
    indices = [
        Index("path", unique = true)
    ]
)
data class Audio(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val path: String,
    val duration: Duration
)
