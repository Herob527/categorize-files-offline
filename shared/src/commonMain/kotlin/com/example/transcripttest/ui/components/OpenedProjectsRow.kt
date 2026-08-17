package com.example.transcripttest.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.Route
import com.example.transcripttest.navigation.Navigator
import io.github.vinceglb.filekit.utils.toPath
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OpenedProjectsRow(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: ProjectListViewModel = koinViewModel(),
) {
    val projectList by viewModel.projectListState.collectAsState()

    Row(
        modifier = modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        projectList.openedProjects.forEach {
            Button(onClick = {
                viewModel.setProject(it)
                navigator.navigate(Route.Dashboard(it))
            }) {
                Text(it.absoluteRootPath.toPath().name)

            }

        }
        IconButton(
            onClick = {
                navigator.navigateSingleTop(Route.Startup)
            },
            modifier = Modifier.size(16.dp)
        ) {
            Text("+", fontSize = 12.sp)
        }
    }
}
