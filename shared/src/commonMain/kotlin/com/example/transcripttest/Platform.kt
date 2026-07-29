package com.example.transcripttest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform