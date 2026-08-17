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

    fun setProject(project: Project) = _projectListState
        .update { currentState ->
            val existingProjects = if (project in currentState.existingProjects) {
                listOf(project, *currentState.existingProjects.minus(project).toTypedArray())
            } else {
                currentState.existingProjects.plus(project)
            }
            ProjectsList(
                currentProject = project,
                existingProjects = existingProjects,
                openedProjects = currentState.openedProjects.plus(project)
            )
        }

    fun removeProject(project: Project) = _projectListState
        .update {
            ProjectsList(
                currentProject = if (project == it.currentProject) null else it.currentProject,
                existingProjects = it.existingProjects - project,
                openedProjects = it.openedProjects - project
            )
        }

    fun closeProject(project: Project) = _projectListState.update {
        ProjectsList(
            currentProject = if (project == it.currentProject) null else it.currentProject,
            existingProjects = it.existingProjects,
            openedProjects = it.openedProjects - project

        )
    }

}