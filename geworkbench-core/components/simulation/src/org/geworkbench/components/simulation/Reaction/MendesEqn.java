package org.geworkbench.components.simulation.Reaction;

import java.util.Vector;
import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression;
import org.systemsbiology.math.Expression.Element;
import java.util.ArrayList;

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
public class MendesEqn implements IReaction{
    Vector<Species> reactants = new Vector<Species>();
    Vector<Species> products = new Vector<Species>();
    Vector<Species> activators = new Vector<Species>();
    Vector<Species> inhibitors = new Vector<Species>();
    Vector<Species> kinases = new Vector<Species>();
    
    double hillCoef = Math.random()*5;
    double kAct = Math.random()*2;
    double kInh = Math.random()*2;
    double mrnaBasalRate = Math.random()*20;
    double proBasalRate = 1.0;
    double activeproBasalRate = 1.0;
    double basalRate = 0.01;
    
    
//    double hillCoef = 1.5;
//    double kAct = 0.01;
//    double kInh = 0.01;
//    double mrnaBasalRate = 1.0;
//    double proBasalRate = 1.0;
//    double activeproBasalRate = 1.0;
//    double basalRate = 0.5;
    int WRONG = 0;
    int maxNumModules = 3;
    
    public MendesEqn() {
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
        for (int i = 0; i < kinases.size(); i++) {
            Species foo2 = kinases.get(i);
//            if (foo.getName().compareTo(foo2.getName()) == 0) {
            if (foo.getName().equals(foo2.getName())) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean hasInhibitor(Species foo) {
        for (int i = 0; i < inhibitors.size(); i++) {
            Species foo2 = inhibitors.get(i);
            if (foo.getName().equals(foo2.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasActivator(Species foo) {
        for (int i = 0; i < activators.size(); i++) {
            Species foo2 = activators.get(i);
            if (foo.getName().equals(foo2.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean underLimit() {
        if ((activators.size() + inhibitors.size()) < 3) {
            return true;
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
        if (!hasActivator(activator))
            activators.add(activator);
    }
    
    public void addInhibitor(Species inhibitor) {
        if (!hasInhibitor(inhibitor))
            inhibitors.add(inhibitor);
    }
    
    public void addKinase(Species kinase) {
        if (!hasGene(kinase))
            kinases.add(kinase);
    }
    
    public boolean hasKinase() {
        if (kinases.isEmpty())
            return false;
        else
            return true;
    }
    
    public Expression getInfoMrnaRate(GeneInfo geneInfo, ArrayList<ReactionInfo> allReactionsInfo, Species gene) {
        ElementMath e = new ElementMath();
        Element temp1 = getTranscriptionReaction(geneInfo, allReactionsInfo);
        Element temp2 = e.getMultiply(e.getElement(-geneInfo.getDeg()), e.getElement(gene.getSymbol()));
        Element elRate = e.getAdd(temp1, temp2);
        elRate = e.getAdd(elRate, e.getElement(basalRate));
        Expression expRate = new Expression(elRate.toString());
        return expRate;
    }
    public Expression getInfoProteinRate(GeneInfo geneInfo, ReactionInfo reactionInfo, Species gene) {
        ElementMath e = new ElementMath();
        Species activator = activators.get(0);
        Element temp1 = e.getMultiply(e.getElement(reactionInfo.getVmax()), e.getElement(activator.getSymbol()));
        Element temp2 = e.getMultiply(e.getElement(-geneInfo.getDeg()), e.getElement(gene.getSymbol()));
        Element elRate = e.getAdd(temp1, temp2);
        Expression expRate = new Expression(elRate.toString());
        return expRate;
    }
    
    
    public Expression getRate(double dRate, Species gene) {
        ElementMath e = new ElementMath();
        //System.out.println("**FOR GENE " + gene.getSymbol() + "**");
        Element temp1 = getTranscriptionReaction();
        //Element temp1 = e.getMultiply(e.getElement(mrnaBasalRate), getTranscriptionReaction());
//        Element temp2 = e.getMultiply(e.getElement(dRate), e.getElement(gene.getSymbol()));
        Element temp2 = e.getMultiply(e.getElement(-Math.random()*2), e.getElement(gene.getSymbol()));
        Element elRate = e.getAdd(temp1, temp2);
        elRate = e.getAdd(elRate, e.getElement(basalRate));
        Expression expRate = new Expression(elRate.toString());
        return expRate;
    }
    
    //MODIFY THIS EVENTUALLY
    public Expression getRate2(double dRate, Species gene) {
        ElementMath e = new ElementMath();
        Species activator = activators.get(0);
        Element temp1 = e.getMultiply(e.getElement(proBasalRate), e.getElement(activator.getSymbol()));
        Element temp2 = e.getMultiply(e.getElement(dRate), e.getElement(gene.getSymbol()));
        Element elRate = e.getAdd(temp1, temp2);
        Expression expRate = new Expression(elRate.toString());
        return expRate;
    }
    
    public Expression getRate3(double dRate, Species gene) {
        ElementMath e = new ElementMath();
        
        Species activator = activators.get(0);
        Element temp = new Element();
        
        if (kinases.isEmpty()) {
            temp = HillTerm.getHillTerm(activator, 1, kAct);
        } else {
            Species kinase = kinases.get(0);
            temp = e.getMultiply(e.getElement(activeproBasalRate), HillTerm.getHillTerm(activator, 1, kAct));
            temp = e.getMultiply(temp, e.getElement(kinase.getSymbol()));
        }
        Element temp2 = e.getMultiply(e.getElement(dRate), e.getElement(gene.getSymbol()));
        Element elRate = e.getAdd(temp, temp2);
        Expression expRate = new Expression(elRate.toString());
        return expRate;
    }
    
    public Expression getDegRate(double dRate, Species gene) {
        ElementMath e = new ElementMath();
        Element temp = e.getMultiply(e.getElement(dRate), e.getElement(gene.getSymbol()));
        Expression DegRate = new Expression(temp.toString());
        return DegRate;
    }
    
    
    
    Element getTranscriptionReaction(GeneInfo geneInfo, ArrayList<ReactionInfo> allReactionsInfo) {
        ElementMath e = new ElementMath();
        int numModules = (int) (Math.random() * maxNumModules);
        numModules++;
        
        Element[] modules = new Element[numModules];
        int[] hasTF = new int[numModules];
        
        for(int i = 0; i < numModules; i++) {
            modules[i] = new Element(1.0);
        }
        
        int actCount = 0;
        int inhCount = 0;
        int moduleIndex = 0;
        
        while (actCount < activators.size() || inhCount < inhibitors.size()) {
            if (Math.random() < 0.5 && actCount < activators.size()) {
                moduleIndex = (int) (Math.random() * numModules);
                // System.out.println("ACTIVATOR ADDED TO MODULE " + actCount + " " + moduleIndex + " " + activators.size());
                ReactionInfo reactionInfo = (ReactionInfo)allReactionsInfo.get(0);
                for (int q = 0; q < allReactionsInfo.size(); q++) {
                    reactionInfo = (ReactionInfo)allReactionsInfo.get(q);
                    if (activators.get(actCount).getName().contains(reactionInfo.getSource())) {
                        modules[moduleIndex] = e.getMultiply(modules[moduleIndex], HillTerm.getMendesActTerm(activators.get(actCount), reactionInfo.getHill(), reactionInfo.getK(), reactionInfo.getVmax(), WRONG));
                    }
                }
                hasTF[moduleIndex] = 1;
                actCount++;
            }
            
            if (Math.random() < 0.5 && inhCount < inhibitors.size()) {
                moduleIndex = (int) (Math.random() * numModules);
                //System.out.println("INHIBITOR ADDED TO MODULE " + inhCount + " " + moduleIndex + " " + inhibitors.size());
                ReactionInfo reactionInfo = (ReactionInfo)allReactionsInfo.get(0);
                for (int q = 0; q < allReactionsInfo.size(); q++) {
                    reactionInfo = (ReactionInfo)allReactionsInfo.get(q);
                    if (inhibitors.get(inhCount).getName().contains(reactionInfo.getSource())) {
                        modules[moduleIndex] = e.getMultiply(modules[moduleIndex], HillTerm.getMendesInhTerm(inhibitors.get(inhCount), reactionInfo.getHill(), reactionInfo.getK(), reactionInfo.getVmax(), WRONG));
                    }
                }
                hasTF[moduleIndex] = 1;
                inhCount++;
            }
        }
        
        Element elrate = new Element(0.0);
        int noTF = 0;
        for (int i = 0; i < numModules; i++) {
            if (hasTF[i] == 1)
                elrate = e.getAdd(modules[i], elrate);
            else
                noTF++;
        }
        
        if (noTF == numModules)
            elrate = new Element(1.0);
        
        
        return elrate;//*/
    }
    
    
    
    //modified
    Element getTranscriptionReaction() {
        ElementMath e = new ElementMath();
        //    return e.getMultiply(getActivationReaction(),getInhibitionReaction()); /*
        int numModules = (int) (Math.random() * maxNumModules);
        numModules++;
//        if (numModules == 0)
        //          numModules = 1;
        
        Element[] modules = new Element[numModules];
        int[] hasTF = new int[numModules];
        
        for(int i = 0; i < numModules; i++) {
            modules[i] = new Element(1.0);
        }
        
        int actCount = 0;
        int inhCount = 0;
        int moduleIndex = 0;
        
        while (actCount < activators.size() || inhCount < inhibitors.size()) {
            if (Math.random() < 0.5 && actCount < activators.size()) {
                moduleIndex = (int) (Math.random() * numModules);
                // System.out.println("ACTIVATOR ADDED TO MODULE " + actCount + " " + moduleIndex + " " + activators.size());
                modules[moduleIndex] = e.getMultiply(modules[moduleIndex], HillTerm.getMendesActTerm(activators.get(actCount), Math.random()*5, Math.random()*2, Math.random()*20, WRONG));
                hasTF[moduleIndex] = 1;
                actCount++;
            }
            if (Math.random() < 0.5 && inhCount < inhibitors.size()) {
                moduleIndex = (int) (Math.random() * numModules);
                //System.out.println("INHIBITOR ADDED TO MODULE " + inhCount + " " + moduleIndex + " " + inhibitors.size());
                modules[moduleIndex] = e.getMultiply( modules[moduleIndex], HillTerm.getMendesInhTerm(inhibitors.get(inhCount), Math.random()*5, Math.random()*2, Math.random()*20, WRONG));
                hasTF[moduleIndex] = 1;
                inhCount++;
            }
        }
//        while (actCount < activators.size() || inhCount < inhibitors.size()) {
//            if (Math.random() < 0.5 && actCount < activators.size()) {
//                moduleIndex = (int) (Math.random() * numModules);
//               // System.out.println("ACTIVATOR ADDED TO MODULE " + actCount + " " + moduleIndex + " " + activators.size());
//                modules[moduleIndex] = e.getMultiply(modules[moduleIndex], HillTerm.getMendesActTerm(activators.get(actCount), hillCoef, kAct, mrnaBasalRate, WRONG));
//                hasTF[moduleIndex] = 1;
//                actCount++;
//            }
//            if (Math.random() < 0.5 && inhCount < inhibitors.size()) {
//                moduleIndex = (int) (Math.random() * numModules);
//                //System.out.println("INHIBITOR ADDED TO MODULE " + inhCount + " " + moduleIndex + " " + inhibitors.size());
//                modules[moduleIndex] = e.getMultiply( modules[moduleIndex], HillTerm.getMendesInhTerm(inhibitors.get(inhCount), hillCoef, kAct, mrnaBasalRate, WRONG));
//                hasTF[moduleIndex] = 1;
//                inhCount++;
//            }
//        }
        
        
        
        
        
        Element elrate = new Element(0.0);
        int noTF = 0;
        for (int i = 0; i < numModules; i++) {
            if (hasTF[i] == 1)
                elrate = e.getAdd(modules[i], elrate);
            else
                noTF++;
        }
        
        if (noTF == numModules)
            elrate = new Element(1.0);
        
        
        return elrate;//*/
    }
    
    public Element getActivationReaction() {
        if(activators.size() > 0){
            return getActivationTerm(0);
        }else{
            return new Element(1.0);
        }
    }
    
//    Element getActivationTerm(int index){
//        ElementMath e = new ElementMath();
//
//        if(index == (activators.size() - 1)){
//            return HillTerm.getMendesActTerm(activators.get(index), hillCoef, kAct, mrnaBasalRate, WRONG);
//        }else{
//            return e.getMultiply(HillTerm.getMendesActTerm(activators.get(index),
//                hillCoef, kAct, mrnaBasalRate, WRONG), getActivationTerm(index + 1));
//        }
//    }
    
    Element getActivationTerm(int index){
        ElementMath e = new ElementMath();
        
        if(index == (activators.size() - 1)){
            return HillTerm.getMendesActTerm(activators.get(index), Math.random()*5, Math.random()*2, Math.random()*20, WRONG);
        }else{
            return e.getMultiply(HillTerm.getMendesActTerm(activators.get(index),
                    Math.random()*5, Math.random()*2, Math.random()*20, WRONG), getActivationTerm(index + 1));
        }
    }
    
    
    
    public Element getInhibitionReaction() {
        if(inhibitors.size() > 0){
            return getInhibitionTerm(0);
        }else{
            return new Element(1.0);
        }
    }
    
//    Element getInhibitionTerm(int index){
//            ElementMath e = new ElementMath();
//            if(index == (inhibitors.size() - 1)){
//                return HillTerm.getMendesInhTerm(inhibitors.get(index), hillCoef, kInh, mrnaBasalRate, WRONG);
//            }else{
//                return e.getMultiply(HillTerm.getMendesInhTerm(inhibitors.get(index),
//                    hillCoef, kInh, mrnaBasalRate, WRONG), getInhibitionTerm(index + 1));
//            }
//    }
    
    Element getInhibitionTerm(int index){
        ElementMath e = new ElementMath();
        if(index == (inhibitors.size() - 1)){
            return HillTerm.getMendesInhTerm(inhibitors.get(index), Math.random()*5, Math.random()*2, Math.random()*20, WRONG);
        }else{
            return e.getMultiply(HillTerm.getMendesInhTerm(inhibitors.get(index),
                    Math.random()*5, Math.random()*2, Math.random()*20, WRONG), getInhibitionTerm(index + 1));
        }
    }
}
