package com.example.transcripttest.data.local.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class Transcript(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val text: String
)
