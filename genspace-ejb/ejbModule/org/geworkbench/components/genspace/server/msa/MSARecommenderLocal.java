package org.geworkbench.components.genspace.server.msa;

import java.util.List;

import javax.ejb.Local;

import org.geworkbench.components.genspace.entity.msa.ProteinSequence;

@Local
public interface MSARecommenderLocal {

	public List<ProteinSequence> getRecommendedSequences(List<ProteinSequence> queries);

}
