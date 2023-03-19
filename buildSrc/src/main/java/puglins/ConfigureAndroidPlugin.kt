package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import extensions.configureAndroid

open class ConfigureAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("com.android.application")
        target.plugins.apply("kotlin-android")
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("kotlin-kapt")
        target.configureAndroid()
    }
}
