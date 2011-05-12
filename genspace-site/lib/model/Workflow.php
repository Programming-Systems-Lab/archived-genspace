<?php



/**
 * Skeleton subclass for representing a row from the 'WORKFLOW' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    propel.generator.lib.model
 */
class Workflow extends BaseWorkflow {
	public function getName()
	{
		$c = new Criteria();
		$c->addJoin(ToolPeer::ID,WorkflowToolPeer::TOOL_ID);
		$c->add(WorkflowToolPeer::WORKFLOW_ID,$this->getId());

		$r = array();
		foreach(ToolPeer::doSelect($c) as $tool)
		{
			$r[] = $tool->getName();
		}

		return implode(", ",$r);
	}

} // Workflow
