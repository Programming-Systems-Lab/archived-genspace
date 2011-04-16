package org.geworkbench.components.simulation.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import org.geworkbench.components.simulation.networkGenerator.SbmlNetworkGenerator;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.ModelExporterHumanReadable;

public class PrintNetworkHr {
    public PrintNetworkHr() {
    }

    public static void main(String[] args){
        new PrintNetworkHr().exportDefault();
    }

    void exportDefault(){
        File sbmlFile = new File("Z:/Simulations/Params/SimParamsTest/CenturySF-001.xml");
        File writeFile = new File("Z:/Simulations/Params/SimParamsTest/SF-001_hr.txt");
        exportNetworkHr(sbmlFile, writeFile);
    }

    public void exportNetworkHr(File sbmlFile, File writeFile){
        SbmlNetworkGenerator networkGenerator = new SbmlNetworkGenerator();
        Model model = networkGenerator.createModelFromSbmlFile(sbmlFile);

        try{
            ModelExporterHumanReadable expHr = new ModelExporterHumanReadable();
            FileOutputStream os = new FileOutputStream(writeFile);
            PrintWriter writer = new PrintWriter(os);
            expHr.export(model, writer);
        }catch(Exception e){
            e.printStackTrace();
        }


    }

}
