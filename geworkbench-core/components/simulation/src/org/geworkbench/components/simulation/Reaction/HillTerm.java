package org.geworkbench.components.simulation.Reaction;

import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression.Element;

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
public class HillTerm {

    public HillTerm(){
    }

    public static Element getHillTerm(Species activator, double n, double K) {
        ElementMath e = new ElementMath();
            Element numerator = e.getPower(e.getElement(activator.getSymbol()),
                                         e.getElement(n));
            Element denominator = e.getAdd(e.getPower(e.getElement(activator.getSymbol()),
                                                  e.getElement(n)),
                                         e.getPower(e.getElement(K),
                                                  e.getElement(n)));
            Element fraction = e.getDivide(numerator, denominator);
            return fraction;
        }

    public static Element getMendesActTerm(Species activator, double n, double K, double vmax, int WRONG) {
        ElementMath e = new ElementMath();
        if (WRONG == 1) {
            Element numerator = e.getElement(activator.getSymbol());
            Element denominator = e.getAdd(e.getElement(activator.getSymbol()),
                                         e.getElement(K));
            Element fraction = e.getDivide(numerator, denominator);
            Element activationTerm = e.getAdd(e.getElement(1.0), fraction);
            Element term = e.getPower(activationTerm, e.getElement(n));
            return term;
        }
        else {
            Element numerator = e.getPower(e.getElement(activator.getSymbol()),
                                           e.getElement(n));
            Element denominator = e.getAdd(e.getPower(e.getElement(activator.getSymbol()),
                e.getElement(n)), e.getPower(e.getElement(K), e.getElement(n)));
            Element fraction = e.getDivide(numerator, denominator);
            Element term = e.getMultiply(e.getElement(vmax), fraction);
            return term;
           // Element activationTerm = e.getAdd(e.getElement(1.0), fraction);
            //return activationTerm;
        }
    }
    
    public static Element getMendesActTerm(double input, double n, double K, double vmax, int WRONG) {
        ElementMath e = new ElementMath();
        if (WRONG == 1) {
            Element numerator = e.getElement(input);
            Element denominator = e.getAdd(e.getElement(input),
                                         e.getElement(K));
            Element fraction = e.getDivide(numerator, denominator);
            Element activationTerm = e.getAdd(e.getElement(1.0), fraction);
            Element term = e.getPower(activationTerm, e.getElement(n));
            return term;
        }
        else {
            Element numerator = e.getPower(e.getElement(input),
                                           e.getElement(n));
            Element denominator = e.getAdd(e.getPower(e.getElement(input),
                e.getElement(n)), e.getPower(e.getElement(K), e.getElement(n)));
            Element fraction = e.getDivide(numerator, denominator);
            Element term = e.getMultiply(e.getElement(vmax), fraction);
            return term;
           // Element activationTerm = e.getAdd(e.getElement(1.0), fraction);
            //return activationTerm;
        }
    }

    public static Element getMendesInhTerm(Species inhibitor, double n, double K, double vmax, int WRONG) {
        ElementMath e = new ElementMath();
        if (WRONG == 1) {
            Element numerator = e.getElement(K);
            Element denominator = e.getAdd(e.getElement(inhibitor.getSymbol()),
                                         e.getElement(K));
            Element fraction = e.getDivide(numerator, denominator);
            Element term = e.getPower(fraction, e.getElement(n));
            return term;
        }
        else {
            Element numerator = e.getPower(e.getElement(K),
                                         e.getElement(n));
            Element denominator = e.getAdd(e.getPower(e.getElement(inhibitor.getSymbol()),
                                                  e.getElement(n)),
                                         e.getPower(e.getElement(K),
                                                  e.getElement(n)));
            Element fraction = e.getDivide(numerator, denominator);
            Element term = e.getMultiply(e.getElement(vmax), fraction);
            return term;
        }
    }
    
    public static Element getMendesInhTerm(double input, double n, double K, double vmax, int WRONG) {
        ElementMath e = new ElementMath();
        if (WRONG == 1) {
            Element numerator = e.getElement(K);
            Element denominator = e.getAdd(e.getElement(input),
                                         e.getElement(K));
            Element fraction = e.getDivide(numerator, denominator);
            Element term = e.getPower(fraction, e.getElement(n));
            return term;
        }
        else {
            Element numerator = e.getPower(e.getElement(K),
                                         e.getElement(n));
            Element denominator = e.getAdd(e.getPower(e.getElement(input),
                                                  e.getElement(n)),
                                         e.getPower(e.getElement(K),
                                                  e.getElement(n)));
            Element fraction = e.getDivide(numerator, denominator);
            Element term = e.getMultiply(e.getElement(vmax), fraction);
            return term;
        }
    }

}
