plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.mvi.domain"
}

dependencies {
    implementation(project(":common"))
    implementation(Retrofit.retrofitGson)
    daggerHilt()
    test()
}