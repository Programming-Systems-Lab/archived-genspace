package org.geworkbench.components.simulation.simulator;

import java.util.HashMap;
import org.systemsbiology.chem.Model;

public interface ISimulator {
  public void initialize(HashMap propsMap);
  public void runSimulation(Model model);
  public double[][] getTimeCourseValues();
  public double[] getSteadyStateValues();
  public void setRequestedGeneNames(String[] geneNames);
}
