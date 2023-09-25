package com.mtzuri.matrixes.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MatrixTableView extends JFrame {
    public MatrixTableView(String title, int rows, int cols, int[][] data) throws HeadlessException {
        super("Matriz de " + title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = getjTable(rows, cols, title);

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                table.setValueAt(data[i][j],i+1,j+1);
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    private static JTable getjTable(int rows, int cols, String title) {
        DefaultTableModel model = new DefaultTableModel(rows, cols){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        if (title.equals("adyacencia")){
            for (int j = 1; j < cols; j++) {
                    model.setValueAt("V: " + j, 0, j);
            }
        } else if (title.equals("incidencia")) {
            for (int j = 1; j < cols; j++) {
                model.setValueAt("E: " + j, 0, j);
            }
        }


        for (int i = 1; i < rows; i++) {
            model.setValueAt("V: " + i, i, 0);
        }

        return new JTable(model){
            @Override
            public JTableHeader getTableHeader(){
                return null;
            }
        };
    }
}
