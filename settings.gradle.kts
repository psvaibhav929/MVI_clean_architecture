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

rootProject.name = "MVI"
include(":app")
include(":common")
include(":domain")
include(":data")
include(":features:features-animal-list")
include(":features:features-animal-details")
include(":features")
