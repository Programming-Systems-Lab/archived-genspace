package org.geworkbench.components.simulation.simulator;

import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.SimulatorDeterministicBase;
import org.systemsbiology.chem.SimulatorDeterministicRungeKuttaFixed;
import org.systemsbiology.chem.SimulatorParameters;

public class SimulatorDefault extends SimulatorBase{
  double[][] timeCourseValues;

  public SimulatorDefault() {
  }

  public void runSimulation(Model model){
    try {

      double[] retTime = new double[numTimePoints];
      Object[] retSymbols = new Object[numTimePoints];

      SimulatorDeterministicBase simulator = new SimulatorDeterministicRungeKuttaFixed();
      simulator.setLangevinAmplification(this.simulationLangevinAmplification);
      simulator.initialize(model);
      SimulatorParameters params = simulator.getDefaultSimulatorParameters();

      if(this.requestedGeneNames == null){
          this.requestedGeneNames = model.getOrderedSpeciesNamesArray();
      }
      //Gotta check on the ordering here
//      String[] symbolNames = model.getOrderedSpeciesNamesArray();
//      String[] symbolNames = model.getDynamicSymbolNames();
//      String[] symbolNames = model.getOrderedNonConstantSymbolNamesArray();

      simulator.simulate(0, numTimePoints - 1, params, numTimePoints,
                         this.requestedGeneNames, retTime, retSymbols);

//      String[] tmp = new String[0];
//      String[] orderedSymbolNames = (String[]) model.vecOrderedSpecies.toArray(tmp);

//      double[] steadyStateResults = (double[]) retSymbols[retTime.length - 1];

//      if(printSimulationResults){
//        util.writeSimulationResultsToFile(symbolNamesNoPumps, orderedSymbolNames, retTime, retSymbols, timeCourseFile);
//      }
//      return steadyStateResults;
      timeCourseValues = new double[retSymbols.length][];
      for(int i = 0; i < timeCourseValues.length; i++){
        timeCourseValues[i] = (double[])retSymbols[i];
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public double[][] getTimeCourseValues() {
    return timeCourseValues;
  }


  /**
   * getSteadyStateValues
   *
   * @return double[]
   */
  public double[] getSteadyStateValues() {



    return timeCourseValues[timeCourseValues.length - 1];
  }
}
