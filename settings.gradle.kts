rootProject.name = "Plexippus"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("autocode", "main:core")

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }
