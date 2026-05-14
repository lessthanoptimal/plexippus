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
    api(libs.ejml.core)
}
