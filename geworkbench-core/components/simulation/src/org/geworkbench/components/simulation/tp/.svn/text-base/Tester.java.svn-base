package org.geworkbench.components.simulation.tp;

import java.io.PrintWriter;
import org.geworkbench.components.simulation.networkGenerator.RandomNetworkGenerator;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.sbml.ModelExporterMarkupLanguage;

public class Tester {
    public Tester() {
    }

    public static void main(String[] args){
        new Tester().doTest();
    }

    void doTest(){
        try{
//        System.out.println(System.getProperty("user.home"));
            RandomNetworkGenerator generator = new RandomNetworkGenerator();
            generator.initialize(null);
            Model m = generator.generateNetwork();
            ModelExporterMarkupLanguage exp = new ModelExporterMarkupLanguage();
            PrintWriter pr = new PrintWriter(System.out);
            exp.export(m, pr);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
