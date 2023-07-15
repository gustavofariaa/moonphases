import extensions.androidBase
import extensions.configureAndroid

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
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

android {
    namespace = "com.gustavofaria.moonphases"
}
