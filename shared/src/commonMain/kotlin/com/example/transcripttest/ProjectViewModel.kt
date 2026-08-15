package com.example.transcripttest

import androidx.lifecycle.ViewModel
import com.example.transcripttest.dataclasses.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProjectViewModel : ViewModel() {
    private val _projectState = MutableStateFlow(Project())
    val projectState = _projectState.asStateFlow()

    fun updateProject(project: Project) {
        _projectState.update { currentProject -> currentProject.let { project } }

    }
}