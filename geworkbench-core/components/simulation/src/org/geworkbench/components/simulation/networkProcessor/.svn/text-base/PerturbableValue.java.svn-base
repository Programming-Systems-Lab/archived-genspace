package org.geworkbench.components.simulation.networkProcessor;

import org.systemsbiology.math.*;
import org.systemsbiology.util.DataNotFoundException;

public class PerturbableValue extends Value{
    double perturbation;
    public void setPerturbation(double perturbation) {
        this.perturbation = perturbation;
    }

    public double getPerturbation() {
        return perturbation;
    }

    public PerturbableValue(Value value) {
        super(value);
    }

    /**
     * If this object contains an {@link Expression}, computes
     * the value of the Expression using the supplied
     * {@link SymbolEvaluator}; otherwise it returns the
     * floating-point value stored in the internal
     * MutableDouble object within this object.
     */
    public double getValueWithCaching(SymbolEvaluator pSymbolValueMap) throws DataNotFoundException
    {
        double value = super.getValueWithCaching(pSymbolValueMap);
        return value * perturbation;
    }


    /**
     * Returns the floating-point value defined for this object.
     * If the object instead has an {@link Expression} stored
     * within it, an IllegalStateException is thrown.
     */
    public final double getValue() throws IllegalStateException
    {
        double value = super.getValue();
        return value * perturbation;
    }

    public final double getValue(SymbolEvaluator pSymbolValueMap) throws DataNotFoundException
    {
        double value = super.getValue(pSymbolValueMap);
        return value * perturbation;
    }

}
