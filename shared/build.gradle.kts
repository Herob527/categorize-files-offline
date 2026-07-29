@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "2.0.21"
}

kotlin {
    jvm()


    val roomVersion = "2.8.4"
    val nav_version = "2.9.2"

    sourceSets {
        dependencies {
        }
        commonMain.dependencies {
            ksp {
                implementation("androidx.room:room-compiler:$roomVersion")
            }
            implementation( "androidx.room:room-runtime:${roomVersion}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:${nav_version}")

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}