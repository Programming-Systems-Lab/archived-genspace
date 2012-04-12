package org.geworkbench.components.genspace.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.Workflow;
import org.geworkbench.components.genspace.entity.WorkflowTool;
import org.geworkbench.components.genspace.server.mahout.GenspaceItemRecommender;
import org.geworkbench.components.genspace.server.mahout.GenspaceItemSimilarity;
import org.geworkbench.components.genspace.server.mahout.GenspaceMySQLJDBCDataModel;
import org.geworkbench.components.genspace.server.mahout.GenspaceSimilarWorkflowRecommender;
import org.geworkbench.components.genspace.server.mahout.GenspaceUserSimilarity;
import org.geworkbench.components.genspace.server.mahout.MahoutSingleton;
import org.geworkbench.components.genspace.server.stubs.GetAllWorkflowsIncluding;

/**
 * Session Bean implementation class MahoutRecommender
 */
//@WebService
@Stateless
public class MahoutRecommender implements MahoutRecommenderLocal {
	@EJB MahoutSingleton mahoutSingleton;

	@Override
	public void refresh() {
		mahoutSingleton.refresh();
	}

	@Override
	public List<Workflow> getToolSuggestions(int userId, int filterMethod) {
		return mahoutSingleton.getToolsSuggestionsForUser(userId, filterMethod);
	}

	@Override
	public List<TasteUser> getUserSuggestions(int userId, int filterMethod) {
		return mahoutSingleton.getUserSuggestions(userId, filterMethod);
	}

	@Override
	public List<Workflow> getSimilarWorkflows(List<Tool> tools) {
		return mahoutSingleton.getSimilarWorkflows(tools);
	}

	@Override
	public List<Workflow> getSimilarUserWorkflows(int userId, int filterMethod) {
		return mahoutSingleton.getSimilarUserWorkflows(userId, filterMethod);
	}

	@Override
	public List<Workflow> getToolsSuggestionsForUser(int userId,
			int filterMethod) {
		return mahoutSingleton.getToolsSuggestionsForUser(userId, filterMethod);
	}

}
