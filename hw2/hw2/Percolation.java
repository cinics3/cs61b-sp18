package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int length;
    private int size;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N <= 0");

        grid = new int[N][N];
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N);
        length = N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = -1;
            }
        }
    }


    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= length && col < 0 || col >= length)
            throw new IndexOutOfBoundsException("row or col doesn't exist.");

        if (grid[row][col] == -1) {
            grid[row][col] = xyTo1D(row, col);
            size +=1;
        }

        // left right up down
        if (col - 1 >= 0 && isOpen(row , col - 1)) {
            weightedQuickUnionUF.union(grid[row][col - 1], grid[row][col]);
        }
        if (col + 1 < length && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(grid[row][col + 1], grid[row][col]);
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(grid[row - 1][col], grid[row][col]);
        }
        if (row + 1 < length && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(grid[row + 1][col], grid[row][col]);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (grid[row][col] == -1) return false;
        else return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isOpen(row,col))
        {
            for (int i = 0; i < length; i++) {
                if (isOpen(0, i) && weightedQuickUnionUF.connected(grid[0][i], grid[row][col])) return true;
            }
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }

    // does the system percolate
    public boolean percolates() {
        for (int i = 0; i < length; i++) {
            if (isOpen(length - 1, i) && isFull(length - 1, i)) return true;
        }

        return false;
    }

    private int xyTo1D(int r, int c) {
        return r * grid.length + c;
    }

    public static void main(String[] args) {


    }
}
