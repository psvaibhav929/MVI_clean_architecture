
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.mvi.features"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}


dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":features:features-animal-list"))
    implementation(project(":features:features-animal-details"))
    implementation(Dep.coreKtx)
    implementation(Dep.lifecycleRuntime)
    implementation(Dep.coil)
    implementation(Dep.splashScreen)

    compose()
    retrofit()
    daggerHilt()
    test()
    androidTest()
}