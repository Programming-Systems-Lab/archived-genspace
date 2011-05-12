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
class WorkflowratingPeer extends BaseWorkflowratingPeer {
	public static function getWorkflowRating($user, $workflowId)
	{

		$c = new Criteria();
		$c->add(WorkflowratingPeer::WORKFLOW_ID,$workflowId);
		$c->addGroupByColumn(WorkflowratingPeer::WORKFLOW_ID);
		$c->addSelectColumn("AVG(".WorkflowratingPeer::RATING.") as A");
		$c->addSelectColumn("COUNT(".WorkflowratingPeer::RATING.") as B");

		$c->addSelectColumn(WorkflowRatingPeer::WORKFLOW_ID);

		$rs = WorkflowRatingPeer::doSelectStmt($c);

		$total = 0;
		$avgRating = 0;
		if($rs->rowCount() > 0)
		{
			$d = $rs->fetchAll();

			$avgRating = $d[0]['A'];
			$total = $d[0]['B'];
		}


		if ($user) {

			$c = new Criteria();

			$c->add(WorkflowRatingPeer::WORKFLOW_ID, $workflowId);

			$c->add(WorkflowRatingPeer::CREATOR_ID, $user);

			$user_rating = WorkflowRatingPeer::doSelect($c);

		}


		if ($user_rating) $user_rating = $user_rating[0]->getRating();

		else $user_rating = 0;


		return array("user" => $user_rating, "overall" => $avgRating, "total" => $total);


	}
} // WorkflowratingPeer
