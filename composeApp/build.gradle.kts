import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization") version "1.9.10"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
    
        
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.androidx.activity.compose)
                implementation(project.dependencies.platform(libs.koin.bom))


                // Ktor
                implementation(libs.ktor.client.okhttp)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                // navigation voyager
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.bottomSheetNavigator)
                implementation(libs.voyager.koin)
                implementation(libs.voyager.screenModel)
                implementation(libs.voyager.tabNavigator)
                implementation(libs.voyager.transitions)

                // DI koin
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.annotations)
                
                // kotlin coroutines
                implementation(libs.coroutines)
                
                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.encoding)
                
                // Image Loader
                implementation(libs.coil)
                implementation(libs.coil.okhttp)
                implementation(libs.coil.bom)
            }
        }
        
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

// Ensure all Kotlin compile tasks depend on kspCommonMainKotlinMetadata for prior code processing.
tasks.withType<KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}


// Make all SourcesJar tasks depend on kspCommonMainKotlinMetadata.
afterEvaluate {
    tasks.filter { task: Task ->
        task.name.contains("SourcesJar", true)
    }.forEach {
        it.dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "com.shageldi.pexels"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.shageldi.pexels"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    
    applicationVariants.all {
        sourceSets {
            getByName("main") {
                java.srcDir(File("build/generated/ksp/commonMain/kotlin"))
            }
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation(libs.koin.core)
        implementation(libs.koin.android)
        implementation(libs.koin.annotations)
        ksp(libs.koin.ksp.compiler)
    }
}

