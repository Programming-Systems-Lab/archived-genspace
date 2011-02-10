package org.geworkbench.components.simulation.util;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.geworkbench.components.simulation.networkGenerator.SbmlNetworkGenerator;
import org.geworkbench.components.simulation.networkGenesProcessor.NetworkGenesProcessor;
import org.geworkbench.util.pathwaydecoder.mutualinformation.AdjacencyMatrix;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.Reaction;
import org.systemsbiology.chem.Reaction.ReactionElement;
import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression;
import org.systemsbiology.math.Value;

public class ExtractAdjMatrix {
    AdjacencyMatrix adjMatrix;

    public ExtractAdjMatrix() {
    }

    public static void main(String[] args){
        new ExtractAdjMatrix().extractMatrix(args);
    }

    void extractMatrix(String[] args){
        File sbmlFile = new File(args[0]);
        File writeFile = new File(args[1]);
        writeAdjMatrix(sbmlFile, writeFile, "_void_");
    }

    void writeDefaultFiles(){
        File sbmlFile = new File("Z:/Simulations/Params/SimParamsTest/CenturySF-001.xml");
        File writeFile = new File("Z:/Simulations/Params/SimParamsTest/SF-001.adj");
        writeAdjMatrix(sbmlFile, writeFile, "_void_");
    }

    public void writeAdjMatrix(File sbmlFile, File writeFile){
        SbmlNetworkGenerator networkGenerator = new SbmlNetworkGenerator();
        Model model = networkGenerator.createModelFromSbmlFile(sbmlFile);

        HashMap geneMap = createGeneMap(model.getOrderedSpeciesNamesArray());
        HashMap geneIdMap = createGeneIdMap(model.getOrderedSpeciesNamesArray());
        extractAdjMatrix(model, geneMap);

        adjMatrix.print(geneIdMap, writeFile);

    }

    public void writeAdjMatrix(File sbmlFile, File writeFile, String[] geneNames){
        SbmlNetworkGenerator networkGenerator = new SbmlNetworkGenerator();
        Model model = networkGenerator.createModelFromSbmlFile(sbmlFile);

        HashMap geneMap = createGeneMap(geneNames);
        HashMap geneIdMap = createGeneIdMap(geneNames);
        extractAdjMatrix(model, geneMap);

        adjMatrix.print(geneIdMap, writeFile);
    }

    public void writeAdjMatrix(File sbmlFile, File writeFile, String excludeName){
        SbmlNetworkGenerator networkGenerator = new SbmlNetworkGenerator();
        Model model = networkGenerator.createModelFromSbmlFile(sbmlFile);

        String[] origGeneNames = model.getOrderedSpeciesNamesArray();
//        String[] origGeneNames = model.getOrderedSpeciesNamesArray(new NumberedGeneNameComparator());

        NetworkGenesProcessor processor = new NetworkGenesProcessor();
        String[] newGeneNames = processor.getGeneListExcluding(origGeneNames, excludeName);

        HashMap geneMap = createGeneMap(newGeneNames);
        HashMap geneIdMap = createGeneIdMap(newGeneNames);
        extractAdjMatrix(model, geneMap);

        adjMatrix.print(geneIdMap, writeFile);
    }




    public void extractAdjMatrix(File sbmlFile){
        SbmlNetworkGenerator networkGenerator = new SbmlNetworkGenerator();
        Model model = networkGenerator.generateNetwork();

        HashMap geneMap = createGeneMap(model.getOrderedSpeciesNamesArray());
        extractAdjMatrix(model, geneMap);
    }

    HashMap createGeneMap(String[] geneNames){
        HashMap geneMap = new HashMap();
        for(int geneCtr = 0; geneCtr < geneNames.length; geneCtr++){
            geneMap.put(geneNames[geneCtr], new Integer(geneCtr));
        }
        return geneMap;
    }

