package org.geworkbench.components.genspace.server.msa;

import java.util.List;

import javax.ejb.Local;

import org.geworkbench.components.genspace.entity.msa.DNASequence;

@Local
public interface MSARecommenderLocal {

	public List<DNASequence> getRecommendedSequences(List<DNASequence> queries);

}
