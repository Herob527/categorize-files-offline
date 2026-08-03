package com.example.transcripttest.dataclasses

data class ProjectsList(
    var currentProject: Project? = null,
    val paths: List<Project>
)
