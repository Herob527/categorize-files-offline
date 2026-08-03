package com.example.transcripttest.di

import com.example.transcripttest.interfaces.Database
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

actual val platformModule = module {
    single<DatabaseModule>() bind Database::class
}
