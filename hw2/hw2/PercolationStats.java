package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] fractions;
    private int numTrails;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("N <= 0 or T <= 0");

        numTrails = T;
        fractions = new double[T];
        int totalSites = N * N;

        for (int i = 0; i < T; i++) {
            int numOpenedSites = 0;
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    numOpenedSites += 1;
                }
            }

            fractions[i] = (double) numOpenedSites / totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu - (1.96 * sigma / Math.sqrt(numTrails));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu + (1.96 / sigma / Math.sqrt(numTrails));
    }
}
