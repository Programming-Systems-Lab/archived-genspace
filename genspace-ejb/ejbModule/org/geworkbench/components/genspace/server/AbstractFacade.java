package org.geworkbench.components.genspace.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.geworkbench.components.genspace.entity.LogAction;
import org.geworkbench.components.genspace.entity.User;

/**
 * Borrowed from Netbeans wizard :)
 * @author jon
 *
 * @param <T>
 */
public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	protected void logUsage()
	{
		StackTraceElement[] ste = new Throwable().getStackTrace();
		String methodn = ste[1].getMethodName();
		String classn = ste[1].getClassName().replace("org.geworkbench.components.genspace.server.", "");
		String username = ctx.getCallerPrincipal().getName();
		Query q;

		if("ANONYMOUS".equals(username) || username == null)
		{
			q = getEntityManager().createNativeQuery("insert into usageevent (createdat,ipaddress,actiontype_id,user_id) " +
					"select NOW() as createdat, ? as ipaddress, la.id as actiontype_id, null as user_id from logaction la " +
					"where la.`CLASSNAME` = ? and la.`METHODNAME`=?");
			q.setParameter(1, getRemoteIP());
			q.setParameter(2, classn);
			q.setParameter(3, methodn);
		}
		else
		{			
			q = getEntityManager().createNativeQuery("insert into usageevent (createdat,ipaddress,actiontype_id,user_id) " +
			"select NOW() as createdat, 123 as ipaddress, la.id as actiontype_id, r.id as user_id from logaction la, registration r " +
			"where la.`CLASSNAME` = ? and la.`METHODNAME`=? and r.username=?");
			q.setParameter(1, getRemoteIP());
			q.setParameter(2, classn);
			q.setParameter(3, methodn);
			q.setParameter(4, username);
		}
		if(q.executeUpdate() == 0)
		{
			LogAction axn = new LogAction();
			axn.setClassName(classn);
			axn.setMethodName(methodn);
			getEntityManager().persist(axn);
		}
	}
	
	void setEm(EntityManager em) {
		this.em = em;
	}
	@PersistenceContext(unitName="genspace_persist") private EntityManager em;

	@Resource
	WebServiceContext wsContex;
	@WebMethod
	protected long getRemoteIP()
	{
		if(wsContex == null)
			return 0;

		try
		{
		MessageContext mc = wsContex.getMessageContext();
		HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
		String[] addrArray = req.getRemoteAddr().split("\\.");

		long num = 0;
		for (int i=0;i<addrArray.length;i++) {
			int power = 3-i;

			num += ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
		}
		return num;
		}
		catch(Exception ex)
		{
			return 0;
		}
	}

	public static Object readObject(byte[] data)
	{
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new ByteArrayInputStream(data));
			return is.readObject(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] writeObject(Object o)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	protected void invalidateCache()
	{
		//    	cachedUser = null;
	}
	//    private User cachedUser = null;
	@Resource
	SessionContext ctx;
	protected User getUser()
	{
		//    	if(cachedUser != null)
		//    		return cachedUser;
		if(ctx.getCallerPrincipal().getName().equals("anonymous") || ctx.getCallerPrincipal().getName().equals("ANONYMOUS"))
			return null;
		Query q = getEntityManager().createQuery("select object(c) from User as c where c.username=:user");
		q.setParameter("user", ctx.getCallerPrincipal().getName());
		User r = null;
		try
		{
			r = (User) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			System.err.println("Unable to find user record for logged in user " +  ctx.getCallerPrincipal().getName());
		}
		return r;
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
