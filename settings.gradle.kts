pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LLoyd"
include(":app")
include(":common")
include(":domain")
include(":data")
include(":features")
include(":features:features-animal-list")
include(":features:features-animal-details")
