// Copyright (c) 2026 NINOX 360 LLC. Licensed under the BSD 3-Clause; see LICENSE.TXT in project root

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

// While this project is Java 25, we want to build using older versions of Java. Hence, Java 17 here.
kotlin {jvmToolchain(17)}

val spotlessVersion = libs.versions.spotless.get()
val gversionVersion = libs.versions.gversion.get()

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    implementation("com.peterabeles.gversion:com.peterabeles.gversion.gradle.plugin:$gversionVersion")
    implementation(kotlin("gradle-plugin", libs.versions.kotlin.get()))
}
