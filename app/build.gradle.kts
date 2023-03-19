import extensions.androidBase
import extensions.configureAndroid

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

configureAndroid()

dependencies {
    rootProject.subprojects {
        if (this.name.contains("core") || this.name.contains("impl") || this.name.contains("api")) {
            implementation(this)
        }
    }
    androidBase()
}
