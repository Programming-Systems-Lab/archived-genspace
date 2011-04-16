package org.geworkbench.components.simulation.networkGenesProcessor;

import org.geworkbench.util.ArrayUtil;

public class NetworkGenesProcessor {
    public NetworkGenesProcessor() {
    }

    public String[] getGeneListExcluding(String[] geneList, String excludeName){
        int excludeIndex = ArrayUtil.getArrayIndex(geneList, excludeName);
        return getGeneListExcluding(geneList, excludeIndex);
    }

    public String[] getGeneListExcluding(String[] geneList, int excludeIndex){
        String[] newGeneList = new String[geneList.length - 1];
        for(int i = 0; i < newGeneList.length; i++){
            if(i < excludeIndex){
                newGeneList[i] = geneList[i];
            }else if(i > excludeIndex){
                newGeneList[i - 1] = geneList[i];
            }
        }
        return newGeneList;
    }
}
