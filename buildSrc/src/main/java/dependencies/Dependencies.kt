package dependencies

object Dependencies {
    // ANDROID BASE
    const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME}"
    const val SPLASHSCREEN = "androidx.core:core-splashscreen:${Versions.SPLASHSCREEN}"

    // ADS
    const val PLAY_SERVICES_ADS =
        "com.google.android.gms:play-services-ads:${Versions.PLAY_SERVICES_ADS}"

    // IN APP REVIEW
    const val REVIEW = "com.google.android.play:review:${Versions.REVIEW}"
    const val REVIEW_KTX = "com.google.android.play:review-ktx:${Versions.REVIEW}"

    // MATERIAL
    const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"

    // COMPOSE
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"

    // NAVIGATION
    const val NAVIGATION_COMPOSE =
        "androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE}"

    // GSON
    const val GSON =
        "com.google.code.gson:gson:${Versions.GSON}"

    // COMPOSE DEBUG
    const val UI_TOOLING_COMPOSE_DEBUG = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val UI_TEST_MANIFEST_COMPOSE_DEBUG =
        "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
}
