package org.geworkbench.components.simulation.Reaction;

import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression;

/**
 * <p>Title: Bioworks</p>
 *
 * <p>Description: Modular Application Framework for Gene Expession, Sequence
 * and Genotype Analysis</p>
 *
 * <p>Copyright: Copyright (c) 2003 -2004</p>
 *
 * <p>Company: Columbia University</p>
 *
 * @author not attributable
 * @version 1.0
 */
public interface IReaction {
    public Expression getRate(double dRate, Species gene);

}
