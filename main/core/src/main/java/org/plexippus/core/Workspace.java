package org.plexippus.core;

public interface Workspace {
    DoubleLease leaseDoubles(int minSize);

    interface DoubleLease extends AutoCloseable {
        double[] array();
        int size();   // logical size requested, not array.length
        @Override void close();  // no checked exception
    }
}
