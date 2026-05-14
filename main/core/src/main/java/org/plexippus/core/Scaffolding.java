package org.plexippus.core;

import lombok.Getter;
import lombok.Setter;

public class Scaffolding {
    @Getter @Setter private Workspace workspace;
    @Getter @Setter private BlasLevel1 level1;
    @Getter @Setter private BlasLevel2 level2;
    @Getter @Setter private BlasLevel3 level3;

    @Getter @Setter private Decompositions decompositions;
    @Getter @Setter private Solvers solvers;

}
