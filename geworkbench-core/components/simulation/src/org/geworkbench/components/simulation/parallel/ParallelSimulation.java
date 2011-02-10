package org.geworkbench.components.simulation.parallel;

import java.io.File;
import java.util.HashMap;
import mpi.MPI;
import mpi.MPIException;
import mpi.Status;
import org.geworkbench.bison.util.FileUtil;
import org.geworkbench.components.simulation.SimulationMain;
import org.geworkbench.util.Util;

public class ParallelSimulation {
  HashMap propsMap;

  public ParallelSimulation() {
  }

  static public void main(String[] args) throws MPIException {
    new ParallelSimulation().doSimulations(args);
  }

  public void doSimulations(String[] args) throws MPIException {

    MPI.Init(args);

    int my_rank; // Rank of process
    int source; // Rank of sender
    int dest; // Rank of receiver
    int tag = 50; // Tag for messages
    int myrank = MPI.COMM_WORLD.Rank();
    int p = MPI.COMM_WORLD.Size();

    final int RUN_SIMULATION = 1;
    final int SIMULATION_COMPLETE = 2;
    final int END_OF_SIMULATIONS = 3;

    File dir = new File(args[args.length - 1]);
    File[] files = dir.listFiles();

    propsMap = Util.readHashMapFromFiles(files, "#");
    int numSimulations = Util.getInt(propsMap, "NumSimulations");

    double[] simResults = new double[200];

    if (myrank == 0) {
      double[][] allResults = new double[numSimulations][];
      int[] fakeArr = new int[0];

      int simulationsRan = 0;
      //tell each slave to run a simulation
      for (int procCtr = 1; procCtr < p; procCtr++) {
        if (procCtr <= numSimulations) {
            System.out.println("Running Simulation " + simulationsRan);
            simulationsRan++;
          MPI.COMM_WORLD.Send(fakeArr, 0, 0, MPI.INT, procCtr, RUN_SIMULATION);
        }
      }

      int simulationsReceived = 0;

      while (simulationsReceived < numSimulations) {
//      while (simulationsRan < numSimulations) {
        //receive the results of the simulation
        Status status = MPI.COMM_WORLD.Recv(simResults, 0, 200, MPI.DOUBLE, MPI.ANY_SOURCE, MPI.ANY_TAG);
//        System.out.println("Master received results from " + status.source);
        if (status.tag == SIMULATION_COMPLETE) {
          allResults[simulationsReceived] = new double[status.Get_count(MPI.DOUBLE)];
          System.arraycopy(simResults, 0, allResults[simulationsReceived], 0, status.Get_count(MPI.DOUBLE));
          System.out.println("Received Results From simulation " + simulationsReceived);
          simulationsReceived++;
//          if(simulationsRan < numSimulations){
              System.out.println("Running Simulation " + simulationsRan);
              MPI.COMM_WORLD.Send(fakeArr, 0, 0, MPI.INT,
                                  status.source, RUN_SIMULATION);
              simulationsRan++;
//          }
        }
      }

      System.out.println("Printing results");
      SimulationMain sim = new SimulationMain();
      String[] speciesNames = sim.getSpeciesNames(propsMap);
      String writeFileName = Util.getString(propsMap, "ResultsFile");
      FileUtil.printMatrix(speciesNames, null, allResults, writeFileName);

      for (int procCtr = 1; procCtr < p; procCtr++) {
          MPI.COMM_WORLD.Send(fakeArr, 0, 0, MPI.INT, procCtr, END_OF_SIMULATIONS);
      }


//      Util.printArray(allResults,
//                      Util.getString(propsMap, "DirectoryBase") + Util.getString(propsMap, "WriteFileBase") +
//                      "SimulationResults.xls");
//
//      SimulationUtil simUtil = new SimulationUtil();
//      simUtil.postProcessSimulationResults(propsMap);
    }

    else { //Slave nodes to run a simulation
      int[] fakeArr = new int[0];
      int ctr = 0;
      String writeFileName = Util.getString(propsMap, "ResultsFile");
      while (true) {
        Status status = MPI.COMM_WORLD.Recv(fakeArr, 0, 0, MPI.INT, 0,
                                            MPI.ANY_TAG);
        if (status.tag == END_OF_SIMULATIONS) {
          System.out.println("exiting");
          break;
        }
        else if (status.tag == RUN_SIMULATION) {
            ctr++;
          double[] results = runSimulation(propsMap);
//          FileUtil.printArray(results, writeFileName + "_" + myrank + "_" + ctr);
          MPI.COMM_WORLD.Send(results, 0, results.length, MPI.DOUBLE, 0, SIMULATION_COMPLETE);
        }
        else {
          System.out.println("Tag not recognized " + status.tag);
        }
      }
    }
    MPI.Finalize();
  }

  double[] runSimulation(HashMap propsMap) {
    SimulationMain sim = new SimulationMain();
    double[] results = sim.runSteadyState(propsMap);
    return results;
  }

}
