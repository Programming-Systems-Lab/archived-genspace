/*
 * reactionInfo.java
 *
 * Created on April 19, 2006, 5:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.geworkbench.components.simulation.Reaction;

import org.systemsbiology.math.Expression.Element;

/**
 *
 * @author ha2130
 */
public class ReactionInfo {
    String source;
    double k;
    double hill;
    double vmax;
    String interaction;
    
    public ReactionInfo(String source, double k, double hill, double vmax, String interaction) {
        this.source = source;
        this.hill = hill;
        this.vmax = vmax;
        this.interaction = interaction;
        this.k = k;
    }
    
    public double getK() {return k;}
    public double getVmax() {return vmax;}
    public double getHill() {return hill;}
    public String getInteraction() {return interaction;}
    public String getSource() {return source;}
    
    public double getTermValue(double input) {
        ElementMath e = new ElementMath();
        Element eqn = new Element();
        
        if (getInteraction().contains("Inhibitor")) {
            eqn = HillTerm.getMendesInhTerm(input, getHill(), getK(), getVmax(), 0);
        }
        else if(getInteraction().contains("Activator")) {
            eqn = HillTerm.getMendesActTerm(input, getHill(), getK(), getVmax(), 0);
        }
        return eqn.mNumericValue;
    }
}