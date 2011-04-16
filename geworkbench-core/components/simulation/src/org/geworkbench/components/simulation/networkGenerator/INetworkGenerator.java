package org.geworkbench.components.simulation.networkGenerator;

import java.util.HashMap;
import org.systemsbiology.chem.Model;

public interface INetworkGenerator {
  public void initialize(HashMap propsMap);
  public void initialize(HashMap propsMap, int one, int two, int three, int four, double five, double six, double seven, double eight, int[] inputs, int[] outputs, double K, double metrop, double actPercent);
  public Model generateNetwork();
}
