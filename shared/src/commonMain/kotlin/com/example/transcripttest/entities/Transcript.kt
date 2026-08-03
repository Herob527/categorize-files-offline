package com.example.transcripttest.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class Transcript(
    @PrimaryKey val id: Int,
    val text: String
)
