package com.example.transcripttest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.dataclasses.Project
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.utils.toPath
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import transcripttest.shared.generated.resources.Res
import transcripttest.shared.generated.resources.cross

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
            if (it.paths.isEmpty()) {
                return Column {
                    Text("No recent projects")
                }
            }
            Grid(
                config = {
                    gap(8.dp)
                }
            ) {
                it.paths.forEach { project ->
                    Box(contentAlignment = Alignment.TopEnd) {
                        IconButton(
                            shape = RectangleShape,
                            modifier = Modifier
                                .zIndex(4f)
                                .size(20.dp),
                            onClick = { projectListViewModel.removeProject(project) },
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit,
                                painter = painterResource(Res.drawable.cross),
                                contentDescription = "Cross"
                            )
                        }
                        Button(
                            onClick = { projectListViewModel.setProject(project) },
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

}