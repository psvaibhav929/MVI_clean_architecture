import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}
fun DependencyHandler.platformImplementation(dependency: String) {
    add("implementation", platform(dependency))
}

fun DependencyHandler.androidTest(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.androidTestPlatform(dependency: String) {
    add("androidTestImplementation", platform(dependency))
}

fun DependencyHandler.debugImplementations(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}
fun DependencyHandler.kaptAndroidTest(dependency: String) {
    add("kaptAndroidTest", dependency)
}