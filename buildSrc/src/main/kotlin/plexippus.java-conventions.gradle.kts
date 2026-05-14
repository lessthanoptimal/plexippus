// Copyright (c) 2026 NINOX 360 LLC. Licensed under the BSD 3-Clause; see LICENSE.TXT in project root

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    kotlin("jvm")
    id("com.diffplug.spotless")
}

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
        vendor.set(JvmVendorSpec.AZUL)
    }
}

tasks.withType<JavaCompile> { options.encoding = "UTF-8" }

tasks.withType<Javadoc> {
    isFailOnError = false
    enabled = !project.version.toString().contains("SNAPSHOT") // disable to stop it from spamming stdout

    (options as StandardJavadocDocletOptions).apply {
        links = listOf(
            "https://docs.oracle.com/en/java/javase/25/docs/api/",
            "https://ejml.org/javadoc/",
        )
        addStringOption("-release", "25")
        addBooleanOption("html5", true)
    }
}

// Where to download dependencies from
repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://central.sonatype.com/repository/maven-snapshots/")
}

val libs = versionCatalogs.named("libs")

dependencies {
    // @Generated
    compileOnly(libs.findLibrary("javax-annotations").get())

    // Lombok reduces Java verbosity with annotations for accessors
    compileOnly(libs.findLibrary("lombok").get())
    annotationProcessor(libs.findLibrary("lombok").get())

    // Unit Test dependencies
    testImplementation(libs.findLibrary("junit5").get())
    testRuntimeOnly(libs.findLibrary("junit5-engine").get())
    testRuntimeOnly(libs.findLibrary("junit5-launcher").get())
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()

    // Make the error logging verbose to make debugging on CI easier
    testLogging.showStandardStreams = true
    testLogging.exceptionFormat = TestExceptionFormat.FULL
    testLogging.showCauses = true
    testLogging.showExceptions = true
    testLogging.showStackTraces = true
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    ratchetFrom("origin/master")

    format("misc") {
        // define the files to apply `misc` to
        target("*.gradle", "*.gradle.kts", "*.md", ".gitignore")

        // define the steps to apply to those files
        trimTrailingWhitespace()
        leadingTabsToSpaces()
        endWithNewline()
    }

    java {
        target("**/java//**/*.java")

        // Auto generated file that doesn't require copyright
        targetExclude("src/main/java/com/ninox360/refvins/ReferenceVinsVersion.java")

        toggleOffOn("formatter:off", "formatter:on")
        removeUnusedImports()
        trimTrailingWhitespace()
        leadingTabsToSpaces()
        endWithNewline()

        licenseHeaderFile("${project.rootDir}/docs/copyright-java.txt")
    }
}
