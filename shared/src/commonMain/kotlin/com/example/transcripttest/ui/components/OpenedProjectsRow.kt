package com.example.transcripttest.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OpenedProjectsRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ProjectListViewModel = koinViewModel(),
) {
    val projectList by viewModel.projectListState.collectAsState()

    Row(
        modifier = modifier
            .border(2.dp, Color.Black)
            .fillMaxWidth()
    ) {
        projectList.openedProjects.forEach {
            Text(it.dbName)
        }
        IconButton(
            onClick = {
                navController.navigate(Routes.Startup.name)
            },
            modifier = Modifier.size(16.dp)
        ) {
            Text("+", fontSize = 12.sp)
        }
    }
}
