package com.example.transcripttest.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.Route
import com.example.transcripttest.navigation.Navigator
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Sidebar(
    currentScreen: Route,
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: ProjectListViewModel = koinViewModel()
) {
    val projectList by viewModel.projectListState.collectAsState()
    val project = projectList.currentProject
    val isVisible = project != null && currentScreen != Route.Startup
    val animatedWidth by animateDpAsState(targetValue = if (isVisible) 120.dp else 0.dp)

    val sidebarRoutes = if (isVisible) listOf(
        Route.Dashboard(project),
        Route.Transcript(project),
        Route.Export(project)
    ) else listOf()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(animatedWidth)
    ) {
        sidebarRoutes.forEach { route ->
            val isSelected = when (currentScreen) {
                is Route.Dashboard -> route is Route.Dashboard
                is Route.Transcript -> route is Route.Transcript
                is Route.Export -> route is Route.Export
                else -> false
            }
            TextButton(
                shape = RectangleShape,
                onClick = { navigator.navigateSingleTop(route) },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.surface else Color.Unspecified,
                )
            ) {
                Text(
                    route.title,
                    softWrap = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize(),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
    VerticalDivider(color = MaterialTheme.colorScheme.primary)
}
