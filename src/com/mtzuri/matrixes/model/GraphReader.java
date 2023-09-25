package com.mtzuri.matrixes.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphReader {
    private final List<int[]> points;
    public GraphReader(String filePath){
        this.points = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = reader.readLine()) != null){
                String[] coordinates = line.replaceAll("[()]","").split(",");
                if (coordinates.length == 2){
                    int[] point = {Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])};
                    this.points.add(point);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<int[]> getPoints() {
        return points;
    }
}
