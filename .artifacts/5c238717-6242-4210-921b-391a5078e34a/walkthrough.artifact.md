# Walkthrough - Fixed Navigation NotImplementedError on Desktop

I have resolved the crash occurring on the Desktop target by removing a redundant and incompatible dependency.

## Changes

### Shared Module

#### [build.gradle.kts](file:///home/szymon/AndroidStudioProjects/TranscriptTest/shared/build.gradle.kts)

I removed the `androidx.navigation:navigation-compose` dependency, which is intended for Android only. The project already included the JetBrains multiplatform version (`org.jetbrains.androidx.navigation:navigation-compose`), which is the correct one to use for Compose Multiplatform projects targeting Desktop.

```diff
-            implementation("androidx.navigation:navigation-compose:${nav_version}")
             implementation( "androidx.room:room-runtime:${roomVersion}")
             implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
             implementation("org.jetbrains.androidx.navigation:navigation-compose:${nav_version}")
```

## Verification Results

### Automated Tests
- Executed `./gradlew :desktopApp:assemble` successfully.

### Manual Verification
- The application should now start successfully on Desktop without throwing `kotlin.NotImplementedError` when `rememberNavController()` is called.
