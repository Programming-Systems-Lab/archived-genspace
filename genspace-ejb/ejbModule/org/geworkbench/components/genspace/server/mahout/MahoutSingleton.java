package org.geworkbench.components.genspace.server.mahout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CachingItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.CachingUserSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.geworkbench.components.genspace.entity.TasteUser;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Workflow;


@Singleton
public class MahoutSingleton implements TimedObject {
	@PersistenceContext(unitName="genspace_persist") private EntityManager em;
	
    
    @Resource(name="jdbc/GenSpaceDB")
	DataSource dataSource;
    
    @Resource
    private SessionContext context;
    
    private CachingItemSimilarity cachingItemSimilarity;
    private CachingUserSimilarity cachingUserSimilarity;
    private CachingUserSimilarity cachingUserNetworkSimilarity;
    private CachingRecommender cachingItemRecommender;
    private GenspaceMySQLJDBCDataModel mysqlModel;
    private ReloadFromJDBCDataModel model;
    private Timer refreshTimer;
    private static int RECOMMENDATIONS = 5;
    private static int NEIGHBORHOOD = 5;
    public MahoutSingleton() {
    	
	}
    @PostConstruct
    public void initialize() {
    	try {
			mysqlModel = 
				new GenspaceMySQLJDBCDataModel(dataSource, "mahout_view", "taste_users_id", "workflow_id", "preference", "timestamp", "network_id");
			model = new ReloadFromJDBCDataModel(mysqlModel);
			
			HashMap<Long, Vector<Integer>> networkMap;
			HashMap<Long, Long> parentMap;
			networkMap = mysqlModel.getUserNetworks();
			parentMap = mysqlModel.getParentMap();
			HashMap<Long,Integer> visibilityMap = mysqlModel.getUserVisibility();
			
			ItemSimilarity genspaceItemSimilarity = new GenspaceItemSimilarity(model, parentMap);
			
			UserSimilarity userSimilarity = new GenspaceUserSimilarity(model, 0, networkMap,visibilityMap);
			UserSimilarity userNetworkSimilarity = new GenspaceUserSimilarity(model, 1, networkMap,visibilityMap);
			
			cachingItemSimilarity = new CachingItemSimilarity(genspaceItemSimilarity, model);
			cachingItemRecommender = 
				new CachingRecommender(new GenspaceItemRecommender(model, cachingItemSimilarity));
			
			cachingUserSimilarity = new CachingUserSimilarity(userSimilarity, model);
			cachingUserNetworkSimilarity = new CachingUserSimilarity(userNetworkSimilarity, model);
			
			TimerService svc = context.getTimerService();
			refreshTimer = svc.createTimer(0, 10000, null);
			
		} catch (TasteException e) {
			e.printStackTrace();
		}
    }

