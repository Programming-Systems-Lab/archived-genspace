<?php



/**
 * Skeleton subclass for performing query and update operations on the 'WORKFLOWRATING' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    propel.generator.lib.model
 */
class WorkflowratingsPeer extends BaseWorkflowratingsPeer {
 public static function getWorkflowRating($user, $workflowId){

                        $connection = Propel::getConnection();


                        $statement = $connection->prepare("SELECT CAST(AVG(rating) as Float) as avg FROM WORKFLOWRATING WHERE workflow_id = " . $workflowId);



                        $resultSet = $statement->execute();

                        $resultSet->next();



                        $avgRating = $resultSet->getFloat("avg");



                        $statement =

                                $connection->prepare("SELECT COUNT(*) as count FROM workflow_ratings WHERE id = " . $workflowId);



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
} // WorkflowratingsPeer
