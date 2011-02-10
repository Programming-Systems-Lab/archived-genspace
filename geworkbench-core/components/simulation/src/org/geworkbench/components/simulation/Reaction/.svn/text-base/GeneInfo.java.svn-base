/*
 * geneInfo.java
 *
 * Created on April 19, 2006, 5:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.geworkbench.components.simulation.Reaction;
import java.util.ArrayList;
/**
 *
 * @author ha2130
 */
public class GeneInfo {
    double deg;
    String geneName;
    ArrayList<ReactionInfo> sources;
    double concentration = 0.0;
    public GeneInfo(String geneName) {
        this.geneName = geneName;
        sources = new ArrayList<ReactionInfo>();
    }
    
    public void addSource(String source, double k, double hill, double vmax, String interaction) {
        ReactionInfo foo = new ReactionInfo(source, k, hill, vmax, interaction);
        sources.add(foo);
    }
    
    public void removeSource(String source) {
        int removeIndex = -1;
        for (int i = 0; i < sources.size(); i++) {
            ReactionInfo foo = sources.get(i);
            if(foo.getSource().contains(source)) {
                removeIndex = i;
            }
        }
        if (removeIndex != -1) {
            sources.remove(removeIndex);
        }
    }
     
    public void setDeg(double deg) {
        this.deg = deg;
    }
    
    public double getConcentration() {
        return concentration;
    }
    
    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }
    
    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }
    
    public ArrayList<ReactionInfo> getSources() {
        return sources;
    }
    
    public double getDeg() {
        return deg;
    }
    public String getGeneName() {
        return geneName;
    }
    public void print() {
        System.out.println("Gene: " + geneName  + " Deg: " + deg);
        if (sources.size() > 0) {
            System.out.print("Sources:");
            for (int i = 0; i < sources.size(); i++) {
                System.out.print(" G: " + ((ReactionInfo)sources.get(i)).getSource() +
                        " K: " + ((ReactionInfo)sources.get(i)).getK() +
                        " H: " + ((ReactionInfo)sources.get(i)).getHill() +
                        " V: " + ((ReactionInfo)sources.get(i)).getVmax() +
                        " " + ((ReactionInfo)sources.get(i)).getInteraction());
            }
            
            System.out.println("");
        }
    }
}
