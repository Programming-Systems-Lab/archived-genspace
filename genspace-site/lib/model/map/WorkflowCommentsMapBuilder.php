<?php



class WorkflowCommentsMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.WorkflowCommentsMapBuilder';

	
	private $dbMap;

	
	public function isBuilt()
	{
		return ($this->dbMap !== null);
	}

	
	public function getDatabaseMap()
	{
		return $this->dbMap;
	}

	
	public function doBuild()
	{
		$this->dbMap = Propel::getDatabaseMap('propel');

		$tMap = $this->dbMap->addTable('workflow_comments');
		$tMap->setPhpName('WorkflowComments');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('PK', 'Pk', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('COMMENT', 'Comment', 'string', CreoleTypes::LONGVARCHAR, true, 2147483647);

		$tMap->addColumn('USERNAME', 'Username', 'string', CreoleTypes::CHAR, true, 200);

		$tMap->addColumn('POSTED_ON', 'PostedOn', 'int', CreoleTypes::TIMESTAMP, true, null);

	} 
} 