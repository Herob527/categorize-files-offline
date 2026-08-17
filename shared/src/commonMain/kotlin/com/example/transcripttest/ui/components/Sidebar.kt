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
import androidx.navigation.NavHostController
import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Sidebar(
    navController: NavHostController,
    currentScreen: Routes,
    modifier: Modifier = Modifier,
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
        Routes.entries.minus(Routes.Startup).forEach {
            TextButton(
                shape = RectangleShape,
                onClick = { navController.navigate(it.name) },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (currentScreen.name == it.name) MaterialTheme.colorScheme.primary else Color.Unspecified,
                    contentColor = if (currentScreen.name == it.name) MaterialTheme.colorScheme.surface else Color.Unspecified,
                )
            ) {
                Text(
                    it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize(),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
