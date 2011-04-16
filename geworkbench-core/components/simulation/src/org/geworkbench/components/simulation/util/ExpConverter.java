package org.geworkbench.components.simulation.util;

import java.io.*;
import java.util.*;

public class ExpConverter {
    public ExpConverter() {
    }

    public static void main(String[] args){
        new ExpConverter().writeFile(args);
    }

    void writeFile(String[] args){
//        File readFile = new File("Z:/Simulations/Results/SF/SF-001_results.txt");
//        File writeFile = new File("Z:/Simulations/Results/SF/SF-001_results.exp");
        File readFile = new File(args[0]);
        File writeFile = new File(args[1]);
        writeFile.getParentFile().mkdirs();
        writeExpFile(readFile, writeFile);
    }

    public static void writeExpFile(File readFile, File writeFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readFile));
            String[] geneNames = reader.readLine().split("\t");
            String line;
            Vector vecAllValues = new Vector();
            while ( (line = reader.readLine()) != null) {
                String[] arrLine = line.split("\t");
                double[] lineVals = new double[arrLine.length];
                for (int i = 0; i < arrLine.length; i++) {
                    lineVals[i] = Double.parseDouble(arrLine[i]);
                }
                vecAllValues.add(lineVals);
            }

            int numValueLines = vecAllValues.size();
            double[][] allData = new double[numValueLines][];
            for (int i = 0; i < numValueLines; i++) {
                allData[i] = (double[]) vecAllValues.get(i);
            }
            writeExpFile(geneNames, allData, writeFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeExpFile(String[] geneNames, double[][] data,
                                    File writeFile) {

        try {
            FileWriter writer = new FileWriter(writeFile);
            writer.write("AffyID\tAnotation\t");

            for (int expCtr = 0; expCtr < data.length; expCtr++) {
                writer.write("Exp" + expCtr + "\t");
            }
            writer.write("\n");

            for (int rowIndex = 0; rowIndex < geneNames.length; rowIndex++) {
                String geneName = geneNames[rowIndex];
                writer.write(geneName + "\t" + geneName);
                for (int columnIndex = 0; columnIndex < data.length;
                     columnIndex++) {
                    double dataValue = data[columnIndex][rowIndex];
                    writer.write("\t" + dataValue + "\t1");
                }
                writer.write("\n");
            }

            writer.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
