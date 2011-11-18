package org.geworkbench.components.genspace.server.msa;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.geworkbench.components.genspace.entity.msa.Alignment;
import org.geworkbench.components.genspace.entity.msa.Reference;
import org.geworkbench.components.genspace.server.AbstractFacade;

@Stateless
@WebService
public class ReferenceFacade extends AbstractFacade<Reference> implements ReferenceFacadeRemote {

	public ReferenceFacade() {
		super(Reference.class);
		// TODO Auto-generated constructor stub
	}

}
