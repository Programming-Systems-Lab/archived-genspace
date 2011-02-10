package org.geworkbench.components.simulation.networkGenerator;

public class NetworkGeneratorFactory {
  public NetworkGeneratorFactory() {
  }

  public static INetworkGenerator getNetworkGenerator(String name){
    if(name.equals("SbmlNetworkGenerator")){
      return new SbmlNetworkGenerator();
  }if(name.equals("RandomNetworkGenerator")){
    return new RandomNetworkGenerator();
  }if(name.equals("RandomProteinNetworkGenerator")){
    return new RandomProteinNetworkGenerator();
  }


    else{
      return null;
    }

  }
}
