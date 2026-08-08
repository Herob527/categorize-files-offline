package com.example.transcripttest.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.awt.AwtWindow
import com.example.transcripttest.ProjectViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.awt.FileDialog
import java.awt.Frame

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FileDialogC(
    parent: Frame? = null,
    onCloseRequest: (result: String?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(file)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)
@Composable
fun StartupScreen() {
    val viewModel = koinViewModel<ProjectViewModel>()
    val test = viewModel.projectState.collectAsState()
    Column {
        Text("Startup")
    }
    FileDialogC {}
}