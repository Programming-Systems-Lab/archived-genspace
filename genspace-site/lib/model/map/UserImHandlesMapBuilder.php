<?php



class UserImHandlesMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.UserImHandlesMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('user_IM_handles');
		$tMap->setPhpName('UserImHandles');

		$tMap->setUseIdGenerator(true);

		$tMap->addColumn('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('IM_HANDLE', 'ImHandle', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('IM_SERVICE', 'ImService', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 