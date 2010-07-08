<?php

/**
 * Subclass for performing query and update operations on the 'tool_comments' table.
 *
 * 
 *
 * @package lib.model
 */ 
class ToolCommentsPeer extends BaseToolCommentsPeer
{
	public static function getToolComments($user, $toolId){
		$c = new Criteria();
		$c->add(ToolCommentsPeer::ID, $toolId);
		$c->addDescendingOrderByColumn('posted_on');
		return ToolCommentsPeer::doSelect($c);
	}
}
