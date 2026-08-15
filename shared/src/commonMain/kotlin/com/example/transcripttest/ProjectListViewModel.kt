package com.example.transcripttest

import androidx.lifecycle.ViewModel
import com.example.transcripttest.dataclasses.Project
import com.example.transcripttest.dataclasses.ProjectsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProjectListViewModel : ViewModel() {
    private val _projectListState = MutableStateFlow(ProjectsList())
    val projectListState = _projectListState.asStateFlow()

    suspend fun setProject(newProject: Project) {
        _projectListState.value
            .apply {
                currentProject = newProject
                paths += newProject
            }.let {
                _projectListState.emit(it)
            }
    }

    suspend fun removeProject(project: Project) {
        _projectListState.value.apply {
            if (currentProject == project) {
                currentProject = null
            }
            paths -= project
        }.let {
            _projectListState.emit(it)
        }
    }

}