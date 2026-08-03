package com.example.transcripttest.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
