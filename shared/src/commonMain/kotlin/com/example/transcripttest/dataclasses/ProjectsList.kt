package com.example.transcripttest.dataclasses

data class ProjectsList(
    var currentProject: Project? = null,
    var paths: Set<Project> = setOf()
)
