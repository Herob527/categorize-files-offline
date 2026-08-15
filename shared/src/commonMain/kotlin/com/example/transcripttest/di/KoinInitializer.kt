package com.example.transcripttest.di

import com.example.transcripttest.ProjectListViewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

expect val platformModule: Module

fun initKoin(
    config: KoinAppDeclaration? = null,
) = startKoin {
    includes(config)
    logger(PrintLogger(Level.DEBUG))
    modules(
        platformModule,
        module {
            viewModel<ProjectListViewModel>()
        }
    )
}