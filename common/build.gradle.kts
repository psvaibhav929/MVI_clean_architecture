
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.mvi.common"
}


dependencies {
    implementation(Dep.coreKtx)
    compose()
    daggerHilt()
    test()
}