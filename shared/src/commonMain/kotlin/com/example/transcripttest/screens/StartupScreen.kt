package com.example.transcripttest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.dataclasses.Project
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.utils.toPath
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalGridApi::class)
@Composable
fun StartupScreen() {
    val projectListViewModel = koinViewModel<ProjectListViewModel>()

    val launcher = rememberDirectoryPickerLauncher(
        onError = { failure ->
            // A valid directory operation could not be completed
            print(failure.message)
        },
        onResult = { directory ->
            if (directory == null) {
                // The user canceled the picker
            } else {
                Project(absoluteRootPath = directory.absolutePath()).let {
                    projectListViewModel.setProject(it)
                }
                // Handle the selected directory
            }
        },
    )
    val projectList = projectListViewModel.projectListState.collectAsState()

    Column {
        Text("Startup")
        Button(
            shape = RectangleShape,
            onClick = { launcher.launch() }
        ) {
            Text("Pick a directory")
        }
        projectList.value.let {
            Grid(
                config = {
                    gap(8.dp)
                }
            ) {
                it.paths.forEach { project ->
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(),
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            project.absoluteRootPath.toPath().name,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }

}