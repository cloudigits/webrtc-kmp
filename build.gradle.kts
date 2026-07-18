plugins {
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
}

allprojects {
    plugins.withId("maven-publish") {
        extensions.configure<PublishingExtension> {
            repositories {
                maven(rootProject.layout.buildDirectory.dir("mvn-repo")) {
                    name = "localRepo"
                }
            }
        }
    }

    tasks.withType<Sign>().configureEach {
        enabled = false
    }
}
