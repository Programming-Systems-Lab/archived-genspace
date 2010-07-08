<?php



class NetworkVisibilityMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.NetworkVisibilityMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('network_visibility');
		$tMap->setPhpName('NetworkVisibility');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addForeignKey('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addColumn('USER_DATA_OPTION', 'UserDataOption', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('NETWORKNAME', 'Networkname', 'string', CreoleTypes::VARCHAR, true, 50);

	} 
} 