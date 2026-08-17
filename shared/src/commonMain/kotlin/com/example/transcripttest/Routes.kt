package com.example.transcripttest

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable data object Dashboard : Route
    @Serializable data object Transcript : Route
    @Serializable data object Export : Route
    @Serializable data object Startup : Route
}

// Keep a way to get title if needed, maybe extension or map
val Route.title: String
    get() = when (this) {
        Route.Dashboard -> "Dashboard"
        Route.Transcript -> "Transcript"
        Route.Export -> "Export"
        Route.Startup -> "Startup"
    }

// For sidebar iteration
val sidebarRoutes = listOf(Route.Dashboard, Route.Transcript, Route.Export)
