<?php



class WorkflowRatingsMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.WorkflowRatingsMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('workflow_ratings');
		$tMap->setPhpName('WorkflowRatings');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('PK', 'Pk', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, true, 100);

		$tMap->addColumn('RATING', 'Rating', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 