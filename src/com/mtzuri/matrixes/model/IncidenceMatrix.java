package com.mtzuri.matrixes.model;

import java.util.List;
import java.util.stream.IntStream;

public class IncidenceMatrix {
    private int numEdges;
    private int[][] incidenceMatrix;
    int numVertices;

    public IncidenceMatrix(List<int[]> points) {
        buildMatrix(points);
    }

    public void buildMatrix(List<int[]> points){
        this.numVertices = points.stream()
                .flatMapToInt(point -> IntStream.of(point[0],point[1]))
                .max()
                .orElse(0);
        this.numEdges = points.size();

        this.incidenceMatrix = new int[numVertices][numEdges];

        for (int edgeIndex = 0; edgeIndex < numEdges; edgeIndex++) {
            int[] point = points.get(edgeIndex);
            int i = point[0] - 1;
            int j = point[1] - 1;

            incidenceMatrix[i][edgeIndex] = 1;
            incidenceMatrix[j][edgeIndex] = 1;
        }
    }

    public int getNumEdges() {
        return numEdges;
    }
    public int[][] getIncidenceMatrix() {
        return incidenceMatrix;
    }
    public int getNumVertices() {
        return numVertices;
    }
}
