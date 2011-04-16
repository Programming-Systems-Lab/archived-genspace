package org.geworkbench.components.cutenet;

import org.geworkbench.bison.datastructure.bioobjects.markers.DSGeneMarker;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.AnnotationParser;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.GeneOntologyUtil;
import org.geworkbench.bison.datastructure.bioobjects.markers.goterms.GOTerm;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: kk2457
 * Date: Mar 14, 2007
 * Time: 2:09:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenewaysRowInformation {
    private DSGeneMarker dSGeneMarker;
    private String geneName;
    private String geneType;
    private ArrayList<CutenetInteraction> interactionDetails;
    private String goInfoStr;


    public GenewaysRowInformation(DSGeneMarker dSGeneMarker,
                                  String geneName,
                                  String geneType,
                                  ArrayList interactionDetails,
                                  String goInfoStr) {
        this.dSGeneMarker = dSGeneMarker;
        this.geneName = geneName;
        this.geneType = geneType;
        this.interactionDetails = interactionDetails;
        this.goInfoStr = goInfoStr;
    }


    public GenewaysRowInformation(DSGeneMarker dSGeneMarker) {
        this.dSGeneMarker = dSGeneMarker;
        goInfoStr = "";

        // TODO: uncomment this block and test if it works.
        /*Set<GOTerm> set = GeneOntologyUtil.getOntologyUtil().getAllGOTerms(dSGeneMarker);

        if (set != null && set.size() > 0) {
            for (GOTerm goTerm : set) {
                if(goTerm != null && goTerm.getName() != null)
                    goInfoStr += goTerm.getName() + "; ";
            }
        } */
        geneType = GeneOntologyUtil.getOntologyUtil().checkMarkerFunctions(dSGeneMarker);
        geneName = dSGeneMarker.getGeneName();
    }


    public DSGeneMarker getdSGeneMarker() {
        return dSGeneMarker;
    }

    public void setdSGeneMarker(DSGeneMarker dSGeneMarker) {
        this.dSGeneMarker = dSGeneMarker;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getGeneType() {
        return geneType;
    }

    public void setGeneType(String geneType) {
        this.geneType = geneType;
    }

    public ArrayList<CutenetInteraction> getInteractionDetails() {
        return interactionDetails;
    }

    public void setInteractionDetails(ArrayList<CutenetInteraction> interactionDetails) {
        this.interactionDetails = interactionDetails;
    }

    public String getGoInfoStr() {
        return goInfoStr;
    }

    public void setGoInfoStr(String goInfoStr) {
        this.goInfoStr = goInfoStr;
    }

    public ArrayList<CutenetInteraction> getSelectedInteractions() {
        return interactionDetails;
    }


    public void reset(){
        dSGeneMarker = null;
        geneName = null;
        geneType = null;
        interactionDetails = null;
        goInfoStr = null;
    }

    public boolean equals(Object obj){
        if(obj instanceof GenewaysRowInformation){
            return dSGeneMarker.getLabel().
                    equals(((GenewaysRowInformation)obj).getdSGeneMarker().getLabel());
        }else{
            return false;
        }
    }


}
