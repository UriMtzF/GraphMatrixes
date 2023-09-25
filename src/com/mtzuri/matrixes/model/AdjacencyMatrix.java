package com.mtzuri.matrixes.model;

import java.util.List;

public class AdjacencyMatrix {
    private int[][] adjacencyMatrix;
    private int maxVertex = 0;

    public AdjacencyMatrix(List<int[]> points) {
        buildMatrix(points);
    }

    public void buildMatrix(List<int[]> points){

        for(int[] point : points){
            this.maxVertex = Math.max(this.maxVertex, Math.max(point[0], point[1]));
        }

        this.adjacencyMatrix = new int[this.maxVertex][this.maxVertex];

        for(int[] point: points){
            int i = point[0] - 1;
            int j = point[1] - 1;
            this.adjacencyMatrix[i][j] = 1;
            this.adjacencyMatrix[j][i] = 1;
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
    public int getMaxVertex() {
        return maxVertex;
    }
}
