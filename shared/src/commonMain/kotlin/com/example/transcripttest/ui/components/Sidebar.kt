package com.example.transcripttest.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.visible
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.Route
import com.example.transcripttest.navigation.Navigator
import com.example.transcripttest.sidebarRoutes
import com.example.transcripttest.title
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

    Column(
        modifier = modifier
            .fillMaxHeight()
            .visible(projectList.currentProject != null)
            .width(100.dp)
            .drawBehind {
                val strokeWidth = 1 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.Black,
                    Offset(size.width + 1, 0f),
                    Offset(size.width + 1, y),
                    strokeWidth
                )
            }) {
        sidebarRoutes.forEach { route ->
            TextButton(
                shape = RectangleShape,
                onClick = { navigator.navigateSingleTop(route) },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (currentScreen == route) MaterialTheme.colorScheme.primary else Color.Unspecified,
                    contentColor = if (currentScreen == route) MaterialTheme.colorScheme.surface else Color.Unspecified,
                )
            ) {
                Text(
                    route.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize(),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
