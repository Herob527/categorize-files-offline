package com.example.transcripttest.di

import com.example.transcripttest.ProjectListViewModel
import com.example.transcripttest.Route
import com.example.transcripttest.navigation.Navigator
import com.example.transcripttest.screens.DashboardScreen
import com.example.transcripttest.screens.ExportScreen
import com.example.transcripttest.screens.StartupScreen
import com.example.transcripttest.screens.TranscriptScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.plugin.module.dsl.viewModel

expect val platformModule: Module

@OptIn(KoinExperimentalAPI::class)
fun initKoin(
    config: KoinAppDeclaration? = null,
) = startKoin {
    includes(config)
    logger(PrintLogger(Level.DEBUG))
    modules(
        platformModule,
        module {
            single { Navigator(Route.Startup) }
            viewModel<ProjectListViewModel>()

            navigation<Route.Startup> { StartupScreen() }
            navigation<Route.Dashboard> { DashboardScreen() }
            navigation<Route.Transcript> { TranscriptScreen() }
            navigation<Route.Export> { ExportScreen() }
        }
    )
}
