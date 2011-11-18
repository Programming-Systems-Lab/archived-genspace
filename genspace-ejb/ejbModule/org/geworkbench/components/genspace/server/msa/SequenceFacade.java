package org.geworkbench.components.genspace.server.msa;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.geworkbench.components.genspace.entity.msa.Sequence;
import org.geworkbench.components.genspace.server.AbstractFacade;

@Stateless
@WebService
public class SequenceFacade extends AbstractFacade<Sequence> implements SequenceFacadeRemote {

	public SequenceFacade() {
		super(Sequence.class);
		// TODO Auto-generated constructor stub
	}

}
