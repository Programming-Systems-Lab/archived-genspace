<?php



/**
 * Skeleton subclass for performing query and update operations on the 'TOOLCOMMENT' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    propel.generator.lib.model
 */
class ToolcommentsPeer extends BaseToolcommentsPeer {
	public static function getToolComments($user, $toolId){

                $c = new Criteria();

                $c->add(ToolCommentsPeer::TOOL_ID, $toolId);

                $c->addDescendingOrderByColumn('CREATEDAT');

                return ToolCommentsPeer::doSelect($c);

        }
} // ToolcommentsPeer
