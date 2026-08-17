package com.example.transcripttest

import com.example.transcripttest.dataclasses.Project
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    abstract val title: String

    @Serializable
    data class Dashboard(val project: Project) : Route() {
        override val title: String = "Dashboard"
    }

    @Serializable
    data class Transcript(val project: Project) : Route() {
        override val title: String = "Transcript"
    }

    @Serializable
    data class Export(val project: Project) : Route() {
        override val title: String = "Export"
    }

    @Serializable
    data object Startup : Route() {
        override val title: String = "Startup"
    }
}
