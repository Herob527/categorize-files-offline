package com.example.transcripttest.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

expect val platformModule: Module

fun initKoin(
    config: KoinAppDeclaration? = null,
) = startKoin {
    includes(config)
    modules(
        platformModule
    )
}