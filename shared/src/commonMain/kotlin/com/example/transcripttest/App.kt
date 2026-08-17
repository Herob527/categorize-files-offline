package com.example.transcripttest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import org.koin.compose.viewmodel.koinViewModel

import com.example.transcripttest.ui.components.Sidebar

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

    val projectListViewModel = koinViewModel<ProjectListViewModel>()

    val projectList by projectListViewModel.projectListState.collectAsState()
    Scaffold { innerPadding ->
        Column {
            Row(
                modifier = Modifier
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