package org.geworkbench.components.simulation;

import com.Ostermiller.util.ExcelCSVParser;
import com.jrefinery.data.XYSeries;
import com.jrefinery.data.XYSeriesCollection;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.systemsbiology.math.Expression.Element;
import org.geworkbench.bison.datastructure.biocollections.microarrays.CSExprMicroarraySet;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMicroarray;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMutableMarkerValue;
import org.geworkbench.components.simulation.Reaction.ElementMath;
import org.geworkbench.components.simulation.networkGenerator.INetworkGenerator;
import org.geworkbench.components.simulation.networkGenerator.NetworkGeneratorFactory;
import org.geworkbench.components.simulation.networkGenesProcessor.NetworkGenesProcessor;
import org.geworkbench.components.simulation.networkProcessor.INetworkProcessor;
import org.geworkbench.components.simulation.networkProcessor.NetworkProcessorFactory;
import org.geworkbench.components.simulation.simulator.ISimulator;
import org.geworkbench.components.simulation.Reaction.*;
import org.geworkbench.components.simulation.simulator.SimulatorFactory;
import org.geworkbench.util.Util;
import org.systemsbiology.chem.*;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.Reaction;
import org.systemsbiology.chem.Species;
import org.systemsbiology.chem.sbml.ModelExporterMarkupLanguage;
import org.systemsbiology.math.Expression;
import org.systemsbiology.math.Value;

public class SimulationMain {
    Model currentSimModel;
    Model previousSimModel;
    int numPhenos = 100;
    int vmaxRange = 5;
    int degRange = 2;
    int phenosOn = 0;
    double fanInPercent = 0.5;
    
    int stoich = 0;
    int numinputs = 1;
    int numoutputs = 2;
    int[] inputs = new int[numinputs];
    int[] outputs = new int[numoutputs];
    double K = 2.0;
    int optimOn = 0;
    
    double switchThreshold = 2.5;
    double switchMagnitude = 5.0;
    int numKinase = 0;
    int numKinaseCons = 0;
    int numGenes = 13;
    double [] optGene = new double[numGenes];
    double [][] optParam = new double[numGenes][];
    int numConnections = 30; //must be even number
    double degradationRate = 1.0;
    double mrnaDegRate = Math.random()*2;
    double proDegRate = 1.0;
    double activeproDegRate = 1.0;
    int externalNode = 0;
    int ilyaCounter = 1;
    int ilyaSim = 0;
    double actPercent = 0.5;
    String saveFile = new String();
//    boolean ilyaSim = true;
    int rewireIterations = 0;
//    double[] chi2Values = new double[5000];
    ArrayList<Double> chi2Values = new ArrayList<Double>();
    int metrop = 0;
    
    int numSims = 20;
    double[]inputVal = new double[numSims];
    double[]output1Val = new double[numSims];
    double[][] output1Val2 = new double[numGenes][numSims];
    double inputpro;
    double outputpro;
    ISimulator simulator;
    boolean stopRequested = false;
    
    public SimulationMain() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public double[] runSteadyState(HashMap propsMap){
        ISimulator simulator = runSimulation(propsMap);
        double[] steadyStateValues = simulator.getSteadyStateValues();
        return steadyStateValues;
    }
    
    public void initIO(HashMap propsMap) {
        int[] temp = new int[numinputs + numoutputs];
        for (int i = 0; i < (numinputs + numoutputs); i++) {
            temp[i] = (int) (Math.random() * numGenes);
            for (int j = 0; j < i; j++) {
                if (temp[j] == temp[i]) {
                    temp[i] = (int) (Math.random() * numGenes);
                    j = -1;
                }
            }
        }
        
        for(int i = 0; i < numinputs; i++) {
            inputs[i] = temp[i];
        }
        
        for(int i = numinputs, j = 0; i < (numinputs + numoutputs); i++, j++) {
            outputs[j] = temp[i];
        }
        
        for (int i = 0; i < numinputs; i++) {
            System.out.println("INPUT: " + inputs[i]);
        }
        
        for (int i = 0; i < numoutputs; i++) {
            System.out.println("OUTPUT: " + outputs[i]);
        }
    }
    
    public double sigfig(double dec) {
        int temp = (int) (dec * 1000);
        double temp2 = ((double)temp) / ((double)1000);
        return temp2;
    }
    
    public static enum attributes {NUM_GENES, NUM_CONNECTIONS, NUM_KINASES, KINASE_CONNECTIONS, NUM_PHENOS, VMAX_RANGE, DEG_RANGE, PHENOS_ON, FAN_IN_PERCENT, OPTIM_ON, ACT_PERCENT};
    
    public void setAttributes(HashMap<attributes, Integer> att){
        numGenes = att.get(attributes.NUM_GENES);
        numConnections = att.get(attributes.NUM_CONNECTIONS);
        numKinase = att.get(attributes.NUM_KINASES);
        numKinaseCons = att.get(attributes.KINASE_CONNECTIONS);
        numPhenos = att.get(attributes.NUM_PHENOS);
        degRange = att.get(attributes.DEG_RANGE);
        vmaxRange = att.get(attributes.VMAX_RANGE);
        phenosOn = att.get(attributes.PHENOS_ON);
        fanInPercent = (double)att.get(attributes.FAN_IN_PERCENT) / 100;
        optimOn = att.get(attributes.OPTIM_ON);
        actPercent = (double)att.get(attributes.ACT_PERCENT) / 100;
    }
    
