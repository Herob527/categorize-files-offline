package com.example.transcripttest.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.transcripttest.ProjectViewModel
import com.example.transcripttest.dataclasses.Project
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import kotlinx.coroutines.Dispatchers
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StartupScreen() {
    val viewModel = koinViewModel<ProjectViewModel>()

    val launcher = rememberDirectoryPickerLauncher(
        onError = { failure ->
            // A valid directory operation could not be completed
            print(failure.message)
        },
        onResult = { directory ->
            if (directory == null) {
                // The user cancelled the picker
            } else {
                print(directory)
                viewModel.updateProject(Project(absoluteRootPath = directory.absolutePath()))
                // Handle the selected directory
            }
        },
    )
    val test = viewModel.projectState.collectAsState(Dispatchers.IO)

    Column {
        Text("Startup")
        Text(test.value.absoluteRootPath)
        Button(onClick = { launcher.launch() }) {
            Text("Pick a directory")
        }
    }
}