import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "2.0.21"

}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

}

compose.desktop {
    application {
        mainClass = "com.example.transcripttest.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.transcripttest"
            packageVersion = "1.0.0"
        }
    }
}
