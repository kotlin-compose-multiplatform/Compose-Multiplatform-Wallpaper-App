rootProject.name = "Pexels"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
    plugins {
        val kspVersion = extra["ksp.version"] as String
        id("com.google.devtools.ksp").version(kspVersion)
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")