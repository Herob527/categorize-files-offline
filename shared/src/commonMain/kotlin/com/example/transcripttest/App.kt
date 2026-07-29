package com.example.transcripttest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    Scaffold { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)) {
            Column {
                Routes.entries.forEach {
                    TextButton(onClick = { navController.navigate(it.name) }) {
                        Text(it.title)
                    }
                }
            }
            NavHost(

                navController = navController,
                startDestination = Routes.Startup.name,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                composable(route = Routes.Startup.name) { StartupScreen() }
                composable(route = Routes.Transcript.name) { TranscriptScreen() }
                composable(route = Routes.Export.name) { ExportScreen() }

            }
        }
    }

}