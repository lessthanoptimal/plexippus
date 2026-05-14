plugins {
    id("plexippus.java-conventions")
    id("com.peterabeles.gversion")
    `maven-publish`
}

// Force the release build to fail if it depends on a SNAPSHOT
project.tasks.named("jar") { dependsOn("checkDependsOnSNAPSHOT") }

// Force publish to fail if trying to upload a stable release and git is dirty
project.tasks.named("publish") { dependsOn("failDirtyNotSnapshot") }

