import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class MainGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugin(project)
        setProjectConfig(project)
    }

    private fun applyPlugin(project: Project) {
        project.apply {
            plugin("kotlin-parcelize")
            plugin("kotlin-kapt")
        }

    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
        compileSdk = ProjectConfig.compileSdk

            defaultConfig{
                minSdk = ProjectConfig.minSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.composeCompiler
            }

        }

    }


    private fun Project.android(): LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }

}
