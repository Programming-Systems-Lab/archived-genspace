package org.geworkbench.components.genspace.server.msa;

import javax.ejb.Remote;

import org.geworkbench.components.genspace.entity.msa.Alignment;
import org.geworkbench.components.genspace.entity.msa.Sequence;

@Remote
public interface AlignmentFacadeRemote {

	public void saveAlignment(Alignment alignment);
	
}
