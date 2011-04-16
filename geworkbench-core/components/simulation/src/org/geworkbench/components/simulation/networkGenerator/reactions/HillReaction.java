package org.geworkbench.components.simulation.networkGenerator.reactions;

import org.systemsbiology.math.Expression;

/**
 * <p>Title: caWorkbench</p>
 *
 * <p>Description: Modular Application Framework for Gene Expession, Sequence
 * and Genotype Analysis</p>
 *
 * <p>Copyright: Copyright (c) 2003 -2004</p>
 *
 * <p>Company: Columbia University</p>
 *
 * @author Adam Margolin
 * @version 3.0
 */
public class HillReaction implements IReaction{
    private double K;
    private double hillCoef;
    
    public HillReaction() {
    }
    
    public double getHillCoef() {
        return hillCoef;
    }
    
    public double getK() {
        return K;
    }
    
    public void setHillCoef(double hillCoef) {
        this.hillCoef = hillCoef;
    }
    
    public void setK(double K) {
        this.K = K;
    }
    
    public Expression getRate() {
        return null;
    }
}
