<?php

/**
 * Subclass for performing query and update operations on the 'workflow_ratings' table.
 *
 * 
 *
 * @package lib.model
 */ 
class WorkflowRatingsPeer extends BaseWorkflowRatingsPeer
{
	
	public static function getWorkflowRating($user, $workflowId){
			$connection = Propel::getConnection();
			
			$statement = 
				$connection->prepareStatement("SELECT CAST(AVG(rating) as Float) as avg FROM workflow_ratings WHERE id = " . $workflowId);
				
			$resultSet = $statement->executeQuery();
			$resultSet->next();
			
			$avgRating = $resultSet->getFloat("avg");
			
			$statement = 
				$connection->prepareStatement("SELECT COUNT(*) as count FROM workflow_ratings WHERE id = " . $workflowId);
				
			$resultSet = $statement->executeQuery();
			$resultSet->next();
			
			$total = $resultSet->getInt("count");
			
			if ($user){
				$c = new Criteria();
				$c->add(WorkflowRatingsPeer::ID, $workflowId);
				$c->add(WorkflowRatingsPeer::USERNAME, $user);
				$user_rating = WorkflowRatingsPeer::doSelect($c);
			}
		
			if ($user_rating) $user_rating = $user_rating[0]->getRating();
			else $user_rating = 0;
			
			return array("user" => $user_rating, "overall" => $avgRating, "total" => $total);
			
	}
	
}
