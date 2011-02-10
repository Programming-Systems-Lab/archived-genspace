package org.geworkbench.components.simulation.networkProcessor;

public class NetworkProcessorFactory {
    public NetworkProcessorFactory() {
    }

    public static INetworkProcessor getNetworkProcessor(String name){
        if("ReactionEfficiencyProcessor".equals(name)){
            return new ReactionEfficiencyProcessor();
        }else{
            return null;
        }
    }
}