    public void setSaveFile(String pathname) {
        saveFile = pathname;
    }
    
//  public void metropolis(HashMap propsMap, org.bioworks.simulation.simulator.ISimulator simulator, INetworkGenerator networkGenerator) {
    public void metropolis(HashMap propsMap, INetworkGenerator networkGenerator) {
        System.out.println("BEGIN MET");
//      simulator.runSimulation(currentSimModel);
        //   double[] SSValues = simulator.getSteadyStateValues();
//      for (int i = 0; i < 5000; i++){
        //         chi2Values.add(i, new Double(0d));
//      }
        double chi2 = 1000;
        double chi2prev = 1000;
        int started = 1;
        double beta = 0.01;
        int chiCtr = 0;
        int update = 0;
        
        inputs[0] = 0;
        outputs[numoutputs - 2] = 1;
        outputs[numoutputs - 1] = 2;
        
        
        
        perturbModel(-1, 0);
        perturbModel(-12, 0);
        
        System.out.println(currentSimModel.toString());
        NelderMead foo = new NelderMead();
        
        while (chi2 > 0.01) {
            chi2prev = chi2;
            
            rewireIterations++;
            System.out.println("Rewire Iteration Number: " + rewireIterations);
            
            chi2 = foo.descend();
            if (chi2 == -1) {
                update = 0;
                currentSimModel = networkGenerator.generateNetwork();
                chi2 = chi2prev;
                started = 0;
            }
            
            int method = 0;
            
            method = (int) (Math.random() * 3);
            
            beta = beta + 0.01;
            
            if (started == 1) {
                
                if (chi2 <= chi2prev) {
                    System.out.println("DID BETTER: " + chi2 + " " + chi2prev);
                    update = 1;
                } else {
                    chiCtr++;
                    System.out.println("DID WORSE: " + chi2 + " " + chi2prev);
                    double acceptanceProb = Math.exp( - (chi2 - chi2prev) * beta);
                    
                    if (Math.random() < acceptanceProb) {
                        System.out.println("ACCEPTED WORSE: " + acceptanceProb);
                        update = 1;
                    } else {
                        System.out.println("NOT ACCEPTED WORSE: " + acceptanceProb);
                        update = 0;
                        currentSimModel = networkGenerator.generateNetwork();
                        chi2 = chi2prev;
                    }
                }
            }
            
            chi2Values.add(chi2); //store chi values
//*
            if (update == 1) {
                try{
                    ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                    File writeFile;
                    if (stoich == 1) {
                        writeFile = new File("C:/Hooman/STOICH.xml");
                    } else {
                        writeFile = new File("C:/Hooman/SWITCH.xml");
                    }
                    
                    FileOutputStream os = new FileOutputStream(writeFile);
                    PrintWriter writer = new PrintWriter(os);
                    sbmlfile.export(currentSimModel, writer);
                    
                    if (stoich == 1) {
                        writeFile = new File("C:/Hooman/stoichData.txt");
                    } else {
                        writeFile = new File("C:/Hooman/switchData.txt");
                    }
                    os = new FileOutputStream(writeFile);
                    writer = new PrintWriter(os);
                    writer.println("Beta: " + beta);
                    writer.println("Iteration: " + rewireIterations);
                    writer.println("Chi2: " + chi2);
                    writer.println("Inputs: " + inputs[0]);
                    writer.println("Outputs: " + outputs[0] + " " + outputs[1]);
                    writer.println("External Node: " + externalNode);
                    writer.println("Input Values: ");
                    for(int i = 0; i < numSims; i++) {writer.println(inputVal[i]);}
                    writer.println("Output 1 Values (Out1=K*In*Out2/Switched Up): ");
                    for(int i = 0; i < numSims; i++) {writer.println(output1Val[i]);}
                    //writer.println("Output 2 Values (Out1=K*In*Out2/Switched Down): ");
                    //for(int i = 0; i < numSims; i++) {writer.println(output2Val[i]);}
                    
                    if (chi2 <= 0.1){
                        writer.println("FINISHED!!!");
                    }
                    writer.flush();
                    
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }//*/
            perturbModel(method, 0);
            started = 1;
        }
    }
    
    public double[] perturbPoint(double[] param) {
        double[] foo = new double[param.length + 1];
        foo[param.length] = 0.0;
        for (int i = 0; i < param.length; i++) {
            double perturbation = Math.random();
            
            if (param[i] >= 6.5) {
                foo[i] = Math.random()*20;
            } else if (param[i] >= 3.5) {
                foo[i] = Math.random()*5;
            } else if(Math.random() <= 0.5 && param[i] - perturbation >= 0.01) {
                foo[i] = param[i] - perturbation;
            } else {
                foo[i] = param[i] + perturbation;
            }
        }
        return foo;
    }
    
    
    public double chisquareK(double input, double pro1, double pro2, double K) {
        return ( ((pro1/pro1) - K * input * (pro2/pro1)) * ((pro1/pro1) - K * input * (pro2/pro1)));
    }
    
    public double chisquareSwitchDown(double inputpro, double outputpro) {
        double profunction = 0.0;
        if (inputpro < switchThreshold) {
            profunction = switchMagnitude;
        } else {
            profunction = 0.0;
        }
        return ((profunction - outputpro) * (profunction - outputpro));
    }
    
    
    
    Vector listeners = new Vector();
    public void addPropertyChangeListener(PropertyChangeListener l){
        listeners.add(l);
    }
    
    public double[] detectParam(ArrayList<Expression.Token> expList, int i, Expression exprate, double[] paramVals) {
        
        //Hill coeff and K for activator
        if ( (i+8) < expList.size() &&
                expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 2).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i + 3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i + 4).mCode.equals(expList.get(i).mCode.MULT) &&
                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 7).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
            double[] foo = {exprate.convertTokenToElement(expList.get(i + 2)).mNumericValue,
            exprate.convertTokenToElement(expList.get(i + 25)).mNumericValue};
//                       exprate.convertTokenToElement(expList.get(i + 10)).mNumericValue};//HILL PARAM
            paramVals[i+2] = foo[0];
            paramVals[i+25] = foo[1];
//        paramVals[i+10] = foo[2]; //HILL PARAM
//        System.out.println("activator " + foo[0] + " " + foo[1]);
            return foo;
        }
        //Hill coeff and K for inhibitor
        if ( (i+10) < expList.size() &&
                expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 2).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i + 3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i + 4).mCode.equals(expList.get(i).mCode.MULT) &&
                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 7).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 8).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i + 9).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i + 10).mCode.equals(expList.get(i).mCode.POW)) {
            double[] foo = {exprate.convertTokenToElement(expList.get(i + 2)).mNumericValue,
            exprate.convertTokenToElement(expList.get(i + 8)).mNumericValue};
//                       exprate.convertTokenToElement(expList.get(i + 12)).mNumericValue};//HILL PARAM
            paramVals[i+2] = foo[0];
            paramVals[i+8] = foo[1];
//        paramVals[i+12] = foo[2];//HILL PARAM
//        System.out.println("inhibitor " + foo[0] + " " + foo[1]);
            return foo;
        }
 /*   //vmax
    if ( (i+4) < expList.size() && i == 0 &&
        expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
        expList.get(i+1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
        expList.get(i+2).mCode.equals(expList.get(i).mCode.NUMBER) &&
        expList.get(i+3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
        expList.get(i+4).mCode.equals(expList.get(i).mCode.MULT)) {
        double[] foo = {exprate.convertTokenToElement(expList.get(i + 2)).mNumericValue};
        paramVals[i+2] = foo[0];
//        System.out.println("vmax " + foo[0]);
        return foo;
    }
  */
        //degrate
        if ( (i+7) < expList.size() &&
                expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i+1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i+2).mCode.equals(expList.get(i).mCode.MINUS) &&
                expList.get(i+3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i+4).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i+5).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i+6).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i+7).mCode.equals(expList.get(i).mCode.MULT)){
            double[] foo = {exprate.convertTokenToElement(expList.get(i + 4)).mNumericValue};
            paramVals[i+4] = foo[0];
//        System.out.println("deg " + foo[0]);
            return foo;
        }
        double[] foo = {-1};
        return foo;
    }
    
    public double[][] getParams() {
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        
        double[][] paramVals = new double[rList.size()][];
        for (int i = 0; i < rList.size(); i++) {
            Reaction reaction = rList.get(i);
            if (reaction.getName().contains("synthesis")) {//PROTEINS
                Value foo = new Value(reaction.getRate());
                Expression exprate = foo.getMExpressionValue();
                
                List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                
                paramVals[i] = new double[expList.size()];
                for (int j = 0; j < expList.size(); j++) {
                    paramVals[i][j] = -1;
                }
//             System.out.println("reaction: " + reaction.getName());
                for (int j = 0; j < expList.size(); j++) {
                    detectParam(expList, j, exprate, paramVals[i]);
                    //if (expList.get(j).mCode.equals(expList.get(j).mCode.NUMBER)) {
                    //    paramVals[i][j] = exprate.convertTokenToElement(expList.get(j)).mNumericValue;
                    //}
                }
            } else {
                paramVals[i] = new double[1];
                paramVals[i][0] = -1;
            }
        }
        return paramVals;
    }
    
    public double[] combineParams(double[][] params){
        
        int paramCount = 0;
        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < params[i].length; j++) {
                if (params[i][j] != -1) {
                    paramCount++;
                }
            }
        }
        double[] paramList = new double[paramCount];
        
        paramCount = 0;
        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < params[i].length; j++) {
                if (params[i][j] != -1) {
                    paramList[paramCount] = params[i][j];
                    paramCount++;
                }
            }
        }
        return paramList;
    }
    
    public Model generateGeneInfoNetwork(ArrayList<GeneInfo> geneInfo, String fileName) {
        Model m = new Model();
        m.setName("My Model");
        Species nullGene;
        Compartment c = new Compartment("cell");
        nullGene = new Species("_void_", c);
        nullGene.setSpeciesPopulation(1.0);
        m.addSpecies(nullGene);
        GeneInfo gene;
        ArrayList<ReactionInfo> allReactionInfo;
        Species[] nodes = new Species[geneInfo.size()];
        
        for (int i = 0; i < geneInfo.size(); i++) {
            gene = (GeneInfo)geneInfo.get(i);
            Species node = new Species(gene.getGeneName(), c);
            node.setSpeciesPopulation(gene.getConcentration());
            m.addSpecies(node);
            nodes[i] = node;
        }
        
        for (int i = 0; i < geneInfo.size(); i++) {
            gene = (GeneInfo)geneInfo.get(i);
            allReactionInfo = (ArrayList<ReactionInfo>)gene.getSources();   
            MendesEqn mendesEqn = new MendesEqn();
            boolean geneIsProtein = false;
            ReactionInfo reactionInfo = null;
            System.out.println("***" + gene.getGeneName());
            
            Species target = null;
            for (int k = 0; k < nodes.length; k++) {
                if (nodes[k].getName().contains(gene.getGeneName())) {
                    target = nodes[k];
                }
            }
            
            Species source = null;
            for(int j = 0; j < allReactionInfo.size(); j++) {
                reactionInfo = (ReactionInfo)allReactionInfo.get(j);
                
                for (int k = 0; k < nodes.length; k++) {
                    if (nodes[k].getName().contains(reactionInfo.getSource())) {
                        source = nodes[k];
                    }
                }

                if (reactionInfo.getInteraction().contains("Activator") ||
                        reactionInfo.getInteraction().contains("Protein")) {
                    mendesEqn.addActivator(source);
                } else {
                    mendesEqn.addInhibitor(source);
                }
                if (reactionInfo.getInteraction().contains("Protein")) {
                    geneIsProtein = true;
                }
            }
            Reaction reaction = new Reaction(gene.getGeneName() + "_synthesis");
            reaction.addReactant(nullGene, 1);
            
            reaction.addProduct(target, 1);
            
            if (!geneIsProtein) {
                reaction.setRate(mendesEqn.getInfoMrnaRate(gene, allReactionInfo, target));
            } else {
                //DO FOR PROTEIN
                reaction.setRate(mendesEqn.getInfoProteinRate(gene, reactionInfo, target));
            }
            m.addReaction(reaction);
            
        }

        try {
            ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
            File writeFile = new File(fileName);
            FileOutputStream os = new FileOutputStream(writeFile);
            PrintWriter writer = new PrintWriter(os);
            sbmlfile.export(m, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("FOOOOOOOOOOOOO" + m.toString());
        
        return m;
    }

    public void createSifFile(ArrayList<GeneInfo> geneInfo) {
        try {File writeFile = new File(saveFile + "FOO.sif");
        FileOutputStream os = new FileOutputStream(writeFile);
        PrintWriter writer = new PrintWriter(os);
        os = new FileOutputStream(writeFile);
        writer = new PrintWriter(os);
        
        for (int i = 0; i < geneInfo.size(); i++) {
            GeneInfo gene = (GeneInfo)geneInfo.get(i);
            ArrayList<ReactionInfo> reactionInfo = (ArrayList<ReactionInfo>)gene.getSources();
            
            for(int j = 0; j < reactionInfo.size(); j++) {
                ReactionInfo reaction = (ReactionInfo)reactionInfo.get(j);
                if (reaction.getInteraction().contains("Activator")) {
                    System.out.println(reaction.getSource() + " pp " + gene.getGeneName());
                    writer.println(reaction.getSource() + " pp " + gene.getGeneName());
                } else {
                    System.out.println(reaction.getSource() + " pd " + gene.getGeneName());
                    writer.println(reaction.getSource() + " pd " + gene.getGeneName());
                }
            }
        }
        }catch(Exception e) {e.printStackTrace();}
    }
//    @Publish public org.geworkbench.events.AdjacencyMatrixEvent publishAdjacencyMatrixEvent(org.geworkbench.events.AdjacencyMatrixEvent event) {
//        return event;
//    }
    public ArrayList<GeneInfo> getGeneInfo() {
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        ArrayList<GeneInfo> geneInfo = new ArrayList<GeneInfo>();
        int paramCount = 0;
        
        try {    
            for (int j = 0; j < rList.size(); j++) {
                String perturbedExp = new String();
                Reaction reaction = rList.get(j);
                
                if (reaction.getName().contains("p")) {
                    Value foo = new Value(reaction.getRate());
                    Expression exprate = foo.getMExpressionValue();
                    
                    GeneInfo gene = new GeneInfo(reaction.getName().substring(0,3));
                    
                    List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                    ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                    
                    for (int i = 0; i < expList.size(); i++) {
                        if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                            if ( (i+6) < expList.size() && (i-2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.SYMBOL)) {
                                gene.setDeg(exprate.convertTokenToElement(expList.get(i + 10)).mNumericValue);
                                gene.addSource(exprate.convertTokenToElement(expList.get(i + 3)).toString(), 0, 0,
                                        exprate.convertTokenToElement(expList.get(i)).mNumericValue, "Protein");
                            }
                        }
                    }
                    geneInfo.add(gene);
                }
                
                if (reaction.getName().contains("m")) {
                    Value foo = new Value(reaction.getRate());
                    Expression exprate = foo.getMExpressionValue();
                    
                    GeneInfo gene = new GeneInfo(reaction.getName().substring(0,3));
                    
                    List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                    ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                    
                    for (int i = 0; i < expList.size(); i++) {
                        
                        if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                            if ( (i+6) < expList.size() && (i-2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                                
                                gene.addSource(exprate.convertTokenToElement(expList.get(i + 5)).toString(), 
                                               exprate.convertTokenToElement(expList.get(i + 23)).mNumericValue, 
                                               exprate.convertTokenToElement(expList.get(i + 8)).mNumericValue,
                                               exprate.convertTokenToElement(expList.get(i)).mNumericValue, "Activator");
//                                System.out.println(exprate.convertTokenToElement(expList.get(i + 5)).toString() + " pp " + reaction.getName().substring(0,3));
//                                writer.println(exprate.convertTokenToElement(expList.get(i + 5)).toString() + " pp " + reaction.getName().substring(0,3));
//                                writer.flush();
                            }
                            //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                            else if ( (i+8) < expList.size() && (i-2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                                gene.addSource(exprate.convertTokenToElement(expList.get(i + 16)).toString(), 
                                               exprate.convertTokenToElement(expList.get(i + 6)).mNumericValue, 
                                               exprate.convertTokenToElement(expList.get(i + 10)).mNumericValue, 
                                               exprate.convertTokenToElement(expList.get(i)).mNumericValue, "Inhibitor");
                                
//                                System.out.println(exprate.convertTokenToElement(expList.get(i + 16)).toString() + " pd " + reaction.getName().substring(0,3));
//                                writer.println(exprate.convertTokenToElement(expList.get(i + 16)).toString() + " pd " + reaction.getName().substring(0,3));
//                                writer.flush();
                            } else if ( (i+3) < expList.size() && (i-4) >= 0 &&
                                    expList.get(i-4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i-3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i-2).mCode.equals(expList.get(i).mCode.MINUS) &&
                                    expList.get(i-1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i+1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i+2).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i+3).mCode.equals(expList.get(i).mCode.MULT)){
                                gene.setDeg(exprate.convertTokenToElement(expList.get(i)).mNumericValue);
                            }
                        }
                    }
                    
                    geneInfo.add(gene);
//                    gene.print();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        ((GeneInfo)geneInfo.get(0)).print();
        return geneInfo;
    }
    
    public void setVmaxAndDeg(double vmaxRange, double degRange) {
        
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        int paramCount = 0;
     
        for (int j = 0; j < rList.size(); j++) {
            String perturbedExp = new String();
            Reaction reaction = rList.get(j);
            if (reaction.getName().contains("m")) {
                Value foo = new Value(reaction.getRate());
                Expression exprate = foo.getMExpressionValue();
                
                List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                
                for (int i = 0; i < expList.size(); i++) {
                    
                    if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                        if ( (i+6) < expList.size() && (i-2) >= 0 &&
                                expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                            
                            perturbedExp = perturbedExp + (Math.random() * vmaxRange);
                        }
                        //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                        else if ( (i+8) < expList.size() && (i-2) >= 0 &&
                                expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                            perturbedExp = perturbedExp + (Math.random() * vmaxRange);
                        }
                        //degrate
                        else if ( (i+3) < expList.size() && (i-4) >= 0 &&
                                expList.get(i-4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i-3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i-2).mCode.equals(expList.get(i).mCode.MINUS) &&
                                expList.get(i-1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i+1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i+2).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i+3).mCode.equals(expList.get(i).mCode.MULT)){
                            perturbedExp = perturbedExp + (Math.random() * degRange);
                        } else {
                            perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                        }
                        
                    } else {
                        perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                    }
                }
                //System.out.println("*** " + reaction.getName() + " *** "  + perturbedExp);
                try{exprate.setExpression(perturbedExp);} catch (Exception d) {d.printStackTrace();}
                reaction.setRate(exprate);
                currentSimModel.setReaction(reaction.getName(), reaction);
            }
        }
    }
    
    
    
    public void setParams(double[] paramList) {
        
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        int paramCount = 0;
        
        //MAY HAVE TO BE REMOVED!!!
//     for (int i = 0; i < paramList.length; i++) {
//         System.out.print(paramList[i] + " ");
//         paramList[i] = Math.abs(paramList[i]);
//         if (paramList[i] > 20)
//             paramList[i] = 20;
//         if (paramList[i] < 0.01)
//             paramList[i] = 0.01;
//     }
        
        for (int j = 0; j < rList.size(); j++) {
            String perturbedExp = new String();
            Reaction reaction = rList.get(j);
            if (reaction.getName().contains("m")) {
                Value foo = new Value(reaction.getRate());
                Expression exprate = foo.getMExpressionValue();
                
                List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                
                for (int i = 0; i < expList.size(); i++) {
                    
                    if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                        if ( (i+6) < expList.size() && (i-2) >= 0 &&
                                expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                            
                            perturbedExp = perturbedExp + paramList[paramCount] + ")*((" + exprate.convertTokenToElement(expList.get(i + 5)).toString() +
                                    "^(" + "1.0"/*paramList[paramCount + 1]*/ + "))/((" + exprate.convertTokenToElement(expList.get(i + 14)).toString() +
                                    "^(" + "1.0"/*paramList[paramCount + 1]*/ + "))+((" + paramList[paramCount + 2 - 1] + ")^(" + "1.0"/*paramList[paramCount + 1]*/;
                            paramCount += 3 - 1;
                            i += 27;
                        }
                        //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                        else if ( (i+8) < expList.size() && (i-2) >= 0 &&
                                expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                            perturbedExp = perturbedExp + paramList[paramCount] + ")*(((" + paramList[paramCount+1] + ")^(" + "1.0"/*paramList[paramCount + 2]*/ + "))/((" + exprate.convertTokenToElement(expList.get(i + 16)) +
                                    "^(" + "1.0"/*paramList[paramCount + 2]*/ + "))+((" + paramList[paramCount+1] + ")^(" + "1.0"/*paramList[paramCount + 2]*/;
                            paramCount += 3 - 1;
                            i += 29;
                        }
               /*      //vmax
                     else if ( (i+2) < expList.size() && (i-2) == 0 &&
                          expList.get(i-2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                          expList.get(i-1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                          expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                          expList.get(i+1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                          expList.get(i+2).mCode.equals(expList.get(i).mCode.MULT)) {
                         perturbedExp = perturbedExp + paramList[paramCount];
                         paramCount++;
                     }*/
                        //degrate
                        else if ( (i+3) < expList.size() && (i-4) >= 0 &&
                                expList.get(i-4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i-3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i-2).mCode.equals(expList.get(i).mCode.MINUS) &&
                                expList.get(i-1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i+1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i+2).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i+3).mCode.equals(expList.get(i).mCode.MULT)){
                            perturbedExp = perturbedExp + paramList[paramCount];
                            paramCount++;
                        } else {
                            perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                        }
                        
                    } else {
                        perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                    }
                }
                //System.out.println("*** " + reaction.getName() + " *** "  + perturbedExp);
                try{exprate.setExpression(perturbedExp);} catch (Exception d) {d.printStackTrace();}
                reaction.setRate(exprate);
                currentSimModel.setReaction(reaction.getName(), reaction);
            }
        }
    }
    
    
    private void firePropertyChange(HashMap map){
        for (int i = 0; i < listeners.size(); i++){
            ((PropertyChangeListener)listeners.get(i)).propertyChange(new PropertyChangeEvent(this, "", null, map));
        }
    }
    public void changeMrnaConc(int index, double conc) {
        try{
            Species gene = currentSimModel.getSpeciesByName("G" + index + "m");
            gene.setSpeciesPopulation(conc);
            currentSimModel.setSpeciesByName("G" + index + "m", gene);
//PROTEINS
//          Species gene2 = currentSimModel.getSpeciesByName("G" + index + "p");
//          gene2.setSpeciesPopulation(conc);
//          currentSimModel.setSpeciesByName("G" + index + "p", gene2);
            
        } catch (Exception q) {
            System.out.println("Could not change concentration");
            q.printStackTrace();
        }
    }
    public void changeMrnaConc2(int index, double conc) {
        try{
            Species gene = currentSimModel.getSpeciesByName("G" + index);
            gene.setSpeciesPopulation(conc);
            currentSimModel.setSpeciesByName("G" + index, gene);
        } catch (Exception q) {
            System.out.println("Could not change concentration");
            q.printStackTrace();
        }
    }
    
//  public void rewire(int method) {
//      Collection<Reaction> temp = currentSimModel.getReactions();
//      ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
//
//      int target = (int)(Math.random() * rList.size());
//      Reaction reaction = rList.get(target);
//      reaction
//      reaction.addActivator(currentSimModel.getSpeciesByName("G" + Math.random()*numGenes + "m"));
//      currentSimModel.setReaction(Math.random()*numGenes, reaction);
//  }
    
    public void robustTest(int geneNum) {
        String[] variations = new String[20];
        for (int i = 0; i < variations.length;i++){
            variations[i] = "-1";
        }
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        
        int target = 0;
        Reaction reaction = rList.get(target);
        
        while(!reaction.getName().contains("G" + geneNum + "m") && target < rList.size()) {
            target++;
            reaction = rList.get(target);
        }
        Value foo = new Value(reaction.getRate());
        Expression exprate = foo.getMExpressionValue();
        
        List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
        ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
        
        String perturbedExp = exprate.toString();
        
        
        if (Math.random() < 0.5) {
            int index = -1;
            if (expList.size() > 20) {
                for (int i = 0; i < expList.size(); i++) {
                    if ((i+9) < expList.size() &&
                            (expList.get(i).mCode.equals(expList.get(i).mCode.MULT) ||
                            expList.get(i).mCode.equals(expList.get(i).mCode.PLUS)) &&
                            expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 3).mCode.equals(expList.get(i).mCode.NUMBER) &&
                            expList.get(i + 4).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                            expList.get(i + 5).mCode.equals(expList.get(i).mCode.MULT) &&
                            expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 7).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 8).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                            expList.get(i + 9).mCode.equals(expList.get(i).mCode.POW)) {
                        index = i+1;
                        if (Math.random() < 0.5) {
                            break;
                        }
                    }
                }
            }
            
            if (index != -1) {
                expList.remove(index); //removes first parenth
                index = index + 3;
                for (int i = 0; i < 31; i++) {
                    expList.remove(index);
                }
            }
            perturbedExp = new String();
            for (int i = 0; i < expList.size(); i++) {
                perturbedExp = constructExp(perturbedExp, expList, i, exprate);
            }
//              System.out.println("removing ACT " + index + " " + perturbedExp);
        } else {
            
            //remove inhibitor edge
            //(((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
            int index = -1;
            if (expList.size() > 20) {
                for (int i = 0; i < expList.size(); i++) {
                    if ((i+11) < expList.size() &&
                            expList.get(i).mCode.equals(expList.get(i).mCode.MULT) &&
                            expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 3).mCode.equals(expList.get(i).mCode.NUMBER) &&
                            expList.get(i + 4).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                            expList.get(i + 5).mCode.equals(expList.get(i).mCode.MULT) &&
                            expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 7).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 8).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                            expList.get(i + 9).mCode.equals(expList.get(i).mCode.NUMBER) &&
                            expList.get(i + 10).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                            expList.get(i + 11).mCode.equals(expList.get(i).mCode.POW)) {
                        
                        index = i+1;
                        if (Math.random() < 0.5) {
                            break;
                        }
                    }
                }
            }
            
            if (index != -1) {
                expList.remove(index); //removes first parenth
                index = index + 3;
                for (int i = 0; i < 33; i++) {
                    expList.remove(index);
                }
            }
            perturbedExp = new String();
            for (int i = 0; i < expList.size(); i++) {
                perturbedExp = constructExp(perturbedExp, expList, i, exprate);
            }
//              System.out.println("removing INH " + index + " " + perturbedExp);
        }
        
        try{exprate.setExpression(perturbedExp);} catch (Exception d) {d.printStackTrace();}
        reaction.setRate(exprate);
        currentSimModel.setReaction(reaction.getName(), reaction);
    }
    public void perturbModel(int method, int counter) {
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        
        int target = (int)(Math.random() * rList.size());
        Reaction reaction = rList.get(target);
        
        if (method != -2 && method != -3) {
            while (!reaction.getName().contains("m") || reaction.getName().contains(inputs[0] + "m")) {
                target = (int) (Math.random() * rList.size());
                reaction = rList.get(target);
            }
        }
        
        if(method == -2) {
            target = 0;
            reaction = rList.get(target);
            while (!reaction.getName().contains("G" + ilyaCounter + "_degradation") && target < rList.size()) {
                target++;
                reaction = rList.get(target);
            }
        }
        
        if(method == -3) {
            target = 0;
            reaction = rList.get(target);
            while (!reaction.getName().contains("G" + ilyaCounter + "_synthesis") && target < rList.size()) {
                target++;
                reaction = rList.get(target);
            }
        }
        
        if(method == -4) {
            target = 0;
            reaction = rList.get(target);
            while (!reaction.getName().contains("G" + counter + "m_synthesis") && target < rList.size()-1) {
                target++;
                reaction = rList.get(target);
            }
        }
        
        if(method == -1) {
            target = 0;
            reaction = rList.get(target);
            while(!reaction.getName().contains("G" + inputs[0] + "m") && target < rList.size()) {
                target++;
                reaction = rList.get(target);
            }
        }
        if(method == -12) {
            target = 0;
            reaction = rList.get(target);
            while(!reaction.getName().contains("G" + inputs[0] + "p") && target < rList.size()) {
                target++;
                reaction = rList.get(target);
            }
        }
        
        
        Value foo = new Value(reaction.getRate());
        Expression exprate = foo.getMExpressionValue();
        
        List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
        ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
        
        String perturbedExp = exprate.toString();
//      System.out.println("Method: " + method);
//      System.out.println("PERTURBING : " + reaction.toString());
        
        if(method == -2 || method == -3 || method == -4) {
            perturbedExp = "0";
            externalNode = target;
        }
        if (method == -1 || method == -12) {
            //perturbedExp = "2*(cos((10*time)))";
            perturbedExp = "0";
            externalNode = target;
        }
        
        if (method == 2) {
            if (Math.random() < 0.5) {
                //remove activator edge
//           ((1.0)*((1.0)*(((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))))
//                  ((1.0)*(((0.01)^(1.5))/((G2p^(1.5))+((0.01)^(1.5)))))
//                  ((1.0)*((G3p^(1.5))/((G3p^(1.5))+((0.01)^(1.5)))))
//           ((1.0)*((1.0)*((G1p^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))))
//           ((((1.0)*(1.0))*((1.0)*((G1p^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))))
                int index = -1;
                if (expList.size() > 20) {
                    for (int i = 0; i < expList.size(); i++) {
                        if ((i+9) < expList.size() &&
                                (expList.get(i).mCode.equals(expList.get(i).mCode.MULT) ||
                                expList.get(i).mCode.equals(expList.get(i).mCode.PLUS)) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 7).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 8).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                expList.get(i + 9).mCode.equals(expList.get(i).mCode.POW)) {
                            index = i+1;
                            if (Math.random() < 0.5) {
                                break;
                            }
                        }
                    }
                }
                
                if (index != -1) {
                    expList.remove(index); //removes first parenth
                    index = index + 3;
                    for (int i = 0; i < 31; i++) {
                        expList.remove(index);
                    }
                }
                perturbedExp = new String();
                for (int i = 0; i < expList.size(); i++) {
                    perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                }
//              System.out.println("removing ACT " + index + " " + perturbedExp);
            } else {
                
                //remove inhibitor edge
                //(((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                int index = -1;
                if (expList.size() > 20) {
                    for (int i = 0; i < expList.size(); i++) {
                        if ((i+11) < expList.size() &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 7).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 8).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 9).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 10).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 11).mCode.equals(expList.get(i).mCode.POW)) {
                            
                            index = i+1;
                            if (Math.random() < 0.5) {
                                break;
                            }
                        }
                    }
                }
                
                if (index != -1) {
                    expList.remove(index); //removes first parenth
                    index = index + 3;
                    for (int i = 0; i < 33; i++) {
                        expList.remove(index);
                    }
                }
                perturbedExp = new String();
                for (int i = 0; i < expList.size(); i++) {
                    perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                }
