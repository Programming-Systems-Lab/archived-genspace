package org.geworkbench.components.simulation;

import java.io.File;
import java.util.HashMap;
import org.geworkbench.bison.util.FileUtil;
import org.geworkbench.util.Util;

public class SimulationCluster {
    public SimulationCluster() {
    }

    public static void main(String[] args) {
        new SimulationCluster().runSimulation(args);
    }

    void runSimulation(String[] args) {
        File dir = new File(args[0]);
        File[] files = dir.listFiles();

        String writeDir = args[1];

        int iteration = Integer.parseInt(args[2]);

        double reactionNoise = Double.parseDouble(args[3]);
        double langevinNoise = Double.parseDouble(args[4]);

        HashMap propsMap = Util.readHashMapFromFiles(files, "#");
        propsMap.put("ReactionNoise", reactionNoise + "");

        propsMap.put("SimulationLangevinAmplification", langevinNoise + "");

        if (iteration == 1) {
            SimulationMain sim = new SimulationMain();
            String[] speciesNames = sim.getSpeciesNames(propsMap);
//            File writeFile = new File(
//                System.getProperty("user.home")
//        "/scratch/aam2110"
//                + "/Simulations/Reconst/reactNoise_" + reactionNoise + "/langNoise_" + langevinNoise  + "/SpeciesNames.txt");
//                    FileUtil.printArray(speciesNames, writeFile);

            File writeFile = new File(writeDir + "/SpeciesNames.txt");

            if (!writeFile.getParentFile().exists()) {
                writeFile.getParentFile().mkdirs();
            }

            FileUtil.printArray(speciesNames, writeFile);

        }
        else {

            SimulationMain sim = new SimulationMain();
            double[] results = sim.runSteadyState(propsMap);
//            double[][] results = sim.runTimeCourse(propsMap);

//            File writeFile = new File(
//                "/razor/1/aam2110/Simulations/Reconst/noise_" + noise + "/" + iteration +
//                ".txt");
//                System.getProperty("user.home")
//        "/scratch/aam2110"
//                + "/Simulations/Reconst/reactNoise_" + reactionNoise + "/langNoise_" + langevinNoise  + "/" +
//                iteration +
//                ".txt");

            File writeFile = new File(writeDir + "/" + iteration + ".txt");

            if (!writeFile.getParentFile().exists()) {
                writeFile.getParentFile().mkdirs();
            }
            FileUtil.printArray(results, writeFile);
//            FileUtil.printMatrix(results, writeFile);
        }
    }

}
