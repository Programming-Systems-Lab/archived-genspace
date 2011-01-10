<?php

require 'lib/model/om/BaseToolRatingsPeer.php';


/**
 * Skeleton subclass for performing query and update operations on the 'tool_ratings' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    lib.model
 */
class ToolRatingsPeer extends BaseToolRatingsPeer {

	public static function getAvgRating($user, $toolId){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT AVG(rating) as AVG FROM tool_ratings WHERE id=:id");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$avgRating = $resultSet->AVG;
			return $avgRating;	
	}
	public static function checkUser($user, $toolId){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT COUNT(*) AS COUNT FROM tool_ratings WHERE id=:id and username=:user ");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             	$statement->bindValue(':user', $user, PDO::PARAM_STR);
           
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$count = $resultSet->COUNT;
			return $count;
	}
	
	public static function getSearchResultsTool($tool){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT tool FROM tools WHERE tool COLLATE SQL_Latin1_General_CP1_CI_AS LIKE :tool");
		
				$tool="%$tool%";
				$statement->bindValue(':tool', $tool, PDO::PARAM_STR);
          
			$statement->execute();
			
		$tool=array();
						while ($resultSet=$statement->fetch(PDO::FETCH_OBJ)){
						$tool[] = $resultSet->tool;
							}
			return $tool;
		}
		public static function getSearchResultsTotal($tool){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT Count(*) AS COUNT FROM tools WHERE tool COLLATE SQL_Latin1_General_CP1_CI_AS LIKE :tool");
			$tool="%$tool%";
					$statement->bindValue(':tool', $tool, PDO::PARAM_STR);
          
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$total = $resultSet->COUNT;
			return $total;
		}
		public static function getSearchResultsId($tool){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT id FROM tools WHERE tool COLLATE SQL_Latin1_General_CP1_CI_AS LIKE :tool");
		
				$tool="%$tool%";
				$statement->bindValue(':tool', $tool, PDO::PARAM_STR);
          
			$statement->execute();
		
	       $id=array();
		   					while ($resultSet=$statement->fetch(PDO::FETCH_OBJ)){
						$id[] = $resultSet->id;
						}
			return $id;
		}
	
	
	public static function getUserRating($user, $toolId){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT Count(*) as Count FROM tool_ratings WHERE id=:id AND username=:username");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             $statement->bindValue(':username', $user, PDO::PARAM_STR);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$rating = $resultSet->Count;
			return $rating;	
	}
	public static function getUserToolRating($user, $toolId){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT rating FROM tool_ratings WHERE id=:id AND username=:username");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             $statement->bindValue(':username', $user, PDO::PARAM_STR);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$rating = $resultSet->rating;
			return $rating;	
	}
	public static function getUserToolComment($user, $toolId){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT comment FROM tool_comments WHERE id=:id AND username=:username");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             $statement->bindValue(':username', $user, PDO::PARAM_STR);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$comment = $resultSet->comment;
			return $rating;	
	}
	public static function getUserComment($user, $toolId){
	$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT Count(*) AS count FROM tool_comments WHERE id=:id AND username=:username");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             $statement->bindValue(':username', $user, PDO::PARAM_STR);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$rating = $resultSet->Count;
			return $rating;	
	}
	
