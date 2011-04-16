package org.geworkbench.components.simulation.networkProcessor;

import java.util.HashMap;
import org.systemsbiology.chem.Model;

public interface INetworkProcessor {
  public void initialize(HashMap propsMap);
  public void processNetwork(Model model);
}
