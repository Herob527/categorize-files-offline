package com.example.transcripttest.di

import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

actual val platformModule = module {
    single<DatabaseModule>()
}
