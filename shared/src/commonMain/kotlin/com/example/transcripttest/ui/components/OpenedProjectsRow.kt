package com.example.transcripttest.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BrushPainter
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

    if (projectList.openedProjects.isEmpty()) return


    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        item {
            TextButton(
                onClick = {
                    navigator.navigateSingleTop(Route.Startup)
                },
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .defaultMinSize(0.dp, 0.dp),
                shape = RectangleShape,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                contentPadding = PaddingValues.Zero,
            ) {
                Text("+", fontSize = 12.sp)
            }
        }
        projectList.openedProjects.forEach {
            val isSelected = projectList.currentProject == it
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    TextButton(
                        modifier = Modifier.height(24.dp).defaultMinSize(0.dp, 0.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                            contentColor = if (isSelected) MaterialTheme.colorScheme.surface else Color.Unspecified,
                        ),
                        onClick = {
                            viewModel.setProject(it)
                            navigator.navigate(Route.Dashboard(it))
                        }) {
                        Text(it.absoluteRootPath.toPath().name, fontSize = 10.sp)

                    }

                    TextButton(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .defaultMinSize(0.dp, 0.dp),
                        shape = RectangleShape,
                        contentPadding = PaddingValues.Zero,
                        colors = ButtonDefaults.textButtonColors(containerColor = Color.Red),
                        onClick = { viewModel.removeProject(it) },
                    ) {
                        Text("X", fontSize = 10.sp, color = Color.White)
                    }
                }

            }
        }
    }
}
