package org.geworkbench.components.idea;

import java.io.Serializable;

import javax.swing.JOptionPane;

import org.geworkbench.bison.datastructure.bioobjects.IdeaEdge.InteractionType;

/**
 * Data structure to hold the network edge information from parameter panel. 
 * 
 * @author zji
 * @version $Id: IdeaNetworkEdge.java 8753 2012-01-24 20:16:06Z maz $
 */
public class IdeaNetworkEdge implements Serializable {
	
	private static final long serialVersionUID = -5741396026577511626L;
	private int geneId1;
	private int geneId2;
	private InteractionType interactionType;
	
	IdeaNetworkEdge(){
		
	}	
	
	IdeaNetworkEdge(int node1, int node2){
		geneId1=node1;
		geneId2=node2;
		setInteractionType(InteractionType.PROTEIN_DNA);	//defaut setting
	}

	public boolean parseIdeaNetworkEdge(String line) {
		String[] tokens = line.split("\\s");
		try{
			geneId1 = Integer.parseInt(tokens[0]);
			geneId2 = Integer.parseInt(tokens[1]);
			setInteractionType(stringToInteractionType(tokens[3]));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(
					null,
					"network format error!",
					"Parsing Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	private static InteractionType stringToInteractionType(String str) {
		int ppiId = Integer.parseInt(str);

		InteractionType interactionType = null;
		if (ppiId == 0)
			interactionType = InteractionType.PROTEIN_DNA;
		else if (ppiId == 1)
			interactionType = InteractionType.PROTEIN_PROTEIN;

		return interactionType;
	}
	
	public int getGene1(){
		return geneId1;
	}
	
	public int getGene2(){
		return geneId2;
	}

	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof IdeaNetworkEdge) ) {
			return false;
		}
		IdeaNetworkEdge edge = (IdeaNetworkEdge)obj;
		if(geneId1==edge.getGene1() && geneId2==edge.getGene2()) {
			return true;
		} else if(geneId1==edge.getGene2() && geneId2==edge.getGene1()) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + geneId1;		
		return hash;
	}

	public void setInteractionType(InteractionType interactionType) {
		this.interactionType = interactionType;
	}

	public InteractionType getInteractionType() {
		return interactionType;
	}
}
