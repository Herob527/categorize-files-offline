package com.example.transcripttest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.transcripttest.di.initKoin
import com.example.transcripttest.screens.ExportScreen
import com.example.transcripttest.screens.StartupScreen
import com.example.transcripttest.screens.TranscriptScreen

enum class Routes(val title: String) {
    Startup("Startup"),
    Transcript("Transcript"),
    Export("Export")
}

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.Startup.name
    )
    remember { initKoin() }
    Scaffold { innerPadding ->
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
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
                    }
            ) {
                Routes.entries.forEach {
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
            NavHost(
                navController = navController,
                startDestination = Routes.Startup.name,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding + PaddingValues(8.dp))
            ) {
                composable(route = Routes.Startup.name) { StartupScreen() }
                composable(route = Routes.Transcript.name) { TranscriptScreen() }
                composable(route = Routes.Export.name) { ExportScreen() }

            }
        }
    }

}