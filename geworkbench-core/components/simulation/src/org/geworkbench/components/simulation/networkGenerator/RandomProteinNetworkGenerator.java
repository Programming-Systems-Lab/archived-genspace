package org.geworkbench.components.simulation.networkGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.geworkbench.components.simulation.Reaction.ElementMath;
import org.geworkbench.components.simulation.Reaction.MendesEqn;
import org.systemsbiology.chem.Compartment;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.Reaction;
import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Symbol;

public class RandomProteinNetworkGenerator implements INetworkGenerator{
    int numKinase;
    int numKinaseCons;
    int numGenes;
    int numConnections;
    double mrnaDegRate;
    double proDegRate;
    double activeproDegRate;
    double degradationRate;
    int[] inputs;
    int[] outputs;
    double K;
    double fanInPercent;
    double actPercent;
    
    int metrop;
    //if you want to generate random mendes networks containing two random act/inh
    //per node, then you make completelyRandom = 0, however, if you want a completely
    //random arrangement of nodes and edges then set it to 1
    int completelyRandom = 1;
    Species nullGene;
    Compartment c;
    public RandomProteinNetworkGenerator() {
    }

    /**
     * generateNetwork
     *
     * @return Model
     */
    public Model generateNetwork() {
        Model m = new Model();
        m.setName("My Model");

//*
        Species[] mrna = new Species[numGenes];
        Species[] protein = new Species[numGenes];
        Species[] activepro = new Species[numGenes];
        generateGenes2(mrna, protein, activepro, m);

        //PROTEINS
        generateProReaction(mrna, protein, m);
        generateActiveProReaction(protein, activepro, m);
        generateLoopySFN2(mrna, protein, activepro, m);
  //*/
  //      Species[] genes = generateGenes(m);
//         generateLoopySFN(mrna, m);

        //generateReactions(genes, m, completelyRandom);
        //StolovitzkyModel foo = new StolovitzkyModel();//temporary
        //m = foo.runModel();//temporary
      //  System.out.println(m.toString());
        return m;
    }

    /**
     * initialize
     *
     * @param propsMap HashMap
     */
    public void initialize(HashMap propsMap) {
        this.numKinase = 1;
        this.numKinaseCons = 2;
        this.numGenes = 4;
        this.numConnections = 8; //must be even number
        this.degradationRate = -1.0;
        this.mrnaDegRate = -Math.random()*2;
        this.proDegRate = -1.0;
        this.activeproDegRate = -1.0;
        this.metrop = 0;

    }

    public void initialize(HashMap propsMap, int one, int two, int three, int four, double five, double six, double seven, double eight, int[] inputs, int[] outputs, double K, double fanInPercent, double actPercent) {
        this.numKinase = one;
        this.numKinaseCons = two;
        this.numGenes = three;
        this.numConnections = four; //must be even number
        this.degradationRate = five;
        this.mrnaDegRate = six;
        this.proDegRate = seven;
        this.activeproDegRate = eight;
        this.inputs = inputs;
        this.outputs = outputs;
        this.K = K;
        this.fanInPercent = fanInPercent;
        this.actPercent = actPercent;
    }


    void generateProReaction(Species[] mrna, Species[] protein, Model m) {
        MendesEqn[] mendesReactions = new MendesEqn[numGenes];
        for(int i = 0; i < numGenes; i++){
            mendesReactions[i] = new MendesEqn();
        }

        for (int i = 0; i < numGenes; i++) {
            Species activator = mrna[i];
            mendesReactions[i].addActivator(activator);
        }

        Reaction[] reactions = new Reaction[numGenes];
        for(int i = 0; i < numGenes; i++){
            reactions[i] = new Reaction(protein[i].getName() + "_synthesis");
            reactions[i].addReactant(nullGene, 1);
            reactions[i].addProduct(protein[i], 1);
            reactions[i].setRate(mendesReactions[i].getRate2(proDegRate, protein[i]));

            m.addReaction(reactions[i]);
        }
    }

    void generateActiveProReaction(Species[] protein, Species[] activepro, Model m) {
        int[] kinIndex = new int[numKinase];
        MendesEqn[] mendesReactions = new MendesEqn[numGenes];

        for (int i = 0; i < numGenes; i++){
            mendesReactions[i] = new MendesEqn();
        }

        for (int i = 0; i < numKinase; i++) {
            kinIndex[i] = (int) (Math.random() * numGenes);
            //System.out.println("KINASE IS: " + kinIndex[i]);
            for(int j = 0; j < numKinaseCons; j++) {
                int targetpro = (int) (Math.random() * numGenes);

                Species kinase;
                if(mendesReactions[kinIndex[i]].hasKinase()) {
                    kinase = activepro[kinIndex[i]];
                }
                else {
                    kinase = protein[kinIndex[i]];
                }

                while(mendesReactions[targetpro].hasKinase()) {
                    targetpro = (int) (Math.random() * numGenes);
                }

                //System.out.println("KINASE TARGET IS: " + targetpro);
                activepro[targetpro] = new Species("G" + targetpro + "ap", c);
                activepro[targetpro].setSpeciesPopulation(1.0);
                m.addSpecies(activepro[targetpro]);
                mendesReactions[targetpro].addKinase(kinase);
                Species activator = protein[targetpro];
                mendesReactions[targetpro].addActivator(activator);
                Reaction reaction = new Reaction(activepro[targetpro].getName() + "_synthesis");
                reaction.addReactant(nullGene, 1);
                reaction.addProduct(activepro[targetpro], 1);
                reaction.setRate(mendesReactions[targetpro].getRate3(activeproDegRate, activepro[targetpro]));
                m.addReaction(reaction);
            }
        }
    }
    
    double calculateFanInPercent(int[] fanIn, int[] fanOut) {
        double total = 0;
        for (int i = 0; i < numGenes; i++) {
            if ((fanIn[i] + fanOut[i]) != 0) {
//                System.out.println("fanIn%: " + (fanIn[i] / (fanIn[i] + fanOut[i])) + " total:" + total);
                total = total + ((double)fanIn[i] / ((double)fanIn[i] + (double)fanOut[i]));
            }
        }
        return (total/numGenes);
    }
    
    boolean gene1FanOutIsBetter(int gene1, int gene2, int[] fanIn, int[] fanOut) {
        
        int[] tempFanIn1 = new int[numGenes];
        int[] tempFanOut1 = new int[numGenes];
        int[] tempFanIn2 = new int[numGenes];
        int[] tempFanOut2 = new int[numGenes];
        
        //out of gene1 into gene2
        for (int i = 0; i < numGenes; i++) {        
            if (i == gene1) {
                tempFanOut1[i] = fanOut[i] + 1;
            }
            else {
                tempFanOut1[i] = fanOut[i];
            }
            if (i == gene2) {
                tempFanIn1[i] = fanIn[i] + 1;
            }
            else {
                tempFanIn1[i] = fanIn[i];
            }
        }
        
        //out of gene2 into gene1
        for (int i = 0; i < numGenes; i++) {        
            if (i == gene2) {
                tempFanOut2[i] = fanOut[i] + 1;
            }
            else {
                tempFanOut2[i] = fanOut[i];
            }
            if (i == gene1) {
                tempFanIn2[i] = fanIn[i] + 1;
            }
            else {
                tempFanIn2[i] = fanIn[i];
            }
        }
        
        double gene1Diff = Math.abs(calculateFanInPercent(tempFanIn1, tempFanOut1)-fanInPercent);
        double gene2Diff = Math.abs(calculateFanInPercent(tempFanIn2, tempFanOut2)-fanInPercent);
        
        if (gene1Diff < gene2Diff) {
            return true;
        }
        else {
            return false;
        }
    }
    
    void generateLoopySFN2(Species[] mrna, Species[] protein, Species[] activepro, Model m) {
        MendesEqn[] mendesReactions = new MendesEqn[numGenes];
        int[] conProb = new int[(numConnections*2)];
        int[] numCons = new int[numGenes];
        int[] fanIn = new int[numGenes];
        int[] fanOut = new int[numGenes];
        
        for(int i = 0; i < numGenes; i++){
            fanIn[i] = 0;
            fanOut[i] = 0;
            mendesReactions[i] = new MendesEqn();
        }

        int gene1 = (int) (Math.random() * numGenes);
        int gene2 = (int) (Math.random() * numGenes);
        int limit = 0;

        for (int i = 0; i < numConnections; i++) {
            if (Math.random() > actPercent) { //activation reaction
                if(gene1FanOutIsBetter(gene1, gene2, fanIn, fanOut)) { //gene1 is activator
                    Species activator = activepro[gene1];
                    if (activator == null) {
                        if (Math.random() < 0.2){
                            gene1 = 0;
                        }
                        activator = protein[gene1];
                    }
                    mendesReactions[gene2].addActivator(activator);
                    fanIn[gene2]++;
                    fanOut[gene1]++;
                }
                else {
                    Species activator = activepro[gene2];
                    if (activator == null) {
                        activator = protein[gene2];
                    }
                    mendesReactions[gene1].addActivator(activator);
                    fanIn[gene1]++;
                    fanOut[gene2]++;
                }
            }
            else { //inhibition reaction
                if(gene1FanOutIsBetter(gene1, gene2, fanIn, fanOut)) { //gene1 is inhibitor
                    Species inhibitor = activepro[gene1];
                    if (inhibitor == null) {
                        if (Math.random() < 0.2){
                            gene1 = 0;
                        }
                        inhibitor = protein[gene1];
                    }
                    mendesReactions[gene2].addInhibitor(inhibitor);
                    fanIn[gene2]++;
                    fanOut[gene1]++;
                }
                else {
                    Species inhibitor = activepro[gene2];
                    if (inhibitor == null) {
                        inhibitor = protein[gene2];
                    }
                    mendesReactions[gene1].addInhibitor(inhibitor);
                    fanIn[gene1]++;
                    fanOut[gene2]++;
                }
            }
            conProb[limit++] = gene1;
            conProb[limit++] = gene2;
            gene1 = (int) (Math.random() * numGenes);
            gene2 = conProb[(int) (Math.random() * limit)];
            numCons[gene1]++;
            numCons[gene2]++;
        }

        for(int i = 0; i < numGenes; i++) {
            //System.out.println(numCons[i]);
        }

        Reaction[] reactions = new Reaction[numGenes];
        for(int i = 0; i < numGenes; i++){
            reactions[i] = new Reaction(mrna[i].getName() + "_synthesis");
            reactions[i].addReactant(nullGene, 1);
            reactions[i].addProduct(mrna[i], 1);
            reactions[i].setRate(mendesReactions[i].getRate(mrnaDegRate, mrna[i]));

            m.addReaction(reactions[i]);
        }
    }


    void generateLoopySFN2original(Species[] mrna, Species[] protein, Species[] activepro, Model m) {
        MendesEqn[] mendesReactions = new MendesEqn[numGenes];
        int[] conProb = new int[(numConnections*2)];
        int[] numCons = new int[numGenes];

        for(int i = 0; i < numGenes; i++){
            mendesReactions[i] = new MendesEqn();
        }

        int gene1 = (int) (Math.random() * numGenes);
        int gene2 = (int) (Math.random() * numGenes);
        int limit = 0;

        for (int i = 0; i < numConnections; i++) {
            if (Math.random() > 0.5) { //activation reaction
                if(Math.random() > 0.5) { //gene1 is activator
                    Species activator = activepro[gene1];
                    if (activator == null) {
                        if (Math.random() < 0.2){
                            gene1 = 0;
                        }
                        activator = protein[gene1];
                    }
                    mendesReactions[gene2].addActivator(activator);
                }
                else {
                    Species activator = activepro[gene2];
                    if (activator == null) {
                        activator = protein[gene2];
                    }
                    mendesReactions[gene1].addActivator(activator);
                }
            }
            else { //inhibition reaction
                if(Math.random() > 0.5) { //gene1 is inhibitor
                    Species inhibitor = activepro[gene1];
                    if (inhibitor == null) {
                        if (Math.random() < 0.2){
                            gene1 = 0;
                        }
                        inhibitor = protein[gene1];
                    }
                    mendesReactions[gene2].addInhibitor(inhibitor);
                }
                else {
                    Species inhibitor = activepro[gene2];
                    if (inhibitor == null) {
                        inhibitor = protein[gene2];
                    }
                    mendesReactions[gene1].addInhibitor(inhibitor);
                }
            }
            conProb[limit++] = gene1;
            conProb[limit++] = gene2;
            gene1 = (int) (Math.random() * numGenes);
            gene2 = conProb[(int) (Math.random() * limit)];
            numCons[gene1]++;
            numCons[gene2]++;
        }

        for(int i = 0; i < numGenes; i++) {
            //System.out.println(numCons[i]);
        }

        Reaction[] reactions = new Reaction[numGenes];
        for(int i = 0; i < numGenes; i++){
            reactions[i] = new Reaction(mrna[i].getName() + "_synthesis");
            reactions[i].addReactant(nullGene, 1);
            reactions[i].addProduct(mrna[i], 1);
            reactions[i].setRate(mendesReactions[i].getRate(mrnaDegRate, mrna[i]));

            m.addReaction(reactions[i]);
        }
    }

    void generateLoopySFN(Species[] genes, Model m) {
        MendesEqn[] mendesReactions = new MendesEqn[genes.length];
        int[] conProb = new int[(numConnections*2)];
        int[] numCons = new int[genes.length];

        for(int i = 0; i < genes.length; i++){
            mendesReactions[i] = new MendesEqn();
        }
        int gene1 = (int) (Math.random() * numGenes);
        int gene2 = (int) (Math.random() * numGenes);
        int limit = 0;
        for (int i = 0; i < numConnections; i++) {
            if (Math.random() > 0.5) { //activation reaction
                if(Math.random() > 0.5) { //gene1 is activator
                    Species activator = genes[gene1];
                    mendesReactions[gene2].addActivator(activator);
                }
                else {
                    Species activator = genes[gene2];
                    mendesReactions[gene1].addActivator(activator);
                }
            }
            else { //inhibition reaction
                if(Math.random() > 0.5) { //gene1 is inhibitor
                    Species inhibitor = genes[gene1];
                    mendesReactions[gene2].addInhibitor(inhibitor);
                }
                else {
                    Species inhibitor = genes[gene1];
                    mendesReactions[gene2].addInhibitor(inhibitor);
                }
            }
            conProb[limit++] = gene1;
            conProb[limit++] = gene2;
            gene1 = (int) (Math.random() * numGenes);
            gene2 = conProb[(int) (Math.random() * limit)];
            numCons[gene1]++;
            numCons[gene2]++;
        }

        for(int i = 0; i < genes.length; i++) {
            //System.out.println(numCons[i]);
        }
        Reaction[] reactions = new Reaction[genes.length];
        for(int i = 0; i < genes.length; i++){
            reactions[i] = new Reaction(genes[i].getName() + "_synthesis");
            reactions[i].addReactant(nullGene, 1);
            reactions[i].addProduct(genes[i], 1);
            reactions[i].setRate(mendesReactions[i].getRate(degradationRate, genes[i]));

            m.addReaction(reactions[i]);
        }
    }

    void generateReactions(Species[] genes, Model m, int completelyRandom){
        //MendesReaction[] mendesReactions = new MendesReaction[genes.length];
        MendesEqn[] mendesReactions = new MendesEqn[genes.length];

        for(int i = 0; i < genes.length; i++){
            mendesReactions[i] = new MendesEqn();
        }
        //mendes random (each node as 2 act/inh)
        if (completelyRandom == 0) {
            int targetIndex = -1;
            for (int reactionCtr = 0; reactionCtr < (numConnections / 2); reactionCtr++) {
                targetIndex = targetIndex + 1;
                for (int i = 0; i < 2; i++) {
                    int actIndex = (int) (Math.random() * numGenes);
                    int inhIndex = (int) (Math.random() * numGenes);
                    while (actIndex == inhIndex) {
                        actIndex = (int) (Math.random() * numGenes);
                        inhIndex = (int) (Math.random() * numGenes);
                    }
                    Species activator = genes[actIndex];
                    Species inhibitor = genes[inhIndex];

                    if (Math.random() < 0.5) {
                        mendesReactions[targetIndex].addActivator(activator);
                    }
                    else {
                        mendesReactions[targetIndex].addInhibitor(inhibitor);
                    }
                }
            }
        }
        //completely random (any number of activators inhibitors)
        else {
            for (int reactionCtr = 0; reactionCtr < (numConnections);
                 reactionCtr++) {
                double ActOrInh = Math.random();
                if (ActOrInh > 0.5) {
                    int targetIndex = (int) (Math.random() * numGenes);
                    int actIndex = (int) (Math.random() * numGenes);
                    Species activator = genes[actIndex];
                    mendesReactions[targetIndex].addActivator(activator);
                }
                else {
                    int targetIndex = (int) (Math.random() * numGenes);
                    int inhIndex = (int) (Math.random() * numGenes);
                    Species inhibitor = genes[inhIndex];
                    mendesReactions[targetIndex].addInhibitor(inhibitor);
                }
            }
        }

        Reaction[] reactions = new Reaction[genes.length];
        for(int i = 0; i < genes.length; i++){
            reactions[i] = new Reaction(genes[i].getName() + "_synthesis");
            reactions[i].addReactant(nullGene, 1);
            reactions[i].addProduct(genes[i], 1);
            reactions[i].setRate(mendesReactions[i].getRate(degradationRate, genes[i]));

            m.addReaction(reactions[i]);
        }
    }

