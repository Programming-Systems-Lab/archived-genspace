<?php

/**
 * Subclass for performing query and update operations on the 'workflow_comments' table.
 *
 * 
 *
 * @package lib.model
 */ 
class WorkflowCommentsPeer extends BaseWorkflowCommentsPeer
{
	public static function getWorkflowComments($user, $workflowId){
		$c = new Criteria();
		$c->add(WorkflowCommentsPeer::ID, $workflowId);
		$c->addDescendingOrderByColumn('posted_on');
		return WorkflowCommentsPeer::doSelect($c);
	}
}
