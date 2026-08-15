package com.example.transcripttest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.Ruler
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowPosition.PlatformDefault.x
import androidx.compose.ui.window.WindowPosition.PlatformDefault.y
import androidx.compose.ui.zIndex
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
                        Button(
                            onClick = { print("test2") },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            modifier = Modifier
                                .zIndex(2f)
                                .padding(0.dp)
                                .width(24.dp)
                                .height(24.dp)
                        ) {
                            Text("X", fontSize = 12.sp, modifier = Modifier.background(Color.White))
                        }
                        Button(
                            onClick = { print("test4") },
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