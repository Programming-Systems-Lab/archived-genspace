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
	public boolean deleteMyWorkflow(int uw_id) {
		UserWorkflow uw = getEntityManager().find(UserWorkflow.class, uw_id);
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
	public WorkflowFolder addWorkflow(UserWorkflow uw, int folder_id) {
		WorkflowFolder folder = getEntityManager().find(WorkflowFolder.class, folder_id);
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
	public boolean deleteFromInbox(int wiid) {
		IncomingWorkflow wi = getEntityManager().find(IncomingWorkflow.class, wiid);
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
	public UserWorkflow addToRepository(int wiid) {
		IncomingWorkflow wi = getEntityManager().find(IncomingWorkflow.class, wiid);

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
	public boolean removeComment(int wcid) {
		WorkflowComment wc = getEntityManager().find(WorkflowComment.class, wcid);

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
	public boolean deleteMyFolder(int folderid) {
		WorkflowFolder folder = getEntityManager().find(WorkflowFolder.class, folderid);

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