    Species[] generateGenes(Model m){
        Compartment c = new Compartment("cell");
        nullGene = new Species("_void_", c);
        nullGene.setSpeciesPopulation(1.0);
        m.addSpecies(nullGene);
        Species[] genes = new Species[numGenes];
        for(int i = 0; i < numGenes; i++){
            Species gene = new Species("G" + i, c);
            gene.setSpeciesPopulation(1.0);
            m.addSpecies(gene);
            genes[i] = gene;
        }
        return genes;
    }

    void generateGenes2(Species[] mrna, Species[] protein, Species[] activepro, Model m){
        c = new Compartment("cell");
        nullGene = new Species("_void_", c);
        nullGene.setSpeciesPopulation(1.0);
        m.addSpecies(nullGene);
        System.out.println("GENERATING GENES");
        for(int i = 0; i < numGenes; i++){
            Species mrna_node = new Species("G" + i + "m", c);
            mrna_node.setSpeciesPopulation(1.0);
            m.addSpecies(mrna_node);
            mrna[i] = mrna_node;
        }
//PROTEINS
        for(int i = 0; i < numGenes; i++){
            Species protein_node = new Species("G" + i + "p", c);
            protein_node.setSpeciesPopulation(1.0);
            m.addSpecies(protein_node);
            protein[i] = protein_node;
        }

        for(int i = 0; i < numGenes; i++){
            activepro[i] = null;
        }

        //changeMrnaConc(1, m);
    }

    void generateGenes3(Species[] mrna, Species[] protein, Species[] activepro, Model m){
    c = new Compartment("cell");
    nullGene = new Species("_void_", c);
    nullGene.setSpeciesPopulation(1.0);
    m.addSpecies(nullGene);

    double outputconc1 = Math.random() * 1.0;
    double outputconc2 = K * outputconc1;
   // System.out.println("output conc: " + outputconc1 + " " + outputconc2);
    int mConstraint1 = outputs[outputs.length - 2];
    int mConstraint2 = outputs[outputs.length - 1];

    mConstraint1 = -1;
    mConstraint2 = -1;

    //System.out.println("mconstraint: " + mConstraint1 + " " + mConstraint2);

    for(int i = 0; i < numGenes; i++){
        Species mrna_node = new Species("G" + i + "m", c);

        if (i == mConstraint1) {
           mrna_node.setSpeciesPopulation(outputconc1);
        }
        else if (i == mConstraint2) {
            mrna_node.setSpeciesPopulation(outputconc2);
        }
        else {
            mrna_node.setSpeciesPopulation(1.0);
        }

        m.addSpecies(mrna_node);
        mrna[i] = mrna_node;
    }

    for(int i = 0; i < numGenes; i++){
        Species protein_node = new Species("G" + i + "p", c);
        protein_node.setSpeciesPopulation(1.0);
        m.addSpecies(protein_node);
        protein[i] = protein_node;
    }

    for(int i = 0; i < numGenes; i++){
        activepro[i] = null;
    }

    //System.out.println("CREATED GENES");
}

public void changeMrnaConc(int index, Model m) {
    try{
    ElementMath e = new ElementMath();
    Collection<Symbol> temp = m.getSymbols();
    ArrayList<Symbol> sList = new ArrayList<Symbol> (temp);
    int actualIndex = -1;
    Symbol symbol;
    for (int i = 0; i < sList.size(); i++) {
        symbol = sList.get(i);
        if (symbol.getName().contains(index + "m")) {
            actualIndex = i;
            System.out.println(symbol.toString());
        }
    }

    }catch(Exception e) {
        e.printStackTrace();
    }
}
}