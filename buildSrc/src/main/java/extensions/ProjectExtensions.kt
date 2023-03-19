package extensions

import com.android.build.gradle.BaseExtension
import dependencies.Versions
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureAndroid() = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(Versions.COMPILE_SDK)
    buildToolsVersion(Versions.ANDROID_BUILD_TOOLS)
    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures.compose = true

    composeOptions.kotlinCompilerExtensionVersion = "1.3.2"

    configurations.all {
        resolutionStrategy.eachDependency {
            resolutionStrategy.eachDependency {
                when (requested.name) {
                    "javapoet" -> useVersion("1.13.0")
                }
            }
            val requested = this.requested
            if (requested.group == "org.jetbrains.kotlin" && requested.name == "kotlin-reflect") {
                this.useVersion(Versions.KOTLIN_VERSION)
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        disable.add("Instantiatable")
    }
}.apply {
    this@configureAndroid.tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview"
            )
        }
    }

    this@configureAndroid.dependencies {
        androidBase()
    }
}
