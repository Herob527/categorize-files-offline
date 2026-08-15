plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room3)
    alias(libs.plugins.koin.compiler)
}

kotlin {
    jvm()

    room3 {
        schemaDirectory("$projectDir/schemas")
    }
    sourceSets {
        commonMain.dependencies {
            val version = "0.15.0"
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.annotations)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization)
            implementation(libs.androidx.room3.runtime)
            implementation(libs.androidx.sqlite.bundled)
            implementation("io.github.vinceglb:filekit-core:${version}")
            implementation("io.github.vinceglb:filekit-dialogs:${version}")
            implementation("io.github.vinceglb:filekit-dialogs-compose:${version}")
            implementation("io.github.vinceglb:filekit-coil:${version}")

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