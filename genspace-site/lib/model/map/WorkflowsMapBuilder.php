<?php



class WorkflowsMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.WorkflowsMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('workflows');
		$tMap->setPhpName('Workflows');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('PARENT', 'Parent', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('TOOL', 'Tool', 'string', CreoleTypes::CHAR, true, 200);

	} 
} 