package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

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
@WebService
public class WorkflowRepository extends AbstractFacade<Workflow> implements WorkflowRepositoryRemote {

    /**
     * Default constructor. 
     */
    public WorkflowRepository() {
       super(Workflow.class);
    }

	@Override
	@WebMethod
	public boolean deleteMyWorkflow(int uw_id) {
    	logUsage();

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
	@WebMethod
	public WorkflowFolder addWorkflow(UserWorkflow uw, int folder_id) {
    	logUsage();

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
	@WebMethod
	public WorkflowFolder addFolder(WorkflowFolder folder) {
		System.out.println("Adding " + folder);
		folder.setOwner(getUser());
		getEntityManager().persist(folder);
		invalidateCache();
//		getEntityManager().refresh(folder);
		return folder;
	}

	@Override
	@WebMethod
	public boolean deleteFromInbox(int wiid) {
    	logUsage();

		IncomingWorkflow wi = getEntityManager().find(IncomingWorkflow.class, wiid);
		if(wi != null && wi.getReceiver().equals(getUser()))
		{ 
			wi = getEntityManager().merge(wi);
			getEntityManager().remove(wi);
			return true;
		}
		invalidateCache();
		return false;
	}

	@Override
	@WebMethod
	public UserWorkflow addToRepository(int wiid) {
    	logUsage();

		IncomingWorkflow wi = getEntityManager().find(IncomingWorkflow.class, wiid);

		UserWorkflow uw = new UserWorkflow(); 
		uw.setWorkflow(wi.getWorkflow());
		uw.setFolder(getUser().getRootFolder());
		uw.setCreatedAt(new Date());
		uw.setName(wi.getName());
		uw.setOwner(getUser());
		getEntityManager().persist(uw);
		getEntityManager().remove(wi);
		invalidateCache();
//		getEntityManager().refresh(uw);
		return uw;
	}

	@Override
	@WebMethod
	public boolean removeComment(int wcid) {
    	logUsage();

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
	@WebMethod
	public WorkflowComment addComment(WorkflowComment wc) {
    	logUsage();

		wc.setCreatedAt(new Date());
		wc.setCreator(getUser());
		getEntityManager().persist(wc);
		wc = getEntityManager().merge(wc);
//		getEntityManager().refresh(wc.getWorkflow());
		invalidateCache();
		return wc;
	}

	@Override
	@WebMethod
	public UserWorkflow importWorkflow(UserWorkflow w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public boolean sendWorkflow(IncomingWorkflow newW, String receiver) {
    	logUsage();

		newW.setReceiver(findByUserName(receiver));
		newW.setSender(getUser());
		newW.setCreatedAt(new Date());
		getEntityManager().persist(newW);
		invalidateCache();
		return true;
	}

	@Override
	@WebMethod
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

	@Override
	@WebMethod
	public List<IncomingWorkflow> getIncomingWorkflows() {
    	logUsage();

		ArrayList<IncomingWorkflow> ret = new ArrayList<IncomingWorkflow>();
		for(IncomingWorkflow w : getUser().getIncomingWorkflows())
		{
			IncomingWorkflow w2 = new IncomingWorkflow();
			w2.setId(w.getId());
			w2.setName(w.getName());
			w2.setReceiver(w.getReceiver());
			w2.setSender(w.getSender());
			w2.setWorkflow(w.getWorkflow().slimDown());
			w2.setCreatedAt(w.getCreatedAt());
			ret.add(w2);
		}
		getEntityManager().clear();
		System.out.println(ret);
		return ret;
	}

	@WebMethod(exclude=true)
	@Override
	public WorkflowFolder addWorkflow(byte[] uw, int folder) {
		return addWorkflow((UserWorkflow) AbstractFacade.readObject(uw), folder);
	}

	@WebMethod(exclude=true)
	@Override
	public byte[] addComment(byte[] comment) {
		return AbstractFacade.writeObject(addComment((WorkflowComment) AbstractFacade.readObject(comment)));
	}
	@WebMethod(exclude=true)
	@Override
	public boolean sendWorkflowBytes(byte[] newWorkflow, String receiver) {
		return sendWorkflow((IncomingWorkflow) AbstractFacade.readObject(newWorkflow), receiver);
	}
	@WebMethod(exclude=true)
	@Override
	public byte[] getIncomingWorkflowsBytes() {
		return AbstractFacade.writeObject(getIncomingWorkflows());
	}

}
