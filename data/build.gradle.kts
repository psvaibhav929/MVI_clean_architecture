plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.mvi.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(Dep.coreKtx)
    // Retrofit for typesafe API calls
    retrofit()
    //DI - Dagger Hilt
    daggerHilt()
    test()
}