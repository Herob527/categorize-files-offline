package com.example.transcripttest

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.ui.NavDisplay
import com.example.transcripttest.di.initKoin
import com.example.transcripttest.navigation.Navigator
import com.example.transcripttest.ui.components.OpenedProjectsRow
import com.example.transcripttest.ui.components.Sidebar
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    remember { initKoin() }
    val navigator = koinInject<Navigator>()
    val backStack = navigator.backStack
    val currentScreen = backStack.lastOrNull() ?: Route.Startup

    Scaffold { innerPadding ->
        Column {
            OpenedProjectsRow()
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Sidebar(
                    currentScreen = currentScreen
                )
                NavDisplay(
                    backStack = backStack,
                    onBack = { navigator.pop() },
                    entryProvider = koinEntryProvider(),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding + PaddingValues(8.dp)),
                    transitionSpec = {
                        (fadeIn(
                            animationSpec = tween(500)
                        )) togetherWith
                                (fadeOut(
                                    animationSpec = tween(500)
                                ))
                    },
                    popTransitionSpec = {
                        (fadeIn(
                            animationSpec = tween(500)
                        )) togetherWith
                                (fadeOut(
                                    animationSpec = tween(500)
                                ))
                    }
                )
            }
        }
    }
}
