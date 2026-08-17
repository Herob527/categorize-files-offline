package com.example.transcripttest.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.transcripttest.ProjectListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: ProjectListViewModel = koinViewModel(),
) {
    val state by viewModel.projectListState.collectAsState()
    return Column {
        Text("Dashboard ${state.currentProject?.absoluteRootPath}" )
    }
}
