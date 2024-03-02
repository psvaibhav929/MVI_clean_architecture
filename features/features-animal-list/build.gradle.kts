plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()


android {
    namespace = "com.lloyd.features_animal_list"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}


dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(Dep.coreKtx)
    implementation(Dep.lifecycleRuntime)
    implementation(Dep.coil)
    implementation(Retrofit.retrofitGson)

    compose()

    daggerHilt()
    test()
    androidTest()
}