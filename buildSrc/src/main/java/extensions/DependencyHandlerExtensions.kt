package extensions

import dependencies.Dependencies
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.exclude

fun DependencyHandler.androidBase() {
    implementation(Dependencies.CORE)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.SPLASHSCREEN)
    implementation(Dependencies.GSON)
    ads()
    inAppReview()
    material()
    compose()
    navigationCompose()
}

fun DependencyHandler.inAppReview() {
    implementation(Dependencies.REVIEW)
    implementation(Dependencies.REVIEW_KTX)
}

fun DependencyHandler.ads() {
    implementation(Dependencies.PLAY_SERVICES_ADS)
}

fun DependencyHandler.material() {
    implementation(Dependencies.MATERIAL3)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.UI_TOOLING)
    // DEBUG
    debugImplementation(Dependencies.UI_TOOLING_COMPOSE_DEBUG)
    debugImplementation(Dependencies.UI_TEST_MANIFEST_COMPOSE_DEBUG)
}

fun DependencyHandler.navigationCompose() {
    implementation(Dependencies.NAVIGATION_COMPOSE)
}

fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

fun DependencyHandler.implementationProject(project: ProjectDependency) {
    add("implementation", project)
}

fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}

fun DependencyHandler.releaseImplementation(depName: String) {
    add("releaseImplementation", depName)
}

fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

fun DependencyHandler.annotationProcessor(depName: String) {
    add("annotationProcessor", depName)
}

fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

fun DependencyHandler.kaptTest(depName: String) {
    add("kaptTest", depName)
}

fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

fun DependencyHandler.runtimeOnly(depName: String) {
    add("runtimeOnly", depName)
}

fun DependencyHandler.runtimeOnly(depName: String, group: String, module: String) {
    add("runtimeOnly", depName) {}.exclude(group, module)
}
