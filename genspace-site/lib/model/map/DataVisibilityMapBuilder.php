<?php



class DataVisibilityMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.DataVisibilityMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('data_visibility');
		$tMap->setPhpName('DataVisibility');

		$tMap->setUseIdGenerator(false);

		$tMap->addForeignPrimaryKey('USERNAME', 'Username', 'string' , CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addColumn('LOGDATA', 'Logdata', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('DATAVISIBILITY', 'Datavisibility', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 