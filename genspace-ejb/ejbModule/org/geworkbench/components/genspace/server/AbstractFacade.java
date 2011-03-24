package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.WorkflowFolder;

/**
 * Borrowed from Netbeans wizard :)
 * @author jon
 *
 * @param <T>
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass; 
    @PersistenceContext(unitName="genspace_persist") private EntityManager em;
    public User fullySerialize(User u) {
		u.getFolders().size();
		for(WorkflowFolder wf : u.getFolders())
		{
			wf.getWorkflows().size();
		}
		u.getFriends().size();
		u.getIncomingWorkflows().size();
		u.getWorkflowComments().size();
		u.getWorkflows().size();
		u.getNetworks().size();
		return u;
	}

    private User cachedUser = null;
    @Resource
    SessionContext ctx;
    protected User getUser()
    {
    	if(cachedUser != null)
    		return cachedUser;
    	Query q = getEntityManager().createQuery("select object(c) from User as c where c.username=:user");
    	System.out.println("Doing select for user " +  ctx.getCallerPrincipal().getName());
		q.setParameter("user", ctx.getCallerPrincipal().getName());
		User r = null;
		try
		{
		r = (User) q.getSingleResult();
		System.out.println("Have result " + r.getFullName() + r.getLabAffiliation());
		}
		catch(NoResultException e)
		{
			System.err.println("Unable to find user record for logged in user " +  ctx.getCallerPrincipal().getName());
		}
		cachedUser = r;
		return cachedUser;
    }
    
    protected User findByUserName(String username)
	{
		Query q = getEntityManager().createQuery("select object(c) from User as c where c.username=:user");
		q.setParameter("user", username);
		User r = null;
		try
		{
		r = (User) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		return r;
	}
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager()
    {
    	return em;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
