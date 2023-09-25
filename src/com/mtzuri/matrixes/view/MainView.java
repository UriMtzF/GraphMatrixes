package com.mtzuri.matrixes.view;

import com.mtzuri.matrixes.model.AdjacencyMatrix;
import com.mtzuri.matrixes.model.GraphReader;
import com.mtzuri.matrixes.model.IncidenceMatrix;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame implements ActionListener {
    private final JButton bShowIncidence;
    private final JButton bShowAdjacency;
    private final JButton bSelectFile;
    private String filePath;
    private boolean isTableOpen = false;
    public MainView() throws HeadlessException {
        super("Matrices de incidencia y adyacencia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pSelectMatrix = new JPanel();

        this.setLayout(new GridLayout(2,1));

        this.bSelectFile = new JButton("Selecciona un archivo");
        this.bSelectFile.addActionListener(this);

        this.bShowIncidence = new JButton("Matriz de incidencia");
        this.bShowIncidence.setEnabled(false);
        this.bShowIncidence.addActionListener(this);
        this.bShowAdjacency = new JButton("Matriz de adyacencia");
        this.bShowAdjacency.setEnabled(false);
        this.bShowAdjacency.addActionListener(this);

        pSelectMatrix.add(this.bShowIncidence);
        pSelectMatrix.add(this.bShowAdjacency);

        this.add(bSelectFile);
        this.add(pSelectMatrix);

        this.pack();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(this.bSelectFile)){
            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)","txt");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(this.bSelectFile);

            if (returnValue == JFileChooser.APPROVE_OPTION){
                java.io.File selectedFile = fileChooser.getSelectedFile();

                this.filePath = selectedFile.getAbsolutePath();

                this.bShowAdjacency.setEnabled(true);
                this.bShowIncidence.setEnabled(true);
            }
        } else if (actionEvent.getSource().equals(this.bShowAdjacency)) {
            GraphReader reader = new GraphReader(this.filePath);

            AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(reader.getPoints());

            if(!isTableOpen){
                MatrixTableView adjacencyTable = new MatrixTableView("adyacencia",
                        adjacencyMatrix.getMaxVertex(),
                        adjacencyMatrix.getMaxVertex(),
                        adjacencyMatrix.getAdjacencyMatrix());
                adjacencyTable.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        isTableOpen = false;
                    }
                });
                isTableOpen = true;
            }

        } else if (actionEvent.getSource().equals(this.bShowIncidence)) {
            GraphReader reader = new GraphReader(this.filePath);

            IncidenceMatrix incidenceMatrix = new IncidenceMatrix(reader.getPoints());

            if(!isTableOpen){
            MatrixTableView incidenceTable = new MatrixTableView("incidencia",
                    incidenceMatrix.getNumVertices(),
                    incidenceMatrix.getNumEdges(),
                    incidenceMatrix.getIncidenceMatrix());
            incidenceTable.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isTableOpen = false;
                }
            });
            isTableOpen = true;
            }
        }
    }
}