//              System.out.println("removing INH " + index + " " + perturbedExp);
            }
        }
        
        if (method == 1) {
            //add inhibitor/activator edge
            int index = -1;
            for (int i = 0; i < expList.size(); i++) {
                if ((i+7) < expList.size() &&
                        expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+2).mCode.equals(expList.get(i).mCode.NUMBER) &&
                        expList.get(i+3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                        expList.get(i+4).mCode.equals(expList.get(i).mCode.MULT) &&
                        expList.get(i+5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+7).mCode.equals(expList.get(i).mCode.NUMBER)) {
                    index = i+2;
                    if (Math.random() < 0.5) {
                        break;
                    }
                }
                
                if(index == -1 && (i+6) < expList.size() &&
                        expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+2).mCode.equals(expList.get(i).mCode.NUMBER) &&
                        expList.get(i+3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                        expList.get(i+4).mCode.equals(expList.get(i).mCode.MULT) &&
                        expList.get(i+5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                        expList.get(i+6).mCode.equals(expList.get(i).mCode.NUMBER)){
                    index = i+2;
                    if (Math.random() < 0.5) {
                        break;
                    }
                }
            }
            perturbedExp = new String();
            for (int i = 0; i < expList.size(); i++) {
                if (index != -1 && i == index) {
                    int newGene = (int) (Math.random() * numGenes);
                    if (Math.random() < 0.5) {
//                      perturbedExp = perturbedExp + "*((1.0)+((G" + newGene + "p^(1.5))/((G" + newGene + "p^(1.5))+((0.01)^(1.5)))))*";
                        perturbedExp = perturbedExp + "((1.0)*((1.0)*((G" + newGene + "p^(1.5))/((G" + newGene + "p^(1.5))+((0.01)^(1.5))))))";
                    } else {
                        perturbedExp = perturbedExp + "((1.0)*((1.0)*(((0.01)^(1.5))/((G" + newGene + "p^(1.5))+((0.01)^(1.5))))))";
                    }
                } else {
                    perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                }
            }
        }
        
        if (method == 0) {
            //move edge
            
            String gene = new String();
            if (expList.size() > 20) {
                for (int i = 0; i < expList.size(); i++) {
                    if (expList.get(i).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                            expList.get(i + 1).mCode.equals(expList.get(i).mCode.POW)) {
                        gene = exprate.convertTokenToElement(expList.get(i)).toString();
                        if (Math.random() < 0.5) {
                            break;
                        }
                    }
                }
            }
            
            String newGene = "G" + (int) (Math.random() * numGenes);
            int found = 0;
            
            //finds if gene needs to be activated or not
            for (int i = 0; i < rList.size(); i++) {
                Reaction reactionTemp = rList.get(i);
                if (reactionTemp.getName().contains(newGene + "ap")) {
                    newGene = newGene + "ap";
                    found = 1;
                }
            }
            
            if (found == 0) {
                newGene = newGene + "p";
            }
            
            
            perturbedExp = new String();
            for (int i = 0; i < expList.size(); i++) {
                if (expList.get(i).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                        expList.get(i+1).mCode.equals(expList.get(i).mCode.POW)) {
                    if (gene.compareTo(exprate.convertTokenToElement(expList.get(i)).toString()) == 0){
                        perturbedExp = perturbedExp + newGene;
                    } else {
                        perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                    }
                } else {
                    perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                }
            }
//          System.out.println("*: " + perturbedExp);
        }
        
        try{exprate.setExpression(perturbedExp);} catch (Exception d) {d.printStackTrace();}
        reaction.setRate(exprate);
        currentSimModel.setReaction(reaction.getName(), reaction);
//      System.out.println("PERTURBING*: " + reaction.toString());
    }
    public String constructExp(String perturbedExp, ArrayList<Expression.Token> expList, int i, Expression exprate) {
        if (expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN)) {
            perturbedExp = perturbedExp + "(";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.CLOSE_PAREN)) {
            perturbedExp = perturbedExp + ")";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.DIV)) {
            perturbedExp = perturbedExp + "/";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.MINUS)) {
            perturbedExp = perturbedExp + "-";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.MULT)) {
            perturbedExp = perturbedExp + "*";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.PLUS)) {
            perturbedExp = perturbedExp + "+";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.POW)) {
            perturbedExp = perturbedExp + "^";
        }
        if (expList.get(i).mCode.equals(expList.get(i).mCode.EXPRESSION) ||
                expList.get(i).mCode.equals(expList.get(i).mCode.SYMBOL) ||
                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
            //System.out.println("$$$$$$$$$$$$$$$   " + exprate.convertTokenToElement(expList.get(i)).toString());
            perturbedExp = perturbedExp + exprate.convertTokenToElement(expList.get(i)).toString();
        }
        return perturbedExp;
    }
    
    public double[][] runTimeCourse(HashMap propsMap, int metropInit){
        metrop = metropInit;
        ISimulator simulator = runSimulation(propsMap);
        double[][] timeCourseValues = simulator.getTimeCourseValues();
        return timeCourseValues;
    }
    
    void perturbModel2(double[] vmaxVal, double[] degVal) {
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        ElementMath e = new ElementMath();
        int method = -1;
        int degCount = 0;
        int vmaxCount = 0;
        
        for (int j = 0; j < rList.size(); j++) {
            Reaction reaction = rList.get(j);
            
            if (reaction.getName().contains("degradation")) {
                method = 0;
            } else if (reaction.getName().contains("synthesis")){
                method = 1;
            }
            Value foo = new Value(reaction.getRate());
            Expression exprate = foo.getMExpressionValue();
            
            List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
            ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
            
            String perturbedExp = exprate.toString();
            if (method == 0) {
                int index = -1;
                for (int i = 0; i < expList.size(); i++) {
                    if (expList.get(i).mCode.equals(expList.get(i).mCode.MULT)){// &&
//                      expList.get(i + 1).mCode.equals(expList.get(i).mCode.MULT) &&
//                      expList.get(i + 2).mCode.equals(expList.get(i).mCode.SYMBOL)) {
                        index = i-1;
                    }
                }
                perturbedExp = new String();
                for (int i = 0; i < expList.size(); i++) {
                    if (index != -1 && i == index) {
                        perturbedExp = perturbedExp + sigfig(degVal[degCount]);
                        degCount++;
                    } else {
                        perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                    }
                }
            }
            if (method == 1) {
                int index = -1;
                for (int i = 0; i < expList.size(); i++) {
                    if (expList.get(i).mCode.equals(expList.get(i).mCode.SYMBOL)) {
                        index = i;
                        break;
                    }
                }
                perturbedExp = new String();
                for (int i = 0; i < expList.size(); i++) {
                    if (index != -1 && i == index) {
                        perturbedExp = perturbedExp + sigfig(vmaxVal[vmaxCount]);
                        vmaxCount++;
                    } else {
                        perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                    }
                }
            }
            //System.out.println(perturbedExp);
            try{exprate.setExpression(perturbedExp);} catch (Exception d) {d.printStackTrace();}
            reaction.setRate(exprate);
            currentSimModel.setReaction(reaction.getName(), reaction);
        }
    }
    
    public ISimulator runSimulation(final HashMap propsMap){
        //System.out.println(propsMap.toString());
        System.out.println("STARTING MODEL");
        String networkGeneratorKey = Util.getString(propsMap, "NetworkGenerator");
        try{
            INetworkGenerator networkGenerator = NetworkGeneratorFactory.getNetworkGenerator(networkGeneratorKey);
            //initIO(propsMap); //debug
            
            networkGenerator.initialize(propsMap, numKinase, numKinaseCons, numGenes, numConnections, -1 * degradationRate, -1 * mrnaDegRate, -1 * proDegRate, -1 * activeproDegRate, inputs, outputs, K, fanInPercent, actPercent);
            Model model = networkGenerator.generateNetwork();
            currentSimModel = model;
            
            if (networkGeneratorKey.contains("Sbml")) {
                numGenes = currentSimModel.getOrderedSpeciesNamesArray().length - 1;
            }
            
            String networkProcessorKeys = Util.getString(propsMap, "NetworkProcessors");
            String[] arrNetworkProcessorKeys = networkProcessorKeys.split("\t");
            
            for (int i = 0; i < arrNetworkProcessorKeys.length; i++) {
                if (!stopRequested){
                    INetworkProcessor networkProcessor = NetworkProcessorFactory.
                            getNetworkProcessor(arrNetworkProcessorKeys[i]);
                    networkProcessor.initialize(propsMap);
                    networkProcessor.processNetwork(model);
                }
            }
            String simulatorKey = Util.getString(propsMap, "Simulator");
            simulator = SimulatorFactory.getSimulator(simulatorKey);
            simulator.initialize(propsMap);
            String networkGenesProcessor = Util.getString(propsMap, "NetworkGenesProcessor");
            if (networkGenesProcessor != null) {
                try {
                    NetworkGenesProcessor processor = (NetworkGenesProcessor) Class.forName(
                            networkGenesProcessor).newInstance();
                    String[] newGeneList = processor.getGeneListExcluding(model.getOrderedSpeciesNamesArray(), Util.getString(propsMap, "GeneExcludeName"));
                    simulator.setRequestedGeneNames(newGeneList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
//    System.out.println(currentSimModel.toString());
            
            System.out.println("ABOUT TO RUN MODEL");
            //  perturbModel(0); //debug
            //model = currentSimModel;
            inputs[0] = 0;
            outputs[numoutputs - 2] = 1;
            outputs[numoutputs - 1] = 2;
            
            if (metrop == 0) {
                if (ilyaSim == 1) {
                    double[][] vmax = new double[1000][100];
                    double[][] deg = new double[1000][100];
                    double[] maxGene = new double[numGenes];
                    int simNum = 10;
                    int numPhenos = 1000;
                    int numKnockouts = 100;
                    for (int i = 0; i < numGenes; i++) {
                        maxGene[i] = 0.0;
                    }
                    try {
                        File writeFile = new File("C:/Hooman/IlyaData.txt");
                        FileOutputStream os = new FileOutputStream(writeFile);
                        PrintWriter writer = new PrintWriter(os);
                        os = new FileOutputStream(writeFile);
                        writer = new PrintWriter(os);
                        System.out.println("Phenotypes generating");
                        for (int j = 0; j < numPhenos; j++) {
                            currentSimModel = networkGenerator.generateNetwork();
                            System.out.println("generated network" + j);
                            
                            for (int s = 0; s < 100; s++) {
                                vmax[j][s] = Math.random() * 2;
                                deg[j][s] = Math.random() * 2;
                            }
                            perturbModel2(vmax[j], deg[j]);
                            System.out.println(currentSimModel.toString());
                            writer.println("PHENOTYPE #" + j);
                            writer.println(" ");
                            simulator.runSimulation(currentSimModel);
                            double[] SSValues = simulator.getSteadyStateValues();
                            for (int k = 0; k < SSValues.length; k++) {
                                writer.println(SSValues[k]);
                            }
                            writer.println(" ");
                            writer.flush();
                        }
                        
                        writer.println("------------------------------------------------");
                        
                        for (int j = 0; j < numKnockouts; j++) {
                            writer.println(" ");
                            writer.println("Gene knocked out: " + j);
                            writer.println("Initial Conc: 0.0");
                            int[] phenos = new int[simNum];
                            for (int i = 0; i < simNum; i++) {
                                phenos[i] = (int) (Math.random() * numPhenos);
                                currentSimModel = networkGenerator.generateNetwork();
                                perturbModel2(vmax[phenos[i]], deg[phenos[i]]);
                                perturbModel( -2, 0);
                                perturbModel( -3, 0);
                                
                                writer.println(" ");
                                writer.println("Simulation: " + i + " Using Phenotype #" + phenos[i]);
                                writer.println(" ");
                                changeMrnaConc2(ilyaCounter, 0);
                                System.out.println(currentSimModel.toString());
                                simulator.runSimulation(currentSimModel);
                                double[] SSValues = simulator.getSteadyStateValues();
                                for (int k = 0; k < SSValues.length; k++) {
                                    writer.println(SSValues[k] + " ");
                                    if (SSValues[k] > maxGene[j]) {
                                        maxGene[j] = SSValues[k];
                                    }
                                }
                                writer.flush();
                            }
                            writer.println(" ");
                            writer.println("Gene knocked out: " + j);
                            writer.println("Initial Conc (2*max): " + 2 * maxGene[j]);
                            for (int i = 0; i < simNum; i++) {
                                currentSimModel = networkGenerator.generateNetwork();
                                perturbModel2(vmax[phenos[i]], deg[phenos[i]]);
                                perturbModel( -2, 0);
                                perturbModel( -3, 0);
                                
                                writer.println(" ");
                                writer.println("Simulation: " + i + " Using Phenotype #" + phenos[i]);
                                writer.println(" ");
                                changeMrnaConc2(ilyaCounter, 2 * maxGene[j]);
                                System.out.println(currentSimModel.toString());
                                simulator.runSimulation(currentSimModel);
                                double[] SSValues = simulator.getSteadyStateValues();
                                for (int k = 0; k < SSValues.length; k++) {
                                    writer.println(SSValues[k]);
                                }
                                writer.flush();
                            }
                            ilyaCounter++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                } else {
                    
                    if (optimOn == 1) {
                        optimize(networkGenerator);
                        NelderMead foo = new NelderMead();
                        //foo.test();
                    }
                    
                    else if (phenosOn == 1) {
                        phenotypeData(networkGenerator, propsMap);
                    } else {
                        
                        System.out.println(currentSimModel.toString()); 
                        simulator.runSimulation(model);
                        //createSifFile(getGeneInfo());
//                        double[][] SSValues = simulator.getTimeCourseValues();
//                        double[] SSValues2 = simulator.getSteadyStateValues();
//                        for (int i = 0; i < numGenes; i++) {
//                            System.out.print("G" + i);
//                            System.out.print(" ");
//                            System.out.print(SSValues2[i]);
//                            System.out.print(" --- ");
//
//                            for (int j = 0; j < SSValues.length; j++) {
//                                System.out.print(SSValues[j][i]);
//                                System.out.print(" ");
//                            }
//                            System.out.flush();
//                            System.out.println("");
//                        }
                        
                        try {
                            ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                            File writeFile = new File(saveFile);
                            FileOutputStream os = new FileOutputStream(writeFile);
                            PrintWriter writer = new PrintWriter(os);
                            sbmlfile.export(currentSimModel, writer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    if (ilyaSim == 0 && metrop == 1) {
                        ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                        File writeFile;
                        if (stoich == 1) {
                            writeFile = new File("C:/Hooman/STOICH.xml");
                        } else {
                            writeFile = new File("C:/Hooman/SWITCH.xml");
                        }
                        
                        FileOutputStream os = new FileOutputStream(writeFile);
                        PrintWriter writer = new PrintWriter(os);
                        sbmlfile.export(currentSimModel, writer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                //       for(int i = 0; i < SSValues.length;i++)System.out.println(SSValues[i]);
//        System.out.println("OUTPUT " + outputs[numoutputs - 2] + " SS: " + SSValues[2*outputs[numoutputs - 2]]);
//        System.out.println("OUTPUT " + outputs[numoutputs - 1] + " SS: " + SSValues[2*outputs[numoutputs - 1]]);
                
            } else {
                metropolis(propsMap, networkGenerator);
                
                System.out.println("Input Values: ");
                for (int i = 0; i < numSims; i++) {
                    System.out.println(inputVal[i]);
                }
                System.out.println("Output 1 Values (Out1=K*In*Out2/Switched Up): ");
                for (int i = 0; i < numSims; i++) {
                    System.out.println(output1Val[i]);
                }
//        System.out.println("Output 2 Values (Out1=K*In*Out2/Switched Down): ");
//        for(int i = 0; i < numSims; i++) {System.out.println(output2Val[i]);}
                
                
//        for (int i = 0; i < rewireIterations && i < 5000; i++) {
//            System.out.println(chi2Values.get(i).doubleValue());
//        }
            }
            System.out.println("DONE RUNNING MODEL");
            return simulator;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
    public void phenotypeData(INetworkGenerator networkGenerator, HashMap propsMap) {
        String [] geneNames = getSpeciesNames(propsMap);
        XYSeriesCollection plots = new XYSeriesCollection();
        XYSeries[] series = new XYSeries[numGenes];
        CSExprMicroarraySet set = new CSExprMicroarraySet();
        set.setLabel("Synthetic Data" + (int) (Math.random() * 100));
        set.initialize(numPhenos, numGenes);
        for (int k = 0; k < series.length; k++) {
            if (!stopRequested){
                series[k] = new XYSeries(geneNames[k]);
                plots.addSeries(series[k]);
                
                set.getMarkers().get(k).setDescription(geneNames[k]);
                set.getMarkers().get(k).setLabel(geneNames[k] +
                        "_");
            }
        }

        double[][] vmax = new double[numPhenos][currentSimModel.getOrderedSpeciesNamesArray().length-1];
        double[][] deg = new double[numPhenos][currentSimModel.getOrderedSpeciesNamesArray().length-1];
        
        String newSaveFile = saveFile.substring(0, saveFile.length() - 4);
        
        String path = newSaveFile + "_Phenotypes\\";
        String filename = newSaveFile.substring(newSaveFile.lastIndexOf('\\')+1, newSaveFile.length());

        try {
            File newDir = new File(path);
            newDir.mkdir();
            File writeFile = new File(path + filename + "_Data.txt");
            FileOutputStream os = new FileOutputStream(writeFile);
            PrintWriter writer = new PrintWriter(os);
            os = new FileOutputStream(writeFile);
            writer = new PrintWriter(os);
            System.out.println("Phenotypes generating");
            for (int j = 0; j < numPhenos; j++) {
                if (!stopRequested){
                    currentSimModel = networkGenerator.generateNetwork();
                    System.out.println("generated network" + j);

                    setVmaxAndDeg(vmaxRange, degRange);
                    
                    writer.println("PHENOTYPE #" + j);
                    writer.println(" ");
                    try {
                        ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                        File writeFile2 = new File(path + filename + "_Phenotype_" + j + ".xml");
                        FileOutputStream os2 = new FileOutputStream(writeFile2);
                        PrintWriter writer2 = new PrintWriter(os2);
                        sbmlfile.export(currentSimModel, writer2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    simulator.runSimulation(currentSimModel);
                    HashMap tempMap = new HashMap();
                    tempMap.put("array", chi2Values);
                    tempMap.put("simulator", simulator);
                    tempMap.put("model", currentSimModel);
                    firePropertyChange(tempMap);
                    double[] SSValues = simulator.getSteadyStateValues();
                    for (int k = 0; k < SSValues.length; k++) {
                        if (!stopRequested){
                            writer.println("G" + k + " " + SSValues[k]);
                        }
                    }
                    writer.println(" ");
                    writer.flush();
                    
                    
                    set.get(j).setLabel("Phenotype_" + j);
                    for (int k = 0; k < SSValues.length; k++) {
                        if (!stopRequested){
                            series[k].add(j,SSValues[k]);
                            DSMicroarray microarray = set.get(j);
                            DSMutableMarkerValue marker = (DSMutableMarkerValue)microarray.getMarkerValue(k);
                            marker.setValue(SSValues[k]);
                            marker.setMissing(false);
                        }
                    }
                }
                
                set.writeToFile(path + filename + "_Data.exp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void optimize(INetworkGenerator networkGenerator) {
        NelderMead foo = new NelderMead();
        double[][] excelValues = excelValues();
        double[] chi2ForEachNode = new double[numGenes];
        String[] reactionEqns = new String[numGenes];
        
        for (int i = 0; i < numGenes; i++) {
            if (!stopRequested){
                reactionEqns[i] = "";
                chi2ForEachNode[i] = -1;
            }
        }
        System.out.println("OPTIMIZING");
        for (int i = 0; i < numGenes; i++) {
            if (!stopRequested){
                System.out.println(currentSimModel.toString());
                foo = new NelderMead();
                foo.setOptimalGene(1);
                chi2ForEachNode[i] = foo.descend();
                System.out.println(chi2ForEachNode[i] + " should be the same as " + foo.getOptimalchi2());
                reactionEqns[i] = foo.getOptimalReaction();
                currentSimModel = networkGenerator.generateNetwork();
            }
        }
        for (int i = 0; i < numGenes; i++) {
            if (!stopRequested){
                System.out.println("G" + i + " " + reactionEqns[i]);
                System.out.println("Chi2 for G" + i + ": " + chi2ForEachNode[i]);
            }
        }
    }
    
    public double getSSEqn(int geneIndex, int dataRow) {
        ElementMath e = new ElementMath();
        ArrayList<GeneInfo> geneInfo = getGeneInfo();
        double[][] excelValues = excelValues();
        GeneInfo gene = geneInfo.get(geneIndex);
        ArrayList<ReactionInfo> reactionInfo = gene.getSources();
        double mult = 1;
        double eqn = 0;
        
        for (int i = 0; i < reactionInfo.size(); i++) {
            ReactionInfo reaction = reactionInfo.get(i);
            int sourceNum = -1;
            try{sourceNum = Integer.valueOf(reaction.getSource().substring(1,2));}
            catch (Exception f) {f.printStackTrace();}
            if (sourceNum != -1) {
                mult = mult * reaction.getTermValue(excelValues[dataRow][sourceNum]);
            }
        }
        eqn = 1 / gene.getDeg() * ((0.01) + mult);
        return eqn;
    }

    public String[] getSpeciesNames(HashMap propsMap){
//   String networkGeneratorKey = Util.getString(propsMap, "NetworkGenerator");
        //  INetworkGenerator networkGenerator = NetworkGeneratorFactory.getNetworkGenerator(networkGeneratorKey);
        //  networkGenerator.initialize(propsMap);
        Model model = currentSimModel;//networkGenerator.generateNetwork();
        String[] origSpeciesNames = model.getOrderedSpeciesNamesArray();
        String networkGenesProcessor = Util.getString(propsMap, "NetworkGenesProcessor");
        if(networkGenesProcessor != null){
            try{
                NetworkGenesProcessor processor = (NetworkGenesProcessor)Class.forName(
                        networkGenesProcessor).newInstance();
                return processor.getGeneListExcluding(origSpeciesNames, Util.getString(propsMap, "GeneExcludeName"));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            return origSpeciesNames;
        }
        return null;
    }
    
    //This is the non-parallel version of the MPI simulation
    public double[][] runSimulations(String[] args) {
        File dir = new File(args[args.length - 1]);
        File[] files = dir.listFiles();
        HashMap propsMap = Util.readHashMapFromFiles(files, "#");
        return runSimulations(propsMap);
    }
    
    
    public double[][] runSimulations(HashMap propsMap){
        
        int numSimulations = Util.getInt(propsMap, "NumSimulations");
        double[][] allResults = new double[numSimulations][];
        for (int simCtr = 0; simCtr < numSimulations; simCtr++) {
            allResults[simCtr] = runSteadyState(propsMap);
        }
        
        for(int i = 0; i < allResults.length; i++){
            for(int j = 0; j < allResults[i].length; j++){
                System.out.print(allResults[i][j] + "\t");
            }
            System.out.println();
        }
        
        return allResults;
        
    }
    
    public double[][] excelValues() {
        //praf	pmek	plcg	PIP2	PIP3	p44/42	pakts473	PKA	PKC	P38	pjnk
        
        
        String[][] values;
        try {
            File readFile = new File("C:/Hooman/Hoomanb2camp.csv");
            FileInputStream os = new FileInputStream(readFile);
            ExcelCSVParser foo = new ExcelCSVParser(os);
            values = foo.getAllValues();
            double[][] excelValues = new double[values.length][values[0].length];
            
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
//                    System.out.println(values[i][j]);
                    excelValues[i][j] = Double.valueOf(values[i][j]);
                }
            }
            
//            System.out.println("TWO EXCEL VALUES: " + excelValues[708][5] + " " + excelValues[0][8]);
            return excelValues;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("DID NOT CONVERT EXCEL SHEET!!");
        return null;
    }
    
    public class NelderMead {
        int NDIMS = 5;
        int NPTS = 6;
        int FUNC = NDIMS;
        int MAXITER = 1000;
        int ncalls = 0;
        double TOL = 1E-20;
        double avgSimplexChi = 1000;
        double prevAvgSimplexChi = 1000;
        double optimalchi2 = 100000;
        int optimalGene = -1;
        String optimalReaction = "";
        
        public  void main(String args[]) {
        }
        
        double[] optVals2 = //new double[80];
        {17.850361851252206, 17.85036093363224, 17.850351608609873, 17.85031980111604, 17.850247087319026,
         17.850111063212484, 17.84988501077328, 17.849537236367578, 17.849030580468234, 17.848322864252278,
         17.847369169047173, 17.846126563882883, 17.84456105328393, 17.84265532704882, 17.84041498985779,
         17.837871014331956, 17.835077348118258, 17.832104316577315, 17.829029778261642, 17.825930327421908,
         17.822874163770788, 17.819914005226547, 17.8062238748125, 0.03338163699203317, 0.03121426638904106,
         0.02954803674845954, 0.028238755115172737, 0.027192709475410453, 0.026345097160289807, 0.02564980661150266,
         0.02507329039490531, 0.024590675896925693, 0.024183213715511666, 0.02383656027430257, 0.023539596505160413,
         0.023283599569530876, 0.023061652080477175, 0.022868214149369848, 0.022698808980542304, 0.022549788887787504,
         0.022418159085288786, 0.022301443529122153, 0.022197581736532423, 0.022104848682607963, 0.022021792068519484,
         0.021947182793533318, 0.02187997555413196, 0.021819277276490374, 0.021764321656336915, 0.02171444849610865};
        
        double[] optValsX = //new double[80];
        {17.850361851252206, 17.850351608609873, 17.850247087319026,
         17.84988501077328, 17.849030580468234,
         17.847369169047173, 17.84456105328393, 17.84041498985779,
         17.835077348118258, 17.829029778261642,
         17.822874163770788, 17.8062238748125, 0.03121426638904106,
         0.028238755115172737, 0.026345097160289807,
         0.02507329039490531, 0.024183213715511666, 0.023539596505160413,
         0.023061652080477175, 0.022698808980542304,
         0.022418159085288786, 0.022197581736532423, 0.022021792068519484,
         0.02187997555413196, 0.021764321656336915 };
        
        double[] optVals = //new double[80];
        {17.850361851252206, 17.85036185122334, 17.850361840947,
         17.850361530391794, 17.850358171182464,
         17.8503374159023, 17.85024676050587, 17.849931509971054,
         17.84898823412097, 17.846463094559635,
         17.8406537405025, 17.83047087167325, 17.817927012338583,
         0.027854045665764143, 0.024355550435262413,
         0.02280913092181036, 0.022046386382272534, 0.021641857362631897,
         0.021415769439588984, 0.021284175221183435,
         0.02120499790675824, 0.021155999290131774, 0.021124924671537794,
         0.021104784637464555, 0.021091474409415282};
        
        
        double[] optVals3 = {82.44394743767542, 82.4439474329017, 82.44394621563329, 82.4439161180918, 82.4436345974264,
        82.44208284143419, 82.43593132632618, 82.41644866599722, 82.36402924137029, 82.23956536559831,
        81.97243442241574, 81.44646707251665, 80.48730322954812, 78.84883762052029, 76.1483758005901,
        71.50293014362374, 61.941664159182594, 37.86263545175215, 5.247604901537083, 0.5097619901292705,
        0.22973702790081127, 0.1767786921456273, 0.15810464780624017, 0.14932890238605215,
        0.14452014251371487, 0.14163079147342528, 0.13978690162840063, 0.13855951282251655,
        0.1377166130485278, 0.1371236006457879, 0.13669819832625507, 0.13638805822656958,
        0.1361588089101645, 0.1359873017157547, 0.13585761624506526, 0.13575860825098826,
        0.1356823575497381, 0.13562316019073, 0.13557685974414213, 0.1355403953086159};
        
        double[] randomInputsXXXX = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9,
        1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9,
        2, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9,
        3, 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9,
        4, 4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9};
        
        double[] randomInputsX = {0, 0.2, 0.4, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8,
        2,  2.2, 2.4, 2.6, 2.8, 3, 3.2, 3.4, 3.6, 3.8,
        4, 4.2, 4.4, 4.6, 4.8};
        
        double[] randomInputsXXX = {0, 0.8, 1.6, 2.4, 3.2, 4, 4.8, 5.6, 6.4, 7.2,
        8,  8.8, 9.6, 10.4, 11.2, 12, 12.8, 13.6, 14.4, 15.2,
        16, 16.8, 17.6, 18.4, 19.2};
        
        double[] randomInputs = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45,
        50,  55, 60, 65, 70, 75, 80, 85, 90, 95};
        
//                              5, 5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8, 5.9};
//                              6, 6.1, 6.2, 6.3, 6.4, 6.5, 6.6, 6.7, 6.8, 6.9,
//                              7, 7.1, 7.2, 7.3, 7.4, 7.5, 7.6, 7.7, 7.8, 7.9};
        
        double[] randomInputsXX = {0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5,
        6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5,
        12.0, 12.5, 13.0, 13.5, 14.0, 14.5, 15.0, 15.5, 16.0, 16.5, 17.0, 17.5,
        18.0, 18.5, 19.0, 19.5};
        
        
        
        
        public int getReactionNum(int num) {
            String[] reactions = currentSimModel.getOrderedSpeciesNamesArray();
            
            int index = -1;
            for (int j = 0; j < reactions.length; j++) {
                if (reactions[j].contains("G" + num + "m")) {
//                 System.out.println("The num " + num + " the index " + j + " the name " + reactions[j]);
                    index = j;
                    break;
                }
            }
            return index;
        }
        public int getReactionNumString(String gene) {
            String[] reactions = currentSimModel.getOrderedSpeciesNamesArray();
            
            int index = -1;
            for (int j = 0; j < reactions.length; j++) {
                if (reactions[j].contains(gene)) {
//                 System.out.println("The num " + num + " the index " + j + " the name " + reactions[j]);
                    index = j;
                    break;
                }
            }
            return index;
        }
        
        public void optimEdge() {
//         System.out.println("OPTIM EDGE!!");
            Collection<Reaction> temp = currentSimModel.getReactions();
            ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
            String[][] perturbedExp = new String[rList.size()][20];
            
            for (int j = 0; j < rList.size(); j++) {
                for (int i = 0; i < 20; i++) {
                    perturbedExp[j][i] = "-1";
                }
                Reaction reaction = rList.get(j);
                if (reaction.getName().contains("m")) {
                    Value foo = new Value(reaction.getRate());
                    Expression exprate = foo.getMExpressionValue();
                    
                    List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                    ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                    
                    int index = 0;
                    int changed = 1;
                    int expNum = 0;
                    
                    while (changed == 1) {
                        changed = 0;
                        perturbedExp[j][expNum] = new String();
                        for (int i = 0; i < index; i++) {
                            perturbedExp[j][expNum] = constructExp(perturbedExp[j][expNum], expList, i, exprate);
                        }
                        for (int i = index; i < expList.size(); i++) {
                            
                            //           ((1.0)*((1.0)*(((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))))
                            //                  ((1.0)*(((0.01)^(1.5))/((G2p^(1.5))+((0.01)^(1.5)))))
                            //                  ((1.0)*((G3p^(1.5))/((G3p^(1.5))+((0.01)^(1.5)))))
                            //           ((1.0)*((1.0)*((G1p^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))))
                            //           ((((1.0)*(1.0))*((1.0)*((G1p^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))))
                            
                            if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                                if (changed == 0 &&
                                        (i + 6) < expList.size() && (i - 2) >= 0 &&
                                        expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                        expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                        expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                        expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                        expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                                    
                                    perturbedExp[j][expNum] = perturbedExp[j][expNum] + "1.0)"; //"5)*((g4^(7))/((g4^(5))+((8)^(7  )))))";
                                    i += 31;
                                    index = i;
                                    changed = 1;
                                }
                                //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                                else if (changed == 0 &&
                                        (i + 8) < expList.size() && (i - 2) >= 0 &&
                                        expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                        expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                        expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                        expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                        expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                        expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                        expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                                    perturbedExp[j][expNum] = perturbedExp[j][expNum] + "1.0)"; // "5)*(((6)^(6))/((g5^(5))+((5)^(5  ))))";
                                    i += 33;
                                    index = i;
                                    changed = 1;
                                    
                                } else {
                                    perturbedExp[j][expNum] = constructExp(perturbedExp[j][expNum], expList, i, exprate);
                                }
                                
                            } else {
                                perturbedExp[j][expNum] = constructExp(perturbedExp[j][expNum], expList, i, exprate);
                            }
                        }
                        if (changed == 1) {
                            expNum++;
                        }
                    }
                    //System.out.println("*** " + reaction.getName() + " *** "  + perturbedExp);
                    expNum = 0;
                    
                    for (int s = 0; s < perturbedExp[j].length; s++) {
                        if (!perturbedExp[j][s].equals("-1")) {
                            System.out.println("PERTURBED EXP " + s + ": " + perturbedExp[j][s]);
                        }
                    }
                    while (!perturbedExp[j][expNum].equals("-1")) {
                        try {
                            exprate.setExpression(perturbedExp[j][expNum]);
                        } catch (Exception d) {
                            d.printStackTrace();
                        }
                        reaction.setRate(exprate);
                        currentSimModel.setReaction(reaction.getName(), reaction);
                        double chi2 = test(1);
                        if (Math.abs(chi2 - optimalchi2) < 5) {
                            System.out.println(" * " + chi2 + " is redundant!");
                            //STORE CHANGES IN perturbedExp --- merge into final equation
                            //or STORE CHANGES INTO another model
                        } else {
                            System.out.println(" * " + chi2 + " is important!");
                            perturbedExp[j][expNum] = "-1";
                        }
                        expNum++;
                    }
                    System.out.println("");
                    
                }
            }
            
            optimEdgeRemove(perturbedExp);
            
        }
        
        public void removeUseless() {
            Collection<Reaction> temp = currentSimModel.getReactions();
            ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
            
            boolean[] checked = new boolean[numGenes];
            String[] useful = new String[numGenes];
            boolean added = false;
            
            for (int i = 0; i < numGenes; i++) {
                checked[i] = false;
                useful[i] = new String();
                useful[i] = "useless";
                useful[0] = "G0p";
            }
            
            for (int i = 0; i < rList.size(); i++) {
                Reaction reaction = rList.get(i);
                int geneIndex = Integer.valueOf(String.valueOf(reaction.getName().charAt(1)));
                if (reaction.getName().contains("m")) {
                    for (int j = 0; j < numGenes; j++) {
                        if (!checked[geneIndex] && reaction.toString().contains(useful[j])) {
                            checked[geneIndex] = true;
                            useful[geneIndex] = "G" + geneIndex + "p";
                            i = 0;
                        }
                    }
                }
            }
            System.out.println("USELESS:");
            for (int i = 0; i < numGenes; i++) {
                System.out.println(useful[i]);
            }
        }
        
        public void optimEdgeRemove(String[][] perturbedExpList) {
//         System.out.println("OPTIM EDGE REMOVE!!");
            
            Collection<Reaction> temp = currentSimModel.getReactions();
            ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
            String[] perturbedExp = new String[rList.size()];
            
            for (int j = 0; j < rList.size(); j++) {
                for (int i = 0; i < 20; i++) {
                    perturbedExp[j] = "-1";
                }
                
//             for (int s = 0; s < perturbedExpList[j].length; s++) {
//                 if (!perturbedExpList[j][s].equals("-1")) {
//                     System.out.println("PERTURBED EXP TO REMOVE" + s + ": " + perturbedExpList[j][s]);
//                 }
//             }
                
                
                Reaction reaction = rList.get(j);
                if (reaction.getName().contains("m")) {
                    Value foo = new Value(reaction.getRate());
                    Expression exprate = foo.getMExpressionValue();
                    
                    List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                    ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                    
                    int expNum = 0;
                    
                    perturbedExp[j] = new String();
                    for (int i = 0; i < expList.size(); i++) {
                        
                        if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                            if ((i + 6) < expList.size() && (i - 2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                                
                                if (!perturbedExpList[j][expNum].equals("-1")) {
                                    perturbedExp[j] = perturbedExp[j] + "1.0)"; //"5)*((g4^(7))/((g4^(5))+((8)^(7  )))))";
                                    i += 31;
                                    System.out.println("GETTING RID OF THIS ONE 1: " + perturbedExp[j]);
                                } else{
//                                 System.out.println("NOT GETTING RID 1");
                                    perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                                }
                                expNum++;
                            } else if ((i + 8) < expList.size() && (i - 2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                                
                                if (!perturbedExpList[j][expNum].equals("-1")) {
                                    perturbedExp[j] = perturbedExp[j] + "1.0)"; // "5)*(((6)^(6))/((g5^(5))+((5)^(5  ))))";
                                    i += 33;
                                    System.out.println("GETTING RID OF THIS ONE 2: " + perturbedExp[j]);
                                } else{
//                                 System.out.println("NOT GETTING RID 2");
                                    perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                                }
                                expNum++;
                                
                            } else {
                                perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                            }
                            
                        } else {
                            perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                        }
                    }
                    
                    System.out.println("Final Equation: " + perturbedExp[j]);
                    try {
                        exprate.setExpression(perturbedExp[j]);
                    } catch (Exception d) {
                        d.printStackTrace();
                    }
                    
                    expNum = 0;
                    reaction.setRate(exprate);
                    currentSimModel.setReaction(reaction.getName(), reaction);
                }
            }
            try {
                ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                File writeFile = new File("C:/Hooman/REDUNDANT_GONE.xml");
                FileOutputStream os = new FileOutputStream(writeFile);
                PrintWriter writer = new PrintWriter(os);
                sbmlfile.export(currentSimModel, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        public double test(int method) {
            double termSum = 0;
            double[] InhActSum = new double[1000];
            for (int i = 0; i < 1000; i++) {
                InhActSum[i] = -1;
            }
            double[] chi2 = new double[numGenes];
//          System.out.println(currentSimModel.toString());
            double[] random = randomInputs;
            double[] SSValues = new double[numGenes];
            for (int i = 0; i < numGenes; i++) {
                SSValues[i] = 1.0;
            }
            
            for(int i = 0; i < random.length; i++) {
                HashMap tempMap = new HashMap();
                tempMap.put("array", chi2Values);
                tempMap.put("simulator", simulator);
                tempMap.put("model", currentSimModel);
                firePropertyChange(tempMap);
                if (i > 0) {
                    for (int q = 0; q < numGenes; q++) {
//                       changeMrnaConc(q, SSValues[getReactionNum(q)]);
                    }
                }
                inputpro = random[i];// + Math.random();
                changeMrnaConc(inputs[0], inputpro);
                
                simulator.runSimulation(currentSimModel);
                System.out.print(" sim" + i);
                System.out.flush();
                SSValues = simulator.getSteadyStateValues();
                
                termSum += sumTerms(SSValues, InhActSum);
                
                for (int k = 0; k < numGenes; k++) {
                    outputpro = SSValues[getReactionNum(k)];
                    
                    chi2[k] = chi2[k] + (optVals[i] - outputpro) * (optVals[i] - outputpro);
                    output1Val2[k][i] = outputpro;//SSValues[2 * mConstraint1];
                }
                inputVal[i] = inputpro;//SSValues[2 * mConstraint0];
            }
            double chi2best = 1000000;
            int index = -1;
            termSum = termSum/random.length;
            if (method == 0) {
                for (int i = 0; i < numGenes; i++) {
                    chi2[i] = chi2[i] / random.length;
                    if (chi2[i] < chi2best) {
                        chi2best = chi2[i];
                        index = i;
                        optimalGene = i;
                        optimalchi2 = chi2best;
                    }
                }
            } else {
                chi2best = chi2[optimalGene] / random.length;
                index = optimalGene;
            }
            
            for (int x =0; x < numGenes; x++) {
                System.out.println(chi2[x]);
            }
//          System.out.println("");
            System.out.print("Output for Opt Gene " + index + ": ");
            for (int i = 0; i < output1Val2[index].length; i++) {
                System.out.print(output1Val2[index][i] + " ");
            }
            System.out.println("");
            
            System.out.print(" opt gene is gene " + index + " with chi2 " + chi2best);
            System.out.println("Term Sum: " + termSum);
            int x = 0;
            while (InhActSum[x] != -1) {
//              System.out.println("individual term sum " + x + ": " + InhActSum[x]);
                x++;
            }
//          removeTerms(InhActSum);
            System.out.flush();
//          System.out.println("OPTIMAL GENE:  " + optimalGene);
            return chi2best;
        }
        
        public void setOptimalChi(double foo) {
            optimalchi2 = foo;
        }
        public void setOptimalGene(int foo) {
            optimalGene = foo;
        }
        
        public double descend() {
            double startTime = System.currentTimeMillis();
            
            for (int i = 0; i < numGenes; i++) {
                optGene[i] = -1;
            }
//          double[] params = {1.5, 0.1, 1.0, 1.5, 8.0, 8.0, 0.1, 14.0, 0.12, 2.0, 0.1};
//          double[] start = {Math.random()*20, Math.random()*2, Math.random()*5, Math.random()*20, Math.random()*5,
//                           Math.random()*2, Math.random()*2, Math.random()*20, Math.random()*2, Math.random()*5, Math.random()*2};
            double[] start = combineParams(getParams());
            NDIMS = start.length;
            NPTS = start.length + 1;
            FUNC = start.length;
            
            double[][] simplex = new double[NPTS][];
            
            for (int i = 0; i < NPTS; i++) {
//              double [] start2 = {Math.random()*20, Math.random()*2, Math.random()*5, Math.random()*20, Math.random()*5,
//                           Math.random()*2, Math.random()*2, Math.random()*20, Math.random()*2, Math.random()*5, Math.random()*2};
                simplex[i] = perturbPoint(start);
            }
            
            double best = 1E99;
            
            //////////////// initialize the funcvals ////////////////
            avgSimplexChi = 0;
            System.out.println(NPTS + " " + NDIMS + " " + FUNC);
            for (int i=0; i<NPTS; i++) {
                simplex[i][FUNC] = func(simplex[i]);
            }
            
            System.out.println("ncalls = "+fwi(ncalls,6));
            int iter=0;
            
            for (iter=1; iter<MAXITER; iter++) {/////////// identify lo, nhi, hi points //////////////
                System.out.println("Simplex Iteration: " + iter);
                double flo = simplex[0][FUNC];
                double fhi = flo;
                int  ilo=0, ihi=0, inhi = -1; // -1 means missing
                for (int i = 1; i < NPTS; i++) {
                    if (simplex[i][FUNC] < flo) {
                        flo = simplex[i][FUNC];
                        ilo = i;
                    }
                    if (simplex[i][FUNC] > fhi) {
                        fhi = simplex[i][FUNC];
                        ihi = i;
                    }
                }
                double fnhi = flo;
                inhi = ilo;
                for (int i = 0; i < NPTS; i++)
                    if ((i != ihi) && (simplex[i][FUNC] > fnhi)) {
                    fnhi = simplex[i][FUNC];
                    inhi = i;
                    }
                
                for (int j = 0; j <= NDIMS; j++)
                    System.out.print(fwd(simplex[ilo][j], 18, 9));
                System.out.println();
                ////////// exit criterion //////////////
                
                if ((iter % 4 * NDIMS) == 0) {
                    if (simplex[ilo][FUNC] > best - TOL)
                        break;
                    best = simplex[ilo][FUNC];
                }
                ///// compute ave[] vector excluding highest vertex //////
                
                double ave[] = new double[NDIMS];
                for (int j = 0; j < NDIMS; j++)
                    ave[j] = 0;
                for (int i = 0; i < NPTS; i++)
                    if (i != ihi)
                        for (int j = 0; j < NDIMS; j++)
                            ave[j] += simplex[i][j];
                for (int j = 0; j < NDIMS; j++)
                    ave[j] /= (NPTS - 1);
                
                ///////// try reflect ////////////////
                
                double r[] = new double[NDIMS];
                for (int j = 0; j < NDIMS; j++)
                    r[j] = 2 * ave[j] - simplex[ihi][j];
                double fr = func(r);
                
                if ((flo <= fr) && (fr < fnhi)) { // in zone: accept
                    for (int j = 0; j < NDIMS; j++)
                        simplex[ihi][j] = r[j];
                    simplex[ihi][FUNC] = fr;
                    continue;
                }
                
                if (fr < flo) { //// below zone; try expand, else accept
                    double e[] = new double[NDIMS];
                    for (int j = 0; j < NDIMS; j++)
                        e[j] = 3 * ave[j] - 2 * simplex[ihi][j];
                    double fe = func(e);
                    if (fe < fr) {
                        for (int j = 0; j < NDIMS; j++)
                            simplex[ihi][j] = e[j];
                        simplex[ihi][FUNC] = fe;
                        continue;
                    } else {
                        for (int j = 0; j < NDIMS; j++)
                            simplex[ihi][j] = r[j];
                        simplex[ihi][FUNC] = fr;
                        continue;
                    }
                }
                
                ///////////// above midzone, try contractions:
                
                if (fr < fhi) { /// try outside contraction
                    double c[] = new double[NDIMS];
                    for (int j = 0; j < NDIMS; j++)
                        c[j] = 1.5 * ave[j] - 0.5 * simplex[ihi][j];
                    double fc = func(c);
                    if (fc <= fr) {
                        for (int j = 0; j < NDIMS; j++)
                            simplex[ihi][j] = c[j];
                        simplex[ihi][FUNC] = fc;
                        continue;
                    } else { /////// contract
                        for (int i = 0; i < NPTS; i++)
                            if (i != ilo) {
                            for (int j = 0; j < NDIMS; j++)
                                simplex[i][j] = 0.5 * simplex[ilo][j] + 0.5 * simplex[i][j];
                            simplex[i][FUNC] = func(simplex[i]);
                            }
                        continue;
                    }
                }
                if (fr >= fhi) { /// over the top; try inside contraction
                    double cc[] = new double[NDIMS];
                    for (int j = 0; j < NDIMS; j++)
                        cc[j] = 0.5 * ave[j] + 0.5 * simplex[ihi][j];
                    double fcc = func(cc);
                    if (fcc < fhi) {
                        for (int j = 0; j < NDIMS; j++)
                            simplex[ihi][j] = cc[j];
                        simplex[ihi][FUNC] = fcc;
                        continue;
                    } else { ///////// contract
                        for (int i = 0; i < NPTS; i++)
                            if (i != ilo) {
                            for (int j = 0; j < NDIMS; j++)
                                simplex[i][j] = 0.5 * simplex[ilo][j] + 0.5 * simplex[i][j];
                            simplex[i][FUNC] = func(simplex[i]);
                            }
                    }
                }
            }
            
  /*        for (int i = 0; i < numGenes; i++) {
              if (optGene[i] == best && optGene[i] != -1) {
                  System.out.println("Optimal Gene is ULTIMATELY gene " + i);
                  setParams(optParam[i]);
              }
          }*/
            try {
                ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                File writeFile = new File("C:/Hooman/HOOMAN_DOES_BIO.xml");
                FileOutputStream os = new FileOutputStream(writeFile);
                PrintWriter writer = new PrintWriter(os);
                sbmlfile.export(currentSimModel, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("ncalls, iters, Best =" + fwi(ncalls, 6) + fwi(iter, 6) + fwd(best, 16, 9));
            double endTime = System.currentTimeMillis();
            
            double totalTimeMin = (endTime - startTime)/60000;
            System.out.println("Total Minutes: " + totalTimeMin);
            double totalMins = (int)(((totalTimeMin / 60) - Math.floor(totalTimeMin / 60)) * 60);
            double totalHours = Math.floor(totalTimeMin / 60);
            System.out.println(totalHours + " hours and " + totalMins + " minutes");
            return best;
        }
        
        public double funcX(double param[]) {
            ncalls++;
            double[] InhActSum = new double[1000];
            for (int i = 0; i < 1000; i++) {
                InhActSum[i] = 0;
            }
            
            double termSum = 0;
            
            
            for (int i = 0; i < param.length; i++) {
                if (param[i] < 0.01)
                    return 10000;
            }
            
            if (param.length != NDIMS) {
                double[] paramList = new double[param.length - 1];
                
                for (int i = 0; i < param.length - 1; i++) {
                    paramList[i] = param[i];
                }
                
                for (int i = 0; i < paramList.length; i++) {
                    System.out.print(paramList[i] + " ");
                }
                System.out.println("PARAM LENGTH: " + paramList.length);
                
                setParams(paramList);
                
            } else {
                for (int i = 0; i < param.length; i++) {
                    System.out.print(param[i] + " ");
                }
                System.out.println("PARAM LENGTH: " + param.length);
                setParams(param);
            }//*/
            //double chi2 = 0;
            double[] chi2 = new double[numGenes];
            double[] random = randomInputs;
            
            double[] SSValues = new double[numGenes];
            for (int i = 0; i < numGenes; i++) {
                SSValues[i] = 1.0;
            }
            for (int i = 0; i < random.length; i++) {
                HashMap tempMap = new HashMap();
                tempMap.put("array", chi2Values);
                tempMap.put("simulator", simulator);
                tempMap.put("model", currentSimModel);
                firePropertyChange(tempMap);
                
                if (i > 0) {
                    for (int q = 0; q < numGenes; q++) {
//                      changeMrnaConc(q, SSValues[getReactionNum(q)]);
                    }
                }
                inputpro = random[i];
                changeMrnaConc(inputs[0], inputpro);
                simulator.runSimulation(currentSimModel);
                SSValues = simulator.getSteadyStateValues();
                termSum += sumTerms(SSValues, InhActSum);
                System.out.println(currentSimModel.toString());
                System.out.print(" sim" + i);
                System.out.flush();
                //*****************************************
                for (int k = 0; k < numGenes; k++) {
                    outputpro = SSValues[getReactionNum(k)];
                    for (int j = 0; j < random.length; j++) {
                        if (Math.abs(inputpro - random[j]) <= 0.1) {
                            //chi2 = chi2 + (optVals[j] - outputpro) * (optVals[j] - outputpro);
                            double term = (optVals[j] - outputpro);
                            chi2[k] = chi2[k] + Math.pow(term,2);
//                           System.out.println("Sim Input: " + inputpro + " Orig Input: " + random[j] + " Output OPT: " + optVals[j] + " Output SS: " + outputpro);
                            break;
                        }
                    }
                    output1Val2[k][i] = outputpro;//SSValues[2 * mConstraint1];
                }
                inputVal[i] = inputpro;//SSValues[2 * mConstraint0];
//             output1Val[i] = outputpro;//SSValues[2 * mConstraint1];
                
                //******************************************/
                
            }
            System.out.print("Inputs: ");
            for (int i = 0; i < inputVal.length;i++) {
                System.out.print(inputVal[i] + " ");
            }
            System.out.println("");
//          System.out.print("Outputs: ");
//          for (int i = 0; i < output1Val.length;i++) {
//              System.out.print(output1Val[i] + " ");
//          }
//          System.out.println("");
            
            
            termSum = termSum / random.length;
            double chi2best = 1000000;
            int index = -1;
            for(int i = 0; i < numGenes; i++) {
                chi2[i] = chi2[i] / random.length;
                if (chi2[i] < chi2best) {
                    chi2best = chi2[i];
                    index = i;
                }
            }
            
            if (optGene[index] == -1) {
                optGene[index] = chi2best;
                optParam[index] = combineParams(getParams());
            } else if (optGene[index] != -1 && chi2best < optGene[index]) {
                optGene[index] = chi2best;
                optParam[index] = combineParams(getParams());
                System.out.println("CURRENT Optimal Gene is gene " + index + " with chi2 " + chi2best);
                try {
                    ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                    File writeFile = new File("C:/Hooman/HOOMAN_DOES_BIO.xml");
                    FileOutputStream os = new FileOutputStream(writeFile);
                    PrintWriter writer = new PrintWriter(os);
                    sbmlfile.export(currentSimModel, writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("");
            System.out.print("Output for Opt Gene " + index + ": ");
            for (int i = 0; i < output1Val2[index].length; i++) {
                System.out.print(output1Val2[index][i] + " ");
            }
            System.out.println("");
            
            //System.out.println(currentSimModel.toString());
            System.out.println("Chi2 for opt gene " + index + ": " + chi2best);
            chi2Values.add(chi2best);
            System.out.println("term Sum: " + termSum);
//          return (chi2best + termSum);
            return (chi2best);
        }
        public void test() {
            double[][] excelValues = excelValues();
            double[] SSValues = new double[numGenes];
            double chi2best = 0.0;
            setOptimalGene(0);
            for (int i = 0; i < numGenes; i++) {
                SSValues[i] = 1.0;
            }
            System.out.println("optimized values: ");
            simulator.runSimulation(currentSimModel);
            for (int q = 0; q < 50; q++) {
                for (int j = 0; j < numGenes; j++) {
                     changeMrnaConc(j, excelValues[q][j]);
                }
                simulator.runSimulation(currentSimModel);
                SSValues = simulator.getSteadyStateValues();
                chi2best = chi2best + Math.pow((excelValues[q][optimalGene] - SSValues[getReactionNum(optimalGene)]), 2);
                System.out.print(SSValues[getReactionNum(optimalGene)] + " ");
            }
            System.out.flush();
            System.out.println("");
            System.out.println("actual values with chi2 of " + chi2best + " : ");
            for (int q = 0; q < 50; q++) {
                System.out.print(excelValues[q][optimalGene] + " ");
            }
            System.out.flush();
        }
        public double func(double param[]) {
            ncalls++;
            double[] InhActSum = new double[1000];
            for (int i = 0; i < 1000; i++) {
                InhActSum[i] = 0;
            }
            
            for (int i = 0; i < param.length; i++) {
                if (param[i] < 0.01)
                    return 10000;
            }
            
            if (param.length != NDIMS) {
                double[] paramList = new double[param.length - 1];
                
                for (int i = 0; i < param.length - 1; i++) {
                    paramList[i] = param[i];
                }
                
                for (int i = 0; i < paramList.length; i++) {
                    System.out.print(paramList[i] + " ");
                }
                System.out.println("PARAM LENGTH: " + paramList.length);
                
                setParams(paramList);
                
            } else {
                for (int i = 0; i < param.length; i++) {
                    System.out.print(param[i] + " ");
                }
                System.out.println("PARAM LENGTH: " + param.length);
                setParams(param);
            }//*/
            
            double chi2best = 0;
            double[][] excelValues = excelValues();
            
            double[] SSValues = new double[numGenes];
            for (int i = 0; i < numGenes; i++) {
                SSValues[i] = 1.0;
            }
            
            HashMap tempMap = new HashMap();
            tempMap.put("array", chi2Values);
            tempMap.put("simulator", simulator);
            tempMap.put("model", currentSimModel);
            firePropertyChange(tempMap);
            
//                System.out.println(currentSimModel.toString());
            
            //                 Input1                                                 Input2
            //praf	pmek	plcg	PIP2	PIP3	p44/42	pakts473	PKA	PKC	P38	pjnk

            for (int q = 0; q < excelValues.length; q++) {
                for (int j = 0; j < numGenes; j++) {
                    if (excelValues[0][j] != -1)
                     changeMrnaConc(j, excelValues[q][j]);
                }
                simulator.runSimulation(currentSimModel);
                SSValues = simulator.getSteadyStateValues();

                chi2best = chi2best + Math.pow((excelValues[q][optimalGene] - SSValues[getReactionNum(optimalGene)]), 2);
            }
            
            if (chi2best < optimalchi2) {
                optimalchi2 = chi2best;
                Collection<Reaction> temp = currentSimModel.getReactions();
                ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
                int target = 0;
                Reaction reaction = rList.get(target);
                while (!reaction.getName().contains("G" + optimalGene + "m")) {
                    target++;
                    reaction = rList.get(target);
                }
                optimalReaction = reaction.toString();
                System.out.println("OPTIMAL REACTION: " + reaction.toString());
            }
            
            chi2best = chi2best / excelValues.length;
            System.out.println("chi2 is: " + chi2best);
            chi2Values.add(chi2best);
            return (chi2best);
        }
        
        public double funcNEW(double param[]) {
            ncalls++;
            double[] InhActSum = new double[1000];
            for (int i = 0; i < 1000; i++) {
                InhActSum[i] = 0;
            }
            
            for (int i = 0; i < param.length; i++) {
                if (param[i] < 0.01)
                    return 10000;
            }
            
            if (param.length != NDIMS) {
                double[] paramList = new double[param.length - 1];
                
                for (int i = 0; i < param.length - 1; i++) {
                    paramList[i] = param[i];
                }
                
                for (int i = 0; i < paramList.length; i++) {
                    System.out.print(paramList[i] + " ");
                }
                System.out.println("PARAM LENGTH: " + paramList.length);
                
                setParams(paramList);
                
            } else {
                for (int i = 0; i < param.length; i++) {
                    System.out.print(param[i] + " ");
                }
                System.out.println("PARAM LENGTH: " + param.length);
                setParams(param);
            }//*/
            
            double chi2best = 0;
            double[] excelValues = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
            
            HashMap tempMap = new HashMap();
            tempMap.put("array", chi2Values);
            tempMap.put("simulator", simulator);
            tempMap.put("model", currentSimModel);
            firePropertyChange(tempMap);
            
            for (int q = 0; q < 40; q++) {
                chi2best = chi2best + Math.pow((excelValues[q] - getSSEqn(optimalGene, q)), 2);
            }
            chi2best = chi2best / 40;
            
            if (chi2best < optimalchi2) {
                optimalchi2 = chi2best;
                Collection<Reaction> temp = currentSimModel.getReactions();
                ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
                int target = 0;
                Reaction reaction = rList.get(target);
                while (!reaction.getName().contains("G" + optimalGene + "m")) {
                    target++;
                    reaction = rList.get(target);
                }
                optimalReaction = reaction.toString();
                System.out.println("OPTIMAL REACTION: " + reaction.toString());
            }
            
            System.out.println("chi2 is: " + chi2best);
            chi2Values.add(chi2best);
            return (chi2best);
        }
        
        public String getOptimalReaction() {
            return optimalReaction;
        }
        
        public double getOptimalchi2() {
            return optimalchi2;
        }
        
        public double sumTerms(double[] SSValues, double [] InhActSum) {
            Collection<Reaction> temp = currentSimModel.getReactions();
            ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
            int inhActCounter = 0;
            
            double totalSum = 0;
            double numTerms = 0;
            for (int j = 0; j < rList.size(); j++) {
                Reaction reaction = rList.get(j);
                if (reaction.getName().contains("m")) {
                    Value foo = new Value(reaction.getRate());
                    Expression exprate = foo.getMExpressionValue();
                    
                    List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                    ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                    
                    for (int i = 0; i < expList.size(); i++) {
                        
                        if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                            if ((i + 6) < expList.size() && (i - 2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                                double vmax = exprate.convertTokenToElement(expList.get(i)).mNumericValue;
                                String geneName = (exprate.convertTokenToElement(expList.get(i+5)).mSymbol).getName();
                                double gene = SSValues[getReactionNumString(geneName)];
                                double hill = exprate.convertTokenToElement(expList.get(i+8)).mNumericValue;
                                double k = exprate.convertTokenToElement(expList.get(i+23)).mNumericValue;
                                double term = vmax*(Math.pow(gene,hill) / (Math.pow(k,hill) + Math.pow(gene,hill))) - 1;
                                totalSum += Math.pow(term,2);
                                InhActSum[inhActCounter] += Math.pow(term,2);
                                inhActCounter++;
                                numTerms++;
//                              System.out.println("Activator term: " + " vmax " + vmax + " gene " + gene + " hill " + hill + " k " + k + " total sum " + Math.pow(term,2));
                            }
                            //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                            else if ((i + 8) < expList.size() && (i - 2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                                double vmax = exprate.convertTokenToElement(expList.get(i)).mNumericValue;
                                String geneName = (exprate.convertTokenToElement(expList.get(i+16)).mSymbol).getName();
                                double gene = SSValues[getReactionNumString(geneName)];;
                                double hill = exprate.convertTokenToElement(expList.get(i+10)).mNumericValue;
                                double k = exprate.convertTokenToElement(expList.get(i+6)).mNumericValue;
                                double term = vmax*(Math.pow(k,hill) / (Math.pow(k,hill) + Math.pow(gene,hill))) - 1;
                                totalSum += Math.pow(term,2);
                                InhActSum[inhActCounter] += Math.pow(term,2);
                                inhActCounter++;
                                numTerms++;
//                              System.out.println("Inhibitor term: " + " vmax " + vmax + " gene " + gene + " hill " + hill + " k " + k + " total sum " + Math.pow(term,2));
                                
                            }
                        }
                    }
                }
            }
//          System.out.println("************** " +  (totalSum/numTerms)     + " ****************");
            return (totalSum/numTerms);
        }
        
        public void removeTerms(double [] InhActSum) {
            Collection<Reaction> temp = currentSimModel.getReactions();
            ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
            int inhActCounter = 0;
            String[] perturbedExp = new String[rList.size()];
            double numTerms = 0;
            for (int j = 0; j < rList.size(); j++) {
                for (int i = 0; i < 20; i++) {
                    perturbedExp[j] = "-1";
                }
                
                Reaction reaction = rList.get(j);
                if (reaction.getName().contains("m")) {
                    Value foo = new Value(reaction.getRate());
                    Expression exprate = foo.getMExpressionValue();
                    
                    List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                    ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                    perturbedExp[j] = new String();
                    for (int i = 0; i < expList.size(); i++) {
                        
                        if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                            if ((i + 6) < expList.size() && (i - 2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                                if (InhActSum[inhActCounter] < 50) {
                                    System.out.println("removing activator");
                                    perturbedExp[j] = perturbedExp[j] + "1.0)"; //"5)*((g4^(7))/((g4^(5))+((8)^(7  )))))";
                                    i += 31;
                                } else{
                                    perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                                }
                                
                                inhActCounter++;
                                numTerms++;
                            }
                            //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                            else if ((i + 8) < expList.size() && (i - 2) >= 0 &&
                                    expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                    expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                    expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                    expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                    expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                                
                                if (InhActSum[inhActCounter] < 50) {
                                    System.out.println("removing inhibitor");
                                    perturbedExp[j] = perturbedExp[j] + "1.0)"; // "5)*(((6)^(6))/((g5^(5))+((5)^(5  ))))";
                                    i += 33;
                                } else{
                                    perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                                }
                                inhActCounter++;
                                numTerms++;
                            } else {
                                perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                            }
                        } else  {
                            perturbedExp[j] = constructExp(perturbedExp[j], expList, i, exprate);
                        }
                    }
                    System.out.println("Final Equation: " + perturbedExp[j]);
                    try {
                        exprate.setExpression(perturbedExp[j]);
                    } catch (Exception d) {
                        d.printStackTrace();
                    }
                    
                    reaction.setRate(exprate);
                    currentSimModel.setReaction(reaction.getName(), reaction);
                }
            }
            
            try {
                ModelExporterMarkupLanguage sbmlfile = new ModelExporterMarkupLanguage();
                File writeFile = new File("C:/Hooman/REDUNDANT_GONE.xml");
                FileOutputStream os = new FileOutputStream(writeFile);
                PrintWriter writer = new PrintWriter(os);
                sbmlfile.export(currentSimModel, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        public String fwi(int n, int w)
        // converts an int to a string with given width.
        {
            String s = Integer.toString(n);
            while (s.length() < w)
                s = " " + s;
            return s;
        }
        
        
        public String fwd(double x, int w, int d)
        // converts a double to a string with given width and decimals.
        {
            java.text.DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(d);
            df.setMinimumFractionDigits(d);
            df.setGroupingUsed(false);
            String s = df.format(x);
            while (s.length() < w)
                s = " " + s;
            if (s.length() > w) {
                s = "";
                for (int i = 0; i < w; i++)
                    s = s + "-";
            }
            return s;
        }
    }
    
    public void main(String[] args) {
        
        if (args != null && args.length > 0) {
            new SimulationMain().runSimulations(args);
        } else {
            System.out.println("Illegal arguments");
        }
    }
    
    private void jbInit() throws Exception {
    }
    
    public double[] detectParamX(ArrayList<Expression.Token> expList, int i, Expression exprate, double[] paramVals) {
        
        //Hill coeff and K for activator
        if ( (i+8) < expList.size() &&
                expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 2).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i + 3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i + 4).mCode.equals(expList.get(i).mCode.MULT) &&
                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 7).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
            double[] foo = {exprate.convertTokenToElement(expList.get(i + 2)).mNumericValue,
            exprate.convertTokenToElement(expList.get(i + 25)).mNumericValue,
            exprate.convertTokenToElement(expList.get(i + 10)).mNumericValue};
            paramVals[i+2] = foo[0];
            paramVals[i+25] = foo[1];
            paramVals[i+10] = foo[2];
//        System.out.println("activator " + foo[0] + " " + foo[1]);
            return foo;
        }
        //Hill coeff and K for inhibitor
        if ( (i+10) < expList.size() &&
                expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 2).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i + 3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i + 4).mCode.equals(expList.get(i).mCode.MULT) &&
                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 6).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 7).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i + 8).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i + 9).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i + 10).mCode.equals(expList.get(i).mCode.POW)) {
            double[] foo = {exprate.convertTokenToElement(expList.get(i + 2)).mNumericValue,
            exprate.convertTokenToElement(expList.get(i + 8)).mNumericValue,
            exprate.convertTokenToElement(expList.get(i + 12)).mNumericValue};
            paramVals[i+2] = foo[0];
            paramVals[i+8] = foo[1];
            paramVals[i+12] = foo[2];
//        System.out.println("inhibitor " + foo[0] + " " + foo[1]);
            return foo;
        }
 /*   //vmax
    if ( (i+4) < expList.size() && i == 0 &&
        expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
        expList.get(i+1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
        expList.get(i+2).mCode.equals(expList.get(i).mCode.NUMBER) &&
        expList.get(i+3).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
        expList.get(i+4).mCode.equals(expList.get(i).mCode.MULT)) {
        double[] foo = {exprate.convertTokenToElement(expList.get(i + 2)).mNumericValue};
        paramVals[i+2] = foo[0];
//        System.out.println("vmax " + foo[0]);
        return foo;
    }
  */
        //degrate
        if ( (i+7) < expList.size() &&
                expList.get(i).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i+1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i+2).mCode.equals(expList.get(i).mCode.MINUS) &&
                expList.get(i+3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                expList.get(i+4).mCode.equals(expList.get(i).mCode.NUMBER) &&
                expList.get(i+5).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i+6).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                expList.get(i+7).mCode.equals(expList.get(i).mCode.MULT)){
            double[] foo = {exprate.convertTokenToElement(expList.get(i + 4)).mNumericValue};
            paramVals[i+4] = foo[0];
//        System.out.println("deg " + foo[0]);
            return foo;
        }
        double[] foo = {-1};
        return foo;
    }
    
    public double[][] getParamsX() {
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        
        double[][] paramVals = new double[rList.size()][];
        for (int i = 0; i < rList.size(); i++) {
            Reaction reaction = rList.get(i);
            if (reaction.getName().contains("m")) {
                Value foo = new Value(reaction.getRate());
                Expression exprate = foo.getMExpressionValue();
                
                List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                
                paramVals[i] = new double[expList.size()];
                for (int j = 0; j < expList.size(); j++) {
                    paramVals[i][j] = -1;
                }
//             System.out.println("reaction: " + reaction.getName());
                for (int j = 0; j < expList.size(); j++) {
                    detectParam(expList, j, exprate, paramVals[i]);
                    //if (expList.get(j).mCode.equals(expList.get(j).mCode.NUMBER)) {
                    //    paramVals[i][j] = exprate.convertTokenToElement(expList.get(j)).mNumericValue;
                    //}
                }
            } else {
                paramVals[i] = new double[1];
                paramVals[i][0] = -1;
            }
        }
        return paramVals;
    }
    public void setParamsX(double[] paramList) {
        
        Collection<Reaction> temp = currentSimModel.getReactions();
        ArrayList<Reaction> rList = new ArrayList<Reaction>(temp);
        int paramCount = 0;
        
        //MAY HAVE TO BE REMOVED!!!
//     for (int i = 0; i < paramList.length; i++) {
//         System.out.print(paramList[i] + " ");
//         paramList[i] = Math.abs(paramList[i]);
//         if (paramList[i] > 20)
//             paramList[i] = 20;
//         if (paramList[i] < 0.01)
//             paramList[i] = 0.01;
//     }
        
        for (int j = 0; j < rList.size(); j++) {
            String perturbedExp = new String();
            Reaction reaction = rList.get(j);
            if (reaction.getName().contains("m")) {
                Value foo = new Value(reaction.getRate());
                Expression exprate = foo.getMExpressionValue();
                
                List<Expression.Token> temp2 = exprate.tokenizeExpression(exprate.toString());
                ArrayList<Expression.Token> expList = new ArrayList<Expression.Token>(temp2);
                
                for (int i = 0; i < expList.size(); i++) {
                    
                    if (expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER)) {
                        if ( (i+6) < expList.size() && (i-2) >= 0 &&
                                expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.SYMBOL) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.POW)) {
                            
                            perturbedExp = perturbedExp + paramList[paramCount] + ")*((" + exprate.convertTokenToElement(expList.get(i + 5)).toString() +
                                    "^(" + paramList[paramCount + 1] + "))/((" + exprate.convertTokenToElement(expList.get(i + 14)).toString() +
                                    "^(" + paramList[paramCount + 1] + "))+((" + paramList[paramCount + 2] + ")^(" + paramList[paramCount + 1];
                            paramCount += 3;
                            i += 27;
                        }
                        //inhibitor (((0.01)^(1.5))/((G1p^(1.5))+((0.01)^(1.5))))
                        else if ( (i+8) < expList.size() && (i-2) >= 0 &&
                                expList.get(i - 2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i - 1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 2).mCode.equals(expList.get(i).mCode.MULT) &&
                                expList.get(i + 3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 5).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i + 6).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i + 7).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i + 8).mCode.equals(expList.get(i).mCode.POW)) {
                            perturbedExp = perturbedExp + paramList[paramCount] + ")*(((" + paramList[paramCount+1] + ")^(" + paramList[paramCount + 2] + "))/((" + exprate.convertTokenToElement(expList.get(i + 16)) +
                                    "^(" + paramList[paramCount + 2] + "))+((" + paramList[paramCount+1] + ")^(" + paramList[paramCount + 2];
                            paramCount += 3;
                            i += 29;
                        }
               /*      //vmax
                     else if ( (i+2) < expList.size() && (i-2) == 0 &&
                          expList.get(i-2).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                          expList.get(i-1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                          expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                          expList.get(i+1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                          expList.get(i+2).mCode.equals(expList.get(i).mCode.MULT)) {
                         perturbedExp = perturbedExp + paramList[paramCount];
                         paramCount++;
                     }*/
                        //degrate
                        else if ( (i+3) < expList.size() && (i-4) >= 0 &&
                                expList.get(i-4).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i-3).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i-2).mCode.equals(expList.get(i).mCode.MINUS) &&
                                expList.get(i-1).mCode.equals(expList.get(i).mCode.OPEN_PAREN) &&
                                expList.get(i).mCode.equals(expList.get(i).mCode.NUMBER) &&
                                expList.get(i+1).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i+2).mCode.equals(expList.get(i).mCode.CLOSE_PAREN) &&
                                expList.get(i+3).mCode.equals(expList.get(i).mCode.MULT)){
                            perturbedExp = perturbedExp + paramList[paramCount];
                            paramCount++;
                        } else {
                            perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                        }
                        
                    } else {
                        perturbedExp = constructExp(perturbedExp, expList, i, exprate);
                    }
                }
                //System.out.println("*** " + reaction.getName() + " *** "  + perturbedExp);
                try{exprate.setExpression(perturbedExp);} catch (Exception d) {d.printStackTrace();}
                reaction.setRate(exprate);
                currentSimModel.setReaction(reaction.getName(), reaction);
            }
        }
    }
    
    
    
}
