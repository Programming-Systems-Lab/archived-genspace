package org.geworkbench.components.simulation.networkGenerator;

import java.util.Vector;
import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression;
import org.systemsbiology.math.Expression.Element;
import org.systemsbiology.math.Expression.ElementCode;
import org.systemsbiology.math.Symbol;

public class MendesReaction {
    Vector<Species> reactants = new Vector<Species>();
    Vector<Species> products = new Vector<Species>();
    Vector<Species> activators = new Vector<Species>();
    Vector<Species> inhibitors = new Vector<Species>();

    double hillCoef = Math.random()*5;
    double kAct = Math.random()*2;
    double kInh = Math.random()*2;
    double basalRate = 1.0;
    //If you want the form of mendes equation with wrong exponents then set the
    //WRONG flag to 1, if you want the correct Hill exponents, set the flag to 0
 //   double hillCoef = 1;
    int WRONG = 0;
   // double basalRate = 0.006;
    //double vmax = 10 * basalRate;
    ///double kAct = 1;
    //double kInh = 1;

    public MendesReaction() {
    }


    public boolean hasGene(Species foo) {
        for (int i = 0; i < activators.size(); i++) {
            Species foo2 = activators.get(i);
//            if (foo.getName().compareTo(foo2.getName()) == 0) {
              if (foo.getName().equals(foo2.getName())) {
                return true;
            }
        }
        for (int i = 0; i < inhibitors.size(); i++) {
            Species foo2 = inhibitors.get(i);
//            if (foo.getName().compareTo(foo2.getName()) == 0) {
            if (foo.getName().equals(foo2.getName())) {
                return true;
            }
        }
        return false;
    }
    public void addReactant(Species reactant) {
        if (!hasGene(reactant))
            reactants.add(reactant);
    }

    public void addProduct(Species product) {
        if (!hasGene(product))
            products.add(product);
    }

    public void addActivator(Species activator) {
        if (!hasGene(activator))
            activators.add(activator);
    }

    public void addInhibitor(Species inhibitor) {
        if (!hasGene(inhibitor))
            inhibitors.add(inhibitor);
    }

    public Expression getRate(double dRate, Species gene) {
        //Element temp0 = getMultiply(getElement(vmax), getTranscriptionReaction());//added
        Element temp1 = getMultiply(getElement(basalRate), getTranscriptionReaction());
        //Element temp1 = getAdd(getElement(basalRate), temp0);//added
        Element temp2 = getMultiply(getElement(dRate), getElement(gene.getSymbol()));
        Element elRate = getAdd(temp1, temp2);
        Expression expRate = new Expression(elRate.toString());
        return expRate;
    }

   public Expression getDegRate(double dRate, Species gene) {
       Element temp = getMultiply(getElement(dRate), getElement(gene.getSymbol()));
       //Element elRate = getSubtract(temp, getElement(1.0));
       Expression DegRate = new Expression(temp.toString());
       return DegRate;

   }

    //*modified
    Element getTranscriptionReaction() {
        return getMultiply(getActivationReaction(),getInhibitionReaction());
    }
    //*/

    public Element getActivationReaction() {
        if(activators.size() > 0){
            return getActivationTerm(0);
        }else{
            return new Element(1.0);
        }
    }

    Element getActivationTerm(int index){
        if(index == (activators.size() - 1)){
            return getActivationTerm(activators.get(index), WRONG);
        }else{
            return getMultiply(getActivationTerm(activators.get(index), WRONG), getActivationTerm(index + 1));
        }
    }

    Element getActivationTerm(Species activator, int hillOrMendes) {

        if (hillOrMendes == 0) {
            Element numerator = getPower(getElement(activator.getSymbol()),
                                         getElement(hillCoef));
            Element denominator = getAdd(getPower(getElement(activator.getSymbol()),
                                                  getElement(hillCoef)),
                                         getPower(getElement(kAct),
                                                  getElement(hillCoef)));
            Element fraction = getDivide(numerator, denominator);

            Element activationTerm = getAdd(getElement(1.0), fraction);
            return activationTerm;
            //return fraction;
        }
        else {
            Element numerator = getElement(activator.getSymbol());
            Element denominator = getAdd(getElement(activator.getSymbol()),
                                         getElement(kAct));
            Element fraction = getDivide(numerator, denominator);
            Element activationTerm = getAdd(getElement(1.0), fraction);
            Element term = getPower(activationTerm, getElement(hillCoef));
            return term;
        }
    }

    public Element getInhibitionReaction() {
        if(inhibitors.size() > 0){
            return getInhibitionTerm(0);
        }else{
            return new Element(1.0);
        }
    }

    Element getInhibitionTerm(int index){
        if(index == (inhibitors.size() - 1)){
            return getInhibitionTerm(inhibitors.get(index), WRONG);
        }else{
            return getMultiply(getInhibitionTerm(inhibitors.get(index), WRONG), getInhibitionTerm(index + 1));
        }
    }

    Element getInhibitionTerm(Species inhibitor, int hillOrMendes) {

        if (hillOrMendes == 0) {
            Element numerator = getPower(getElement(kInh),
                                         getElement(hillCoef));
            Element denominator = getAdd(getPower(getElement(inhibitor.getSymbol()),
                                                  getElement(hillCoef)),
                                         getPower(getElement(kInh),
                                                  getElement(hillCoef)));
            Element fraction = getDivide(numerator, denominator);
            return fraction;
        }
        else {
            Element numerator = getElement(kInh);
            Element denominator = getAdd(getElement(inhibitor.getSymbol()),
                                         getElement(kInh));
            Element fraction = getDivide(numerator, denominator);
            Element term = getPower(fraction, getElement(hillCoef));
            return term;
        }
    }


    Element getSubtract(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.SUBT);
    }

    Element getPower(Element firstOperand, Element secondOperand) {
        return getOperation(firstOperand, secondOperand, ElementCode.POW);
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
