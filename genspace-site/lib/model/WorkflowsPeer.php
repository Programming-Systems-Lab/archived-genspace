<?php

/**
 * Subclass for performing query and update operations on the 'workflows' table.
 *
 * 
 *
 * @package lib.model
 */ 
class WorkflowsPeer extends BaseWorkflowsPeer
{
	
		public static function getWorkflowName($workflowId){
		$result = "";
		$currentTool = "";
		$counter = 1;
		$first = true;
		do {
			$c = new Criteria();
			$c->add(WorkflowsPeer::ID, $workflowId);
			$workflow = WorkflowsPeer::doSelect($c);
			if ($workflow){
				$toolName = $workflow[0]->getTool();
				
				if ($first) {
					$currentTool = $toolName;
					$first = false;
				}
				else if ($toolName != $currentTool){
					if($counter == 1){
						$result = $currentTool . " --> " . $result;
					}
					else {
						$result = $currentTool . "(" . $counter . ") --> " . $result;
					}
					$currentTool = $toolName;
					$counter = 1;
				}
				else $counter++;
				$workflowId = $workflow[0]->getParent();
			}
		}
		while ($workflow);
		
		if($counter == 1){
			$result = $currentTool . " --> " . $result;
		}
		else {
			$result = $currentTool . "(" . $counter . ") --> " . $result;
		}
		
		
		return trim($result, "-> ");
	}
}
