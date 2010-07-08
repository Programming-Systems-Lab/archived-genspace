<?php

/**
 * Subclass for performing query and update operations on the 'tool_ratings' table.
 *
 * 
 *
 * @package lib.model
 */ 
class ToolRatingsPeer extends BaseToolRatingsPeer
{
	
		public static function getToolRating($user, $toolId){
			$connection = Propel::getConnection();
			
			$statement = 
				$connection->prepareStatement("SELECT CAST(AVG(rating) as Float) as avg FROM tool_ratings WHERE id = " . $toolId);
				
			$resultSet = $statement->executeQuery();
			$resultSet->next();
			
			$avgRating = $resultSet->getFloat("avg");
			
			$statement = 
				$connection->prepareStatement("SELECT COUNT(*) as count FROM tool_ratings WHERE id = " . $toolId);
				
			$resultSet = $statement->executeQuery();
			$resultSet->next();
			
			$total = $resultSet->getInt("count");
			
			if ($user){
				$c = new Criteria();
				$c->add(ToolRatingsPeer::ID, $toolId);
				$c->add(ToolRatingsPeer::USERNAME, $user);
				$user_rating = ToolRatingsPeer::doSelect($c);
			}
		
			if ($user_rating) $user_rating = $user_rating[0]->getRating();
			else $user_rating = 0;
			
			return array("user" => $user_rating, "overall" => $avgRating, "total" => $total);
			
	}
	
}
