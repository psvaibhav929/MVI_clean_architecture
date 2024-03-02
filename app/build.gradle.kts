plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.LLoyd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.LLoyd"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.LLoyd.HiltTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    hilt {
        enableTransformForLocalTests = true
    }
    packaging {
        resources {
            excludes.addAll(
                listOf(
                    "/META-INF/{AL2.0,LGPL2.1}",
                    "META-INF/DEPENDENCIES.txt",
                    "META-INF/LICENSE.txt",
                    "META-INF/NOTICE.txt",
                    "META-INF/NOTICE",
                    "META-INF/LICENSE",
                    "META-INF/DEPENDENCIES",
                    "META-INF/notice.txt",
                    "META-INF/license.txt",
                    "META-INF/dependencies.txt",
                    "META-INF/LGPL2.1",
                    "META-INF/services/org.xmlpull.v1.XmlPullParserFactory",
                    "MANIFEST.MF",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md",
                )
            )
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":data"))

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

