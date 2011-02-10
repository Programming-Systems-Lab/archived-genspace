package org.geworkbench.components.simulation.util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.geworkbench.util.ArrayUtil;
import org.systemsbiology.chem.Compartment;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.Reaction;
import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression;
import org.systemsbiology.math.Value;


public class SimulationUtil {
  public SimulationUtil() {
  }

  /*
     public Species[] createSpeciesPumpers(int[] speciesIndices, Model model){
    return createSpeciesPumpers(speciesIndices, model, 0);
     }
   */

  public Species[] createSpeciesPumpers(int[] speciesIndices, Model model, double initialValue, double pumpStrength) {
    String[] symbolNames = model.getOrderedNonConstantSymbolNamesArray();
    Species[] speciesPumpers = new Species[speciesIndices.length];
    try {
      for (int speciesCtr = 0; speciesCtr < speciesIndices.length; speciesCtr++) {
        int speciesIndex = speciesIndices[speciesCtr];

        speciesPumpers[speciesCtr] = createSpeciesPumper(speciesIndex, model, initialValue, pumpStrength, symbolNames);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return speciesPumpers;
  }

  public Species createSpeciesPumper(int speciesIndex, Model model, double initialValue, double pumpStrength) {
    String[] symbolNames = model.getOrderedNonConstantSymbolNamesArray();
    return createSpeciesPumper(speciesIndex, model, initialValue, pumpStrength, symbolNames);
  }

  public Species createSpeciesPumper(int speciesIndex, Model model, double initialValue, double pumpStrength,
                                     String[] symbolNames) {
    try {
      String speciesToPumpName = symbolNames[speciesIndex];
      System.out.println("speciesToPump " + speciesToPumpName);
      Species speciesToPump = model.getSpeciesByName(speciesToPumpName);
      Compartment speciesCompartment = speciesToPump.getCompartment();

      Species speciesPumper = new Species(speciesToPumpName + "_pumper",
                                          speciesCompartment);
      //The pumps are initialized to zero until they are pumped up
      speciesPumper.setValue(new Value(initialValue));
      model.addSpecies(speciesPumper);

      Reaction pumperReaction = new Reaction(speciesToPump + "_pump");
      pumperReaction.addReactant(speciesPumper, 1, true);
      pumperReaction.addProduct(speciesPumper, 1, true);
      pumperReaction.addProduct(speciesToPump, 1, true);
      //Set the rate of the pump tp be some number times the amount of the pump
      //that is present
      pumperReaction.setRate(new Value(new Expression(pumpStrength +
          "*" + speciesPumper.getName())));
      model.addReaction(pumperReaction);
      return speciesPumper;
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }



  public void writeSimulationResultsToFile(String[] symbolNames, String[] orderedSymbolNames,
                                           double[] retTime,
                                           Object[] retSymbols, File writeFile) {
    int[] orderedSymbolMap = new int[symbolNames.length];
    for (int i = 0; i < symbolNames.length; i++) {
      orderedSymbolMap[i] = ArrayUtil.getArrayIndex(symbolNames, orderedSymbolNames[i]);
    }

    try {
      FileWriter writer = new FileWriter(writeFile, true);
      writer.write("\t");
      for (int i = 0; i < symbolNames.length; i++) {
        writer.write(symbolNames[orderedSymbolMap[i]] + "\t");
      }
      writer.write("\n");

      for (int i = 0; i < retTime.length; i++) {
        writer.write(retTime[i] + "\t");
        for (int j = 0; j < symbolNames.length; j++) {
          writer.write( ( (double[]) retSymbols[i])[orderedSymbolMap[j]] + "\t");
        }
        writer.write("\n");
      }

      writer.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
     private void writeMultipleSimulationResultsToFile(String[] symbolNames,
      double[] retTime,
      Vector allResults, File writeFile) {
    Iterator it = allResults.iterator();
    while (it.hasNext()) {
      Object[] retSymbols = (Object[]) it.next();
      writeSimulationResultsToFile(symbolNames, retTime, retSymbols, writeFile);
    }

     }

   */

  public void printAdjacencyMatrix(HashMap reactantDependencies,
                                   String[] symbolNames, File writeFile) {
    try {
      FileWriter writer = new FileWriter(writeFile);
      for (int symbolIndex = 0; symbolIndex < symbolNames.length; symbolIndex++) {
        String symbolName = symbolNames[symbolIndex];
        writer.write(symbolName + ":" + symbolIndex);
        Vector dependencies = (Vector) reactantDependencies.get(symbolName);
        if (dependencies != null) {
          Iterator it = dependencies.iterator();
          while (it.hasNext()) {
            String dependency = (String) it.next();
            int dependencyIndex = ArrayUtil.getArrayIndex(symbolNames, dependency);
            if (dependencyIndex == -1) {
              System.out.println(dependency + " not found");
            }
            else {
              writer.write("\t" + dependencyIndex + " \t1");
            }
          }
        }
        else {
          System.out.println("no dependencies for " + symbolName);
        }
        writer.write("\n");
      }
      writer.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method constructs the auxillary files to go along with a simulation
   * writes the correct adjacency matrix file, and converts the simulation results to
   * .exp format
   *
   * @param propsMap HashMap
   */
  /*
  public void postProcessSimulationResults(HashMap propsMap) {
    MatrixToExpConverter matrixToExpConverter = new MatrixToExpConverter();
    String matrixFileName = Util.getString(propsMap, "DirectoryBase") + Util.getString(propsMap, "WriteFileBase") +
        "SimulationResults.xls";
    String expFileName = Util.getString(propsMap, "DirectoryBase") + Util.getString(propsMap, "WriteFileBase") +
        "SimulationResults.exp";
    matrixToExpConverter.setScalingFactor(1000);
    matrixToExpConverter.convertFile(matrixFileName, expFileName);

    if ("true".equals(Util.getString(propsMap, "CalculateMutualInformation"))) {
      String mutualInformationExecutable = Util.getString(propsMap, "MutualInformationExecutable");
      double miThresh = .0000001;
      double miCleanThresh = .000000001;
      try {
        Runtime.getRuntime().exec(mutualInformationExecutable + " " + expFileName + " " + miThresh);
        System.gc();
        Runtime.getRuntime().exec(mutualInformationExecutable + " -rc " + expFileName + " "
                                  + expFileName + ".adj " + miCleanThresh);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
      */
}
