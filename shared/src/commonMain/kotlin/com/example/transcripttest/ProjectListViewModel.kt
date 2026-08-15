package com.example.transcripttest

import androidx.lifecycle.ViewModel
import com.example.transcripttest.dataclasses.Project
import com.example.transcripttest.dataclasses.ProjectsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProjectListViewModel : ViewModel() {
    private val _projectListState = MutableStateFlow(ProjectsList())
    val projectListState = _projectListState.asStateFlow()

    fun setProject(newProject: Project) = _projectListState
        .update { currentState ->
            ProjectsList(
                currentProject = newProject,
                paths = currentState.paths.plus(newProject)
            )
        }

    fun removeProject(project: Project) = _projectListState
        .update { currentState ->
            ProjectsList(
                currentProject = if (project == currentState.currentProject) null else currentState.currentProject,
                paths = currentState.paths.minus(project)
            )
        }

}