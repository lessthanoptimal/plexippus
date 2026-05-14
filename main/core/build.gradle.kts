// Copyright (c) 2026 NINOX 360 LLC. Licensed under the BSD 3-Clause; see LICENSE.TXT in project root

plugins {
    id("plexippus.libs-conventions")
}

tasks.getByName<Task>("createVersionFile") { enabled = true }

project.tasks.compileKotlin {
    dependsOn(project.tasks.createVersionFile)
}

gversion {
    srcDir = "src/main/java"
    classPackage = "org.plexippus"
    className = "PlexippusVersion"
    language = "java"
    indent = "    "
    annotate = true
}

dependencies {

}
