// Copyright (c) 2026 NINOX 360 LLC. Licensed under the BSD 3-Clause; see LICENSE.TXT in project root

plugins {
    id("plexippus.java-conventions")
    id("com.peterabeles.gversion")
    `maven-publish`
}

// Force the release build to fail if it depends on a SNAPSHOT
project.tasks.named("jar") { dependsOn("checkDependsOnSNAPSHOT") }

// Force publish to fail if trying to upload a stable release and git is dirty
project.tasks.named("publish") { dependsOn("failDirtyNotSnapshot") }

