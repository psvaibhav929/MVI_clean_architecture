import org.gradle.api.artifacts.dsl.DependencyHandler

object Dep {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKtx}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
}

object Retrofit {

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
}

object DaggerHilt {
    const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.2.0"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
}

object JetpackCompose {
    const val activityCompose = "androidx.activity:activity-compose:1.8.2"
    const val composeUI = "androidx.compose.ui:ui"
    const val composeBom = "androidx.compose:compose-bom:2023.03.00"
    const val composeUIGraphics = "androidx.compose.ui:ui-graphics"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeNavigation = "androidx.navigation:navigation-compose:2.7.7"
    const val composeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:2.7.0"
}

object AndroidTest {
    const val androidMockk = "io.mockk:mockk-android:1.13.2"
    const val jUnitExt = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4"
    const val composeBom = "androidx.compose:compose-bom:2023.03.00"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:2.45"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:2.44"
}

object Test {
    const val junit = "junit:junit:4.13.2"
    const val mockk = "io.mockk:mockk:1.13.2"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter:5.8.2"
}

fun DependencyHandler.androidTest() {
    androidTest(AndroidTest.androidMockk)
    androidTest(AndroidTest.jUnitExt)
    androidTest(AndroidTest.espresso)
    androidTest(AndroidTest.composeUiTest)
    androidTest(AndroidTest.hiltTesting)
    androidTestPlatform(AndroidTest.composeBom)
    debugImplementations(AndroidTest.composeUiTooling)
    debugImplementations(AndroidTest.uiTestManifest)
    kaptAndroidTest(AndroidTest.hiltCompiler)
}

fun DependencyHandler.compose() {
    implementation(JetpackCompose.activityCompose)
    platformImplementation(JetpackCompose.composeBom)
    implementation(JetpackCompose.composeUI)
    implementation(JetpackCompose.composeUIGraphics)
    implementation(JetpackCompose.composeToolingPreview)
    implementation(JetpackCompose.composeMaterial)
    implementation(JetpackCompose.composeNavigation)
    implementation(JetpackCompose.composeLifecycle)
}


fun DependencyHandler.retrofit() {
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitGson)
    implementation(Retrofit.okhttp3)
    implementation(Retrofit.okhttp3Logging)

}

fun DependencyHandler.test() {
    implementation(Test.junit)
    implementation(Test.junitJupiter)
    implementation(Test.coroutinesTest)
    implementation(Test.mockk)

}


//DI - Dagger Hilt
fun DependencyHandler.daggerHilt() {
    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltCompiler)
}


