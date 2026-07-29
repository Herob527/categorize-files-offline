# Fix Navigation NotImplementedError on Desktop

The application fails to run on Desktop with a `kotlin.NotImplementedError: Implemented only in JetBrains fork.` This error occurs because both the Google version and the JetBrains fork of the Compose Navigation library are declared as dependencies in the `shared` module. For non-Android targets (like Desktop), only the JetBrains fork (`org.jetbrains.androidx.navigation`) should be used, as it provides the necessary multiplatform implementations.

## User Review Required

> [!NOTE]
> I will be removing the `androidx.navigation:navigation-compose` dependency from the `shared` module. This project currently only has a Desktop target, so this dependency was likely added by mistake or as a duplicate of the JetBrains multiplatform version.

## Proposed Changes

### Shared Module

#### [MODIFY] [build.gradle.kts](file:///home/szymon/AndroidStudioProjects/TranscriptTest/shared/build.gradle.kts)
- Remove the redundant and Android-only `androidx.navigation:navigation-compose` dependency.
- Keep only the JetBrains multiplatform `org.jetbrains.androidx.navigation:navigation-compose` dependency.

## Verification Plan

### Automated Tests
- I will run `./gradlew :desktopApp:assemble` to ensure the project still builds.

### Manual Verification
- The user should try running the application again using `hotRun` or the standard run configuration to verify that the `rememberNavController()` call no longer throws the `NotImplementedError`.
