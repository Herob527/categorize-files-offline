package com.example.transcripttest.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class ProjectsList(
    var currentProject: Project? = null,
    var existingProjects: List<Project> = listOf(),
    var openedProjects: List<Project> = listOf()
)
