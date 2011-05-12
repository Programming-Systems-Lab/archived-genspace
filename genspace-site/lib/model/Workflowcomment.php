<?php



/**
 * Skeleton subclass for representing a row from the 'WORKFLOWCOMMENT' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    propel.generator.lib.model
 */
class Workflowcomment extends BaseWorkflowcomment {
	public function getCreator()
	{
		return RegistrationPeer::retrieveByPK($this->getCreatorId());
	}

} // Workflowcomment
