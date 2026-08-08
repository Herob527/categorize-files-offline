import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room3)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)
}

compose.desktop {

    room3 {
        schemaDirectory("$projectDir/schemas")
    }
    application {
        mainClass = "com.example.transcripttest.MainKt"
        jvmArgs("-Dcompose.reload.devToolsEnabled=false")
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.AppImage)
            packageName = "com.example.transcripttest"
            packageVersion = "1.0.0"
        }
    }
}
