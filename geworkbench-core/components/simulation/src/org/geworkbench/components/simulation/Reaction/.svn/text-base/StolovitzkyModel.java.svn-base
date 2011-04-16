package org.geworkbench.components.simulation.Reaction;

import org.systemsbiology.chem.Compartment;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.Reaction;
import org.systemsbiology.chem.Species;
import org.systemsbiology.math.Expression;
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
public class StolovitzkyModel {

    double s_p53 = 0.02;
    double d_p53 = 0.02;
    double s_Mdm2 = 0.002;
    double e_Mdm2 = 0.024;
    double d_Mdm2 = 0.02;
    //double tau;
    double r_p53 = 0.12;
    double mu_p53 = 0.02;
    double v_p53 = 0.08;
    double k_rp = 0.2;
    double k_fp = 0.6;
    double K = 1;
    double K_d = 0.01;
    double K_p = 1;
    double v_p53_star  = 0.008;
    double K_d_star = 0.1;
    double K_a = 1.5;
    double r_Mdm2 = 0.04;
    double mu_Mdm2 = 0.008;
    double v_Mdm2 = 0.04;
    double n = 4;
    double k_af = 1;


    public StolovitzkyModel() {
    }

    public Model runModel() {
        Reaction[] reactions = new Reaction[6];
        ElementMath e = new ElementMath();

        Model m = new Model();
        m.setName("My Model");
        Compartment c = new Compartment("cell");
        Species nullGene = new Species("_void_", c);
        nullGene.setSpeciesPopulation(1.0);
        m.addSpecies(nullGene);

        //*****************************************
        Species p53_mRNA = new Species("G0", c);
        p53_mRNA.setSpeciesPopulation(1.0);
        m.addSpecies(p53_mRNA);

        Species p53_protein = new Species("G1", c);
        p53_protein.setSpeciesPopulation(1.0);
        m.addSpecies(p53_protein);

        Species p53_activated = new Species("G2", c);
        p53_activated.setSpeciesPopulation(1.0);
        m.addSpecies(p53_activated);

        Species Mdm2_mRNA = new Species("G3", c);
        Mdm2_mRNA.setSpeciesPopulation(1.0);
        m.addSpecies(Mdm2_mRNA);

        Species Mdm2_protein = new Species("G4", c);
        Mdm2_protein.setSpeciesPopulation(1.0);
        m.addSpecies(Mdm2_protein);

        Species ATM_activated = new Species("G5", c);
        ATM_activated.setSpeciesPopulation(300.0);
        m.addSpecies(ATM_activated);

        //*******************************************



        reactions[0] = new Reaction("p53_mRNA_synthesis");
        reactions[0].addReactant(nullGene, 1);
        reactions[0].addProduct(p53_mRNA, 1);

        Element elRate = e.getSubtract(e.getElement(s_p53),
                                       e.getMultiply(e.getElement(d_p53),
            e.getElement(p53_mRNA.getSymbol())));

        Expression expRate = new Expression(elRate.toString());
        reactions[0].setRate(expRate);

        //**************************************
        reactions[1] = new Reaction("Mdm2_mRNA_synthesis");
        reactions[1].addReactant(nullGene, 1);
        reactions[1].addProduct(p53_protein, 1);

        elRate = e.getAdd(e.getElement(s_Mdm2),
                          e.getSubtract(e.getMultiply(e.getElement(e_Mdm2),
            HillTerm.getHillTerm(p53_activated,n,K)),
                                        e.getMultiply(e.getElement(d_Mdm2),
            e.getElement(Mdm2_mRNA.getSymbol()))));

        expRate = new Expression(elRate.toString());
        reactions[1].setRate(expRate);

        //***************************************
        reactions[2] = new Reaction("p53_protein_synthesis");
        reactions[2].addReactant(nullGene, 1);
        reactions[2].addProduct(p53_activated, 1);
        elRate = e.getSubtract(e.getMultiply(e.getElement(r_p53),e.getElement(p53_mRNA.getSymbol())),
                               e.getMultiply(e.getElement(mu_p53),e.getElement(p53_protein.getSymbol())));

        elRate = e.getSubtract(elRate, e.getMultiply(e.getElement(v_p53),e.getMultiply(e.getElement(Mdm2_protein.getSymbol()),
            HillTerm.getHillTerm(p53_protein, 1, K_d))));

        elRate = e.getAdd(elRate, e.getMultiply(e.getElement(k_rp), e.getElement(p53_activated.getSymbol())));

        elRate = e.getSubtract(elRate, e.getMultiply(e.getElement(k_fp),
            e.getMultiply(e.getElement(ATM_activated.getSymbol()), HillTerm.getHillTerm(p53_protein, 1, K_p))));

        expRate = new Expression(elRate.toString());
        reactions[2].setRate(expRate);

        //******************************************
        reactions[3] = new Reaction("p53_activated_synthesis");
        reactions[3].addReactant(nullGene, 1);
        reactions[3].addProduct(Mdm2_mRNA, 1);

        elRate = e.getMultiply(e.getElement(k_fp),e.getMultiply(e.getElement(ATM_activated.getSymbol()),
            HillTerm.getHillTerm(p53_protein, 1, K_p)));
        elRate = e.getSubtract(elRate, e.getMultiply(e.getElement(k_rp),e.getElement(p53_activated.getSymbol())));
        elRate = e.getSubtract(elRate, e.getMultiply(e.getElement(v_p53_star),
            e.getMultiply(e.getElement(Mdm2_protein.getSymbol()), HillTerm.getHillTerm(p53_activated, 1, K_d_star))));

        expRate = new Expression(elRate.toString());
        reactions[3].setRate(expRate);

        //********************************************
        reactions[4] = new Reaction("Mdm2_protein_synthesis");
        reactions[4].addReactant(nullGene, 1);
        reactions[4].addProduct(Mdm2_protein, 1);

        elRate = e.getMultiply(e.getElement(r_Mdm2), e.getElement(Mdm2_mRNA.getSymbol()));
        elRate = e.getSubtract(elRate, e.getMultiply(e.getAdd(e.getElement(mu_Mdm2),
            e.getMultiply(e.getSubtract(e.getElement(v_Mdm2),
                                        e.getElement(mu_Mdm2)),
                          HillTerm.getHillTerm(ATM_activated, 1, K_a))), e.getElement(Mdm2_protein.getSymbol())));

        expRate = new Expression(elRate.toString());
        reactions[4].setRate(expRate);

        /**********************************************
        reactions[5] = new Reaction("ATM_activated_synthesis");
        reactions[5].addReactant(nullGene, 1);
        reactions[5].addProduct(ATM_activated, 1);

        elRate = e.getMultiply(e.getElement(k_af), e.getElement(Mdm2_mRNA.getSymbol()));
        expRate = new Expression(elRate.toString());
        reactions[5].setRate(expRate);
        //*/


        for (int i = 0; i < 5; i++) {
            m.addReaction(reactions[i]);
            System.out.println("ADDED REACTION " + i);
        }

        //System.out.println(m.toString());
        System.out.println("FINISHED MAKING MODEL");
        return m;
    }
}