    HashMap createGeneIdMap(String[] geneNames){
        HashMap geneMap = new HashMap();
        for(int geneCtr = 0; geneCtr < geneNames.length; geneCtr++){
            geneMap.put(new Integer(geneCtr), geneNames[geneCtr]);
        }
        return geneMap;
    }


    void extractAdjMatrix(Model model, HashMap geneMap) {
        adjMatrix = new AdjacencyMatrix();
        for (int i = 0; i < geneMap.size(); i++) {
            adjMatrix.addGeneRow(i);
        }

        Collection reactions = model.getReactions();
        Iterator reactionIt = reactions.iterator();
        while (reactionIt.hasNext()) {
            Reaction reaction = (Reaction) reactionIt.next();

//            addReactantsAndProducts(reaction, geneMap);
            addRateDependencies(reaction, geneMap);

        }
    }

    void addRateDependencies(Reaction reaction, HashMap geneMap) {
        Value value = reaction.getValue();

        Expression ex = value.getMExpressionValue();
        String str = value.getExpressionString();
        java.util.List tokens = ex.tokenizeExpression(str);
        Iterator it = tokens.iterator();
        while (it.hasNext()) {
            Expression.Token t = (Expression.Token) it.next();
            if (t.mCode == Expression.TokenCode.SYMBOL) {
                String symbolName = t.mSymbolName;
                Object objSymbolIndex = geneMap.get(symbolName);
                if (objSymbolIndex != null) {
                    int symbolIndex = ( (Integer) objSymbolIndex).intValue();
                    addAllDependenciesForIndex(symbolIndex, reaction, geneMap);
                }
            }
        }
    }

    void addAllDependenciesForIndex(int index, Reaction reaction,
                                    HashMap geneMap) {
        Collection reactantElements = reaction.getReactantsMap().values();
        addAllDependenciesForIndex(index, reactantElements, geneMap);

        Collection productElements = reaction.getProductsMap().values();
        addAllDependenciesForIndex(index, productElements, geneMap);
    }

    void addAllDependenciesForIndex(int index, Collection elements,
                                    HashMap geneMap) {

        Iterator elementsIter = elements.iterator();
        while (elementsIter.hasNext()) {
            ReactionElement reactantElement = (ReactionElement) elementsIter.
                next();
            Species reactantSpecies = reactantElement.getSpecies();
            String reactantName = reactantSpecies.getName();

            Object objReactantIndex = geneMap.get(reactantName);
            if (objReactantIndex != null) {
                int elementIndex = ( (Integer) objReactantIndex).intValue();
                if(index != elementIndex){
                    addDependency(index, elementIndex);
                }
            }
        }

    }

    void addReactantsAndProducts(Reaction reaction, HashMap geneMap) {
        Collection reactantElements = reaction.getReactantsMap().values();
        Iterator reactantIter = reactantElements.iterator();
        while (reactantIter.hasNext()) {
            ReactionElement reactantElement = (ReactionElement) reactantIter.
                next();
            Species reactantSpecies = reactantElement.getSpecies();
            String reactantName = reactantSpecies.getName();

            Object objReactantIndex = geneMap.get(reactantName);
            if (objReactantIndex != null) {
                int reactantIndex = ( (Integer) objReactantIndex).intValue();
                Collection productElements = reaction.getProductsMap().values();
                Iterator productIter = productElements.iterator();
                while (productIter.hasNext()) {
                    ReactionElement productElement = (ReactionElement)
                        productIter.next();
                    Species productSpecies = productElement.getSpecies();
                    String productName = productSpecies.getName();
                    Object objProductIndex = geneMap.get(productName);
                    if (objProductIndex != null) {
                        int productIndex = ( (Integer) objProductIndex).
                            intValue();
                        if (reactantIndex != productIndex) {
                            addDependency(reactantIndex, productIndex);
                        }
                    }
                }
            }
        }
    }

    void addDependency(int id1, int id2) {
        adjMatrix.add(Math.min(id1, id2), Math.max(id1, id2), 0.05f);
    }

}
