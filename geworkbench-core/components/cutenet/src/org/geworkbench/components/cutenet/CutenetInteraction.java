package org.geworkbench.components.cutenet;

/**
 * @author John Watkinson, Kaushal Kumar
 */
public class CutenetInteraction {

    private CutenetProtein protein1;
    private CutenetProtein protein2;
    private float strength;
    private String type;

    // May want to add other information (such as interaction type) later.

    public CutenetInteraction(CutenetProtein protein1, CutenetProtein protein2, float strength, String type) {
        this.protein1 = protein1;
        this.protein2 = protein2;
        this.strength = strength;
        this.type = type;
    }

    public CutenetProtein getProtein1() {
        return protein1;
    }

    public CutenetProtein getProtein2() {
        return protein2;
    }

    public float getStrength() {
        return strength;
    }
    
    public String getType(){
    	return this.type;
    }
}
