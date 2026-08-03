package com.example.transcripttest.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.time.Duration

@Entity
data class Audio(
    @PrimaryKey val id: Int,
    val path: String,
    val duration: Duration
)
