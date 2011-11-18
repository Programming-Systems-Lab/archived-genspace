package org.geworkbench.components.genspace.server.msa;

import java.util.LinkedHashSet;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.msa.Alignment;
import org.geworkbench.components.genspace.entity.msa.Reference;
import org.geworkbench.components.genspace.entity.msa.Sequence;
import org.geworkbench.components.genspace.server.AbstractFacade;

@Stateless
@WebService
public class AlignmentFacade extends AbstractFacade<Alignment> implements AlignmentFacadeRemote {

	public AlignmentFacade() {
		super(Alignment.class);
	}

	@WebMethod
	@Override
	public void saveAlignment(Alignment alignment) {
		EntityManager em = getEntityManager();
		for (Sequence sequence : new LinkedHashSet<Sequence>(alignment.getSequences())) {
			Query q = em.createQuery("select object(s) from Sequence as s where s.accessionNo=:accessionNo");
			q.setParameter("accessionNo", sequence.getAccessionNo());
			Sequence s = null;
			try
			{
				s = (Sequence) q.getSingleResult();
				alignment.getSequences().remove(sequence);
				alignment.getSequences().add(s);
			}
			catch(NoResultException e) {
				em.persist(sequence);
			}

		}
		for (Reference reference : alignment.getReferences()) {
			em.persist(reference);
		}
		create(alignment);
	}

}
