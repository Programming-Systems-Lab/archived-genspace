package org.geworkbench.components.simulation.simulator;

import java.util.HashMap;
import org.geworkbench.components.simulation.util.SimulationUtil;
import org.geworkbench.util.Util;

public abstract class SimulatorBase
    implements ISimulator {
    String[] requestedGeneNames;
//  String directoryBase;
//  String fileNameBase;


//  File steadyStateFile;
//  File timeCourseFile;
//  File perturbedNodesFile;
//  File varianceFile;

//  boolean printSimulationResults = true;

  int numTimePoints = 20;
  double simulationLangevinAmplification = .03;

  SimulationUtil util = new SimulationUtil();

  public SimulatorBase() {

  }
    public void setRequestedGeneNames(String[] requestedGeneNames) {
        this.requestedGeneNames = requestedGeneNames;
    }

    public String[] getRequestedGeneNames() {
        return requestedGeneNames;
    }



  public void initialize(HashMap propsMap) {
//    String directoryBase = Util.getString(propsMap, "DirectoryBase");
//    String writeFileBase = Util.getString(propsMap, "WriteFileBase");
//    String sbmlFileRelative = Util.getString(propsMap, "SbmlFileRelative");
//    String timeCourseFileRelative = Util.getString(propsMap, "TimeCourseFileRelative");
//    String timeCourseFileName = Util.getString(propsMap, "TimeCourseFileName");
//    String sbmlFileName = Util.getString(propsMap, "SbmlFileName");

//    new File(directoryBase + sbmlFileRelative).mkdirs();
//    new File(directoryBase + writeFileBase + timeCourseFileRelative).mkdirs();

//    this.sbmlFile = new File(directoryBase + sbmlFileRelative + sbmlFileName);
//    this.steadyStateFile = new File(directoryBase + writeFileBase + "_SteadyState.txt");
//    this.varianceFile = new File(directoryBase + writeFileBase + "_variances.xls");
//    this.timeCourseFile = new File(directoryBase + writeFileBase + timeCourseFileRelative + timeCourseFileName);

    int tps = Util.getInt(propsMap, "NumTimePoints");
    if(tps != Integer.MIN_VALUE){
      this.numTimePoints = tps;
    }

    double la = Util.getDouble(propsMap, "SimulationLangevinAmplification");
    if(!Double.isNaN(la)){
      this.simulationLangevinAmplification = la;
    }
  }

  //get rid of "_void_" -- needs to be changed to be more general
//  public String[] removeLastValue(String[] arr) {
//    String[] retVal = new String[arr.length - 1];
//    for (int i = 0; i < retVal.length; i++) {
//      retVal[i] = arr[i];
//    }
//    return retVal;
//  }


//  public String[] getSimulationGeneNames(){
//    Model model = util.createModelFromSbmlFile(sbmlFile);
//    String[] symbolNamesNoPumps = removeLastValue(model.getOrderedNonConstantSymbolNamesArray());
//    return symbolNamesNoPumps;
//  }

}
