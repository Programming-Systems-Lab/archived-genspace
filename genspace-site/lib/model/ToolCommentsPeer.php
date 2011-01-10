<?php

require 'lib/model/om/BaseToolCommentsPeer.php';


/**
 * Skeleton subclass for performing query and update operations on the 'tool_comments' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    lib.model
 */
class ToolCommentsPeer extends BaseToolCommentsPeer {
		public static function getToolComments($user, $toolId){
		$c = new Criteria();
		$c->add(ToolCommentsPeer::ID, $toolId);
		$c->addDescendingOrderByColumn('posted_on');
		return ToolCommentsPeer::doSelect($c);
	}
	

} // ToolCommentsPeer