	public static function getCountRating($toolId){
			$connection = Propel::getConnection();

		$statement = 
				$connection->prepare("SELECT COUNT(*) as COUNT FROM tool_ratings WHERE id =:id " );
			$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             	
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
		
	    	$total = $resultSet->COUNT;
			
		return $total;
	}
	public static function deleteRating($user, $toolId){
			$connection = Propel::getConnection();

		$statement = 
				$connection->prepare("DELETE  FROM tool_ratings WHERE id =:id AND username=:username " );
			$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
			$statement->bindValue(':username', $user, PDO::PARAM_STR);
			//	$statement->bindValue(':pk', $pk,  PDO::PARAM_INT);
			
             	
			$statement->execute();
		
			
	}
	public static function editRating($user, $toolId, $rating){
			$connection = Propel::getConnection();

		$statement = 
				$connection->prepare("UPDATE tool_ratings SET rating=:rating WHERE id =:id AND username=:username " );
			$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
			$statement->bindValue(':username', $user, PDO::PARAM_STR);
			$statement->bindValue(':rating', $rating, PDO::PARAM_STR);
			
			//	$statement->bindValue(':pk', $pk,  PDO::PARAM_INT);
			
             	
			$statement->execute();
		
			
	}
	public static function editComment($user, $toolId, $comment){
			$connection = Propel::getConnection();

		$statement = 
				$connection->prepare("UPDATE tool_comments SET comment=:comment, posted_on=:posted WHERE id=:id AND username=:username " );
			$dt = new DateTime('@'.time(), new DateTimeZone('UTC'));
					// We have to explicitly specify and then change the time zone because of a
					// DateTime bug: http://bugs.php.net/bug.php?id=43003
			$dt->setTimeZone(new DateTimeZone(date_default_timezone_get()));
				
			$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
			$statement->bindValue(':username', $user, PDO::PARAM_STR);
			$statement->bindValue(':comment', $comment, PDO::PARAM_STR);
			$statement->bindValue(':posted', ($dt ? $dt->format('Y-m-d H:i:s') : null), PDO::PARAM_STR);
			
			
			//	$statement->bindValue(':pk', $pk,  PDO::PARAM_INT);
			
             	
			$statement->execute();
		
			
	}
	public static function deleteComment($user, $toolId){
			$connection = Propel::getConnection();

		$statement = 
				$connection->prepare("DELETE  FROM tool_comments WHERE id =:id and username=:username " );
			$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
			$statement->bindValue(':username', $user, PDO::PARAM_STR);
			//	$statement->bindValue(':pk', $pk,  PDO::PARAM_INT);
			
             	
			$statement->execute();
		
			
	}
	public static function deleteTool($toolid){
			$connection = Propel::getConnection();
$statement = 
				$connection->prepare("DELETE FROM workflow_tool WHERE tool_id=:toolid " );
			$statement->bindValue(':toolid', $toolid, PDO::PARAM_INT);
			//	$statement->bindValue(':pk', $pk,  PDO::PARAM_INT);
			
             	
			$statement->execute();
		
		$statement = 
				$connection->prepare("DELETE FROM tools WHERE id=:toolid " );
			$statement->bindValue(':toolid', $toolid, PDO::PARAM_INT);
			//	$statement->bindValue(':pk', $pk,  PDO::PARAM_INT);
			
             	
			$statement->execute();
		
			
	}
	public static function deleteAllRatings(){
			$connection = Propel::getConnection();

		$statement = 
				$connection->prepare("DELETE FROM tool_ratings " );
			
             	
			$statement->execute();
		
			
	}
	public static function deleteAllComments(){
			$connection = Propel::getConnection();

		$statement = 
			$connection->prepare("DELETE  FROM tool_comments " );
			
             	
			$statement->execute();
		
			
	}
		public static function getUserRatings($user){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT id, rating FROM tool_ratings WHERE username=:user");
				$statement->bindValue(':user', $user, PDO::PARAM_STR);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$tool = $resultSet->id;
						$rating=$resultSet->rating;
		
			return array("tool" => $tool, "rating" => $rating);
			
	}
	public static function getUserTools($user){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT id FROM tool_ratings WHERE username=:user");
				$statement->bindValue(':user', $user, PDO::PARAM_STR);
             
			$statement->execute();
		$i=0;
						while ($resultSet=$statement->fetch(PDO::FETCH_OBJ)){
						$tool[$i] = $resultSet->id;
						$i++;
						}
			return $tool;
			
	}
	public static function getUserAllRatings($user){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT rating FROM tool_ratings WHERE username=:user");
				$statement->bindValue(':user', $user, PDO::PARAM_STR);
             
			$i=0;
			$statement->execute();
		
						while(	$resultSet=$statement->fetch(PDO::FETCH_OBJ)){
            	    $numRating[$i] = $resultSet->rating;
				$i++;
		
	        }
		
			return $numRating;
			
	}
	public static function getUserCountRatings($user){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT COUNT(*) AS COUNT FROM tool_ratings WHERE username=:user");
				$statement->bindValue(':user', $user, PDO::PARAM_STR);
             
			$statement->execute();
		
							$resultSet=$statement->fetch(PDO::FETCH_OBJ);
            	    $total = $resultSet->COUNT;
		
					return $total;
			
	}
		
