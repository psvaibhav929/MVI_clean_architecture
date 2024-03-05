
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply<MainGradlePlugin>()

android {
    namespace = "com.lloyd.common"
}


dependencies {
    implementation(Dep.coreKtx)
    daggerHilt()
    test()
}