package org.geworkbench.components.simulation.Reaction;

import org.systemsbiology.math.Expression.Element;
import org.systemsbiology.math.Expression.ElementCode;
import org.systemsbiology.math.Expression;
import org.systemsbiology.math.Symbol;

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
public class ElementMath {
    public ElementMath() {
    }
    Element getSubtract(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.SUBT);
    }

    Element getPower(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.POW);
    }

    Element getSin(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.SIN);
    }

    Element getCos(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.COS);
    }

    Element getAdd(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.ADD);
    }

    Element getDivide(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.DIV);
    }

    Element getMultiply(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.MULT);
    }


    Element getOperation(Element firstOperand, Element secondOperand,
                         ElementCode elementCode) {
        Element el = new Element();
        el.mFirstOperand = firstOperand;
        el.mSecondOperand = secondOperand;
        el.mCode = elementCode;
        return el;
    }

    Element getElement(Symbol elementValue) {
        Element el = new Element();
        el.mSymbol = elementValue;
        el.mCode = ElementCode.SYMBOL;
        return el;
    }

    Element getElement(double elementValue) {
        return new Element(elementValue);
    }
}