		public static function getAllComments(){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT tool_comments.comment  FROM tool_comments");
		     
			$statement->execute();
		
					$i=0;
					while($row=$statement->fetch(PDO::FETCH_NUM)){
            	     $comments[$i] = $row[0];
					 $i++;
					}
  return $comments;
					
	}
	
	public static function getAllToolNames(){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT tools.tool AS tool FROM tool_comments, tools WHERE tool_comments.id=tools.id");
		     
			$statement->execute();
		
					$i=0;
					while($row=$statement->fetch(PDO::FETCH_NUM)){
            	     $tools[$i] = $row[0];
					 $i++;
					}
  return $tools;
  
  
					
	}
		
		public static function getAllDates(){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT posted_on FROM tool_comments");
		     
			$statement->execute();
		
					$i=0;
					while($row=$statement->fetch(PDO::FETCH_OBJ)){
            	     $dates[$i] = $row->posted_on;
					 $i++;
					}
  return $dates;
					
	}
	public static function getAllRatings(){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT rating FROM tool_ratings");
		     
			$statement->execute();
		
					$i=0;
					while($row=$statement->fetch(PDO::FETCH_OBJ)){
            	     $ratings[$i] = $row->rating;
					 $i++;
					}
  return $ratings;
					
	}
	public static function getAllIDs(){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT tool_comments.id FROM tool_comments");
		     
			$statement->execute();
		
					$i=0;
					while($row=$statement->fetch(PDO::FETCH_NUM)){
            	     $id[$i] = $row[0];
					 $i++;
					}
  return $id;
					
	}
	public static function getAllPKs(){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT pk FROM tool_comments");
		     
			$statement->execute();
		
					$i=0;
					while($row=$statement->fetch(PDO::FETCH_NUM)){
            	     $pk[$i] = $row[0];
					 $i++;
					}
  return $pk;
					
	}
		
		public static function getUserComments($user, $toolid){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT comment, posted_on FROM tool_comments WHERE username=:user and id=:id");
				$statement->bindValue(':user', $user, PDO::PARAM_STR);
             	$statement->bindValue(':id', $toolid, PDO::PARAM_INT);
             
			$statement->execute();
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$comment=$resultSet->comment;
		                 $posted=$resultSet->posted_on;
			$statement = $connection->prepare("SELECT tool FROM tools WHERE id=:id");
				$statement->bindValue(':id', $toolid, PDO::PARAM_INT);
           				$statement->execute();
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$tool=$resultSet->tool;
		 
					
			return array("tool" => $tool, "comment" => $comment, "posted_on"=>$posted);
			
	}
		public static function getUserRatings2($user, $toolid){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT rating FROM tool_ratings WHERE username=:user and id=:id");
				$statement->bindValue(':user', $user, PDO::PARAM_STR);
             	$statement->bindValue(':id', $toolid, PDO::PARAM_INT);
             
			$statement->execute();
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$rating=$resultSet->rating;
		               					
			return $rating;
			
	}
	
	
	public static function getToolRating($user, $toolId){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT AVG(rating) as AVG FROM tool_ratings WHERE id=:id");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
						$avgRating = $resultSet->AVG;
			  		//}
			$statement = 
				$connection->prepare("SELECT COUNT(*) as count FROM tool_ratings WHERE id =:id " );
			$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             	
			$statement->execute();
		
						$resultSet=$statement->fetch(PDO::FETCH_OBJ);
		
	    	$total = $resultSet->COUNT;
			
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
	
	public static function getRatings( $toolId){
			$connection = Propel::getConnection();
			
			$statement = $connection->prepare("SELECT rating FROM tool_ratings WHERE id=:id");
				$statement->bindValue(':id', $toolId, PDO::PARAM_INT);
             
			 $i=0;
			$statement->execute();
		
					while(	$resultSet=$statement->fetch(PDO::FETCH_OBJ)){
            	    $numRating[$i] = $resultSet->rating;
				$i++;
		
	        }
			return $numRating;
			
	}
	
	
	} // ToolRatingsPeer
