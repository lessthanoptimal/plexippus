package org.plexippus.core;

public interface BlasLevel1 {
    /// y := alpha \* x + y
    ///
    /// @param n number of elements to process (n ≤ 0 is a no-op)
    /// @param alpha scalar multiplier; if 0.0 the routine returns immediately
    /// @param x source vector
    /// @param xOffset starting index into x
    /// @param incx stride between elements of x; may be negative
    /// @param y destination vector, updated in place
    /// @param yOffset starting index into y
    /// @param incy stride between elements of y; may be negative
    void daxpy( int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy );
}