    @PreDestroy
    public void done()
    {
    	refreshTimer.cancel();
    }
    @Override
    public void ejbTimeout(Timer timer) {
    	refresh();
    }
	public void refresh() {
    	try {
			model.refresh(null);
			
			HashMap<Long, Vector<Integer>> networkMap;
			HashMap<Long, Long> parentMap;
			networkMap = mysqlModel.getUserNetworks();
			parentMap = mysqlModel.getParentMap();
			HashMap<Long,Integer> visibilityMap = mysqlModel.getUserVisibility();

			ItemSimilarity genspaceItemSimilarity = new GenspaceItemSimilarity(model, parentMap);
			
			UserSimilarity userSimilarity = new GenspaceUserSimilarity(model, 0, networkMap,visibilityMap);
			UserSimilarity userNetworkSimilarity = new GenspaceUserSimilarity(model, 1, networkMap,visibilityMap);
			
			cachingItemSimilarity = new CachingItemSimilarity(genspaceItemSimilarity, model);
			cachingItemRecommender = 
				new CachingRecommender(new GenspaceItemRecommender(model, cachingItemSimilarity));
			
			cachingUserSimilarity = new CachingUserSimilarity(userSimilarity, model);
			cachingUserNetworkSimilarity = new CachingUserSimilarity(userNetworkSimilarity, model);
			
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}

	public List<Workflow> getToolSuggestions(int userId, int filterMethod) {
		
    	List<Workflow> ret = new ArrayList<Workflow>();
		try {
			
			List<RecommendedItem> recommendations = cachingItemRecommender.recommend(userId, RECOMMENDATIONS);
			if (recommendations != null) {
				for (RecommendedItem recommendedItem : recommendations) {
					Workflow wf = em.find(Workflow.class, new Integer(new Long (recommendedItem.getItemID()).toString()));
					ret.add(wf.slimDown());
	        	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<TasteUser> getUserSuggestions(int userId, int filterMethod) {
		List<TasteUser> ret = new ArrayList<TasteUser>();
		try {
		
			if (filterMethod == 0) {
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD, cachingUserSimilarity, model);
				
				long[] ids = neighborhood.getUserNeighborhood(userId);
				for (Long id : ids) {
					ret.add(em.find(TasteUser.class, new Integer(id.toString())));
				}
			} else {
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD, cachingUserNetworkSimilarity, model);
				
				long[] ids = neighborhood.getUserNeighborhood(userId);
				for (Long id : ids) {
					ret.add(em.find(TasteUser.class, new Integer(id.toString())));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
    
    public List<Workflow> getToolsSuggestionsForUser(int userId, int filterMethod) {
		List<Workflow> ret = new ArrayList<Workflow>();
		try {
			if (filterMethod == 0) {
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD+10, cachingUserSimilarity, model);

				Recommender toolsRecommender = 
		        	new GenericUserBasedRecommender(model, neighborhood, cachingUserSimilarity);
				List<RecommendedItem> recommendations = toolsRecommender.recommend(userId, RECOMMENDATIONS);
				if (recommendations != null) {
					for (RecommendedItem recommendedItem : recommendations) {
						Workflow wf = em.find(Workflow.class, new Integer(new Long (recommendedItem.getItemID()).toString()));
						ret.add(wf.slimDown());
		        	}
				}
			} else {
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD+10, cachingUserNetworkSimilarity, model);
				Recommender toolsRecommender = 
		        	new GenericUserBasedRecommender(model, neighborhood, cachingUserNetworkSimilarity);
				
				List<RecommendedItem> recommendations = toolsRecommender.recommend(userId, RECOMMENDATIONS);
				if (recommendations != null) {
					for (RecommendedItem recommendedItem : recommendations) {
						Workflow wf = em.find(Workflow.class, new Integer(new Long (recommendedItem.getItemID()).toString()));
						ret.add(wf.slimDown());
		        	}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<Workflow> getSimilarWorkflows(List<Tool> tools) {
		
		String criteriaString = "(";
		List<Integer> toolsIds = new ArrayList<Integer>();
		for (Tool t : tools) {
			toolsIds.add(t.getId());
			criteriaString += "wt.tool_id=" + t.getId() + " or ";
		}
		criteriaString = criteriaString.substring(0, criteriaString.length()-4);
		criteriaString += ") ";
		
//		System.out.println(criteriaString);
		
		List<Workflow> ret = new ArrayList<Workflow>();
		
		Query q = em.createNativeQuery(
				"select tu.* " +
				"from " + 
				"(select taste_users_id, workflow_id " +
				"from TRANSACTION " +
				"group by taste_users_id, workflow_id) as t, WORKFLOWTOOL wt, TASTE_USERS tu " +
				"where t.workflow_id = wt.workflow_id and " +
				"tu.id = t.taste_users_id and "+ criteriaString +
				"group by t.taste_users_id order by count(*) desc limit 1 ",
				TasteUser.class);
		TasteUser tu = null;
		try {
			tu = (TasteUser) q.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		if (tu == null)
			return null;
		else {
			Integer tasteUserId = tu.getId();
			ret = getToolsSuggestionsForUser(tasteUserId, 0);
			return ret;
		}
		
	}
	
	public List<Workflow> getSimilarUserWorkflows(int userId, int filterMethod) {
		
		List<Workflow> ret = new ArrayList<Workflow>();
		try {
			
			// No Filtering
			if (filterMethod == 0) { 
				
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD, cachingUserSimilarity, model);
				CachingRecommender cachingRecommender = 
		        	new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, cachingUserSimilarity));
				List<RecommendedItem> recommendations = cachingRecommender.recommend(userId, RECOMMENDATIONS);
				if (recommendations != null) {
					for (RecommendedItem recommendedItem : recommendations) {
						Workflow wf = em.find(Workflow.class, new Integer(new Long (recommendedItem.getItemID()).toString()));
						ret.add(wf.slimDown());
					}
				}
			} else { // Filtering within network
				
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD, cachingUserNetworkSimilarity, model);
				CachingRecommender cachingRecommender = 
		        	new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, cachingUserNetworkSimilarity));
				List<RecommendedItem> recommendations = cachingRecommender.recommend(userId, RECOMMENDATIONS);
				if (recommendations != null) {
					for (RecommendedItem recommendedItem : recommendations) {
						Workflow wf = em.find(Workflow.class, new Integer(new Long (recommendedItem.getItemID()).toString()));
						ret.add(wf.slimDown());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
