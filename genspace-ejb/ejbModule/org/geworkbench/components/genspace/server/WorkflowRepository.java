package org.geworkbench.components.genspace.server;

import java.util.Date;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import org.geworkbench.components.genspace.entity.IncomingWorkflow;
import org.geworkbench.components.genspace.entity.UserWorkflow;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowComment;
import org.geworkbench.components.genspace.entity.WorkflowFolder;

/**
 * Session Bean implementation class WorkflowRepository
 */
@Stateless
@RolesAllowed("user")
public class WorkflowRepository extends AbstractFacade<Workflow> implements WorkflowRepositoryRemote {

    /**
     * Default constructor. 
     */
    public WorkflowRepository() {
       super(Workflow.class);
    }

	@Override
	public boolean deleteMyWorkflow(UserWorkflow uw) {
		if(uw.getOwner().equals(getUser()))
		{
			uw = getEntityManager().merge(uw);
			getEntityManager().remove(uw);
			invalidateCache();
			return true;
		}
		return false;
	}

	@Override
	public WorkflowFolder addWorkflow(UserWorkflow uw, WorkflowFolder folder) {
		uw.setFolder(folder);
		uw.setCreatedAt(new Date());
		uw.setOwner(getUser());
		getEntityManager().persist(uw);
//		getEntityManager().refresh(folder);
		invalidateCache();
		WorkflowFolder ret = getEntityManager().find(WorkflowFolder.class, folder.getId());
		return ret;
	}

	@Override
	public WorkflowFolder addFolder(WorkflowFolder folder) {
		System.out.println("Adding " + folder);
		folder.setOwner(getUser());
		getEntityManager().persist(folder);
		invalidateCache();
//		getEntityManager().refresh(folder);
		return folder;
	}

	@Override
	public boolean deleteFromInbox(IncomingWorkflow wi) {
		if(wi.getReceiver().equals(getUser()))
		{
			wi = getEntityManager().merge(wi);
			getEntityManager().remove(wi);
			return true;
		}
		invalidateCache();
		return false;
	}

	@Override
	public UserWorkflow addToRepository(IncomingWorkflow wi) {
		UserWorkflow uw = new UserWorkflow(); 
		uw.setWorkflow(wi.getWorkflow());
		uw.setFolder(getUser().getRootFolder());
		uw.setCreatedAt(new Date());
		uw.setName(wi.getName());
		uw.setOwner(getUser());
		getEntityManager().persist(uw);
		invalidateCache();
//		getEntityManager().refresh(uw);
		return uw;
	}

	@Override
	public boolean removeComment(WorkflowComment wc) {
		if(wc.getCreator().equals(getUser()))
		{
			wc = getEntityManager().merge(wc);
			getEntityManager().remove(wc);
			invalidateCache();
			return true;
		}
		return false;
	}

	@Override
	public WorkflowComment addComment(WorkflowComment wc) {
		wc.setCreatedAt(new Date());
		wc.setCreator(getUser());
		getEntityManager().persist(wc);
		wc = getEntityManager().merge(wc);
		invalidateCache();
		return wc;
	}

	@Override
	public UserWorkflow importWorkflow(UserWorkflow w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendWorkflow(IncomingWorkflow newW, String receiver) {
		newW.setReceiver(findByUserName(receiver));
		newW.setSender(getUser());
		newW.setCreatedAt(new Date());
		getEntityManager().persist(newW);
		invalidateCache();
		return true;
	}

	@Override
	public boolean deleteMyFolder(WorkflowFolder folder) {
		if(folder.getOwner().equals(getUser()))
		{
			for(UserWorkflow uw : folder.getWorkflows())
			{
				getEntityManager().remove(uw);
			}
			getEntityManager().remove(folder);
			invalidateCache();
			return true;
		}
		return false;
	}

}
