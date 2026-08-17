package com.example.transcripttest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.transcripttest.di.initKoin
import com.example.transcripttest.screens.DashboardScreen
import com.example.transcripttest.screens.ExportScreen
import com.example.transcripttest.screens.StartupScreen
import com.example.transcripttest.screens.TranscriptScreen

import com.example.transcripttest.ui.components.Sidebar
import com.example.transcripttest.ui.components.OpenedProjectsRow

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.Startup.name
    )
    remember { initKoin() }
    Scaffold { innerPadding ->
        Column {
            OpenedProjectsRow(navController = navController)
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Sidebar(
                    navController = navController,
                    currentScreen = currentScreen
                )
                NavHost(
                    navController = navController,
                    startDestination = Routes.Startup.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding + PaddingValues(8.dp))
                ) {
                    composable(route = Routes.Startup.name) { StartupScreen(navController) }
                    composable(route = Routes.Dashboard.name) { DashboardScreen() }
                    composable(route = Routes.Transcript.name) { TranscriptScreen() }
                    composable(route = Routes.Export.name) { ExportScreen() }

                }
            }
        }
    }

}