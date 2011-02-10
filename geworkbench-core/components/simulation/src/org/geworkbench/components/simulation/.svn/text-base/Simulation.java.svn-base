package org.geworkbench.components.simulation;

import java.util.HashMap;
import org.geworkbench.components.simulation.networkGenerator.INetworkGenerator;
import org.geworkbench.components.simulation.networkGenerator.NetworkGeneratorFactory;
import org.geworkbench.components.simulation.simulator.ISimulator;
import org.geworkbench.components.simulation.simulator.SimulatorFactory;
import org.geworkbench.util.Util;
import org.systemsbiology.chem.Model;

public class Simulation {
    String[] speciesNames;
    double[][] timeCourseResults;
    double[] steadyStateResults;

    public void runSimulation(HashMap propsMap) {
        String networkGeneratorKey = Util.getString(propsMap,
            "NetworkGenerator");
        INetworkGenerator networkGenerator = NetworkGeneratorFactory.
            getNetworkGenerator(networkGeneratorKey);
        networkGenerator.initialize(propsMap);
        Model model = networkGenerator.generateNetwork();

        String simulatorKey = Util.getString(propsMap, "Simulator");
        ISimulator simulator =
            SimulatorFactory.getSimulator(simulatorKey);
        simulator.initialize(propsMap);
        simulator.runSimulation(model);

        this.steadyStateResults = simulator.getSteadyStateValues();
        this.timeCourseResults = simulator.getTimeCourseValues();
        this.speciesNames = model.getOrderedSpeciesNamesArray();
    }

    String[] getSpeciesNames(HashMap propsMap) {
        String networkGeneratorKey = Util.getString(propsMap,
            "NetworkGenerator");
        INetworkGenerator networkGenerator = NetworkGeneratorFactory.
            getNetworkGenerator(networkGeneratorKey);
        networkGenerator.initialize(propsMap);
        Model model = networkGenerator.generateNetwork();

        return model.getOrderedSpeciesNamesArray();
    }

    public String[] getSpeciesNames() {
        return speciesNames;
    }

    public double[] getSteadyStateResults() {
        return steadyStateResults;
    }

    public void setTimeCourseResults(double[][] timeCourseResults) {
        this.timeCourseResults = timeCourseResults;
    }

    public void setSpeciesNames(String[] speciesNames) {
        this.speciesNames = speciesNames;
    }

    public void setSteadyStateResults(double[] steadyStateResults) {
        this.steadyStateResults = steadyStateResults;
    }

    public double[][] getTimeCourseResults() {
        return timeCourseResults;
    }

    public Simulation() {
    }
}
