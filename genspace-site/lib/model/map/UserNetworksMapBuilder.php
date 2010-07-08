<?php



class UserNetworksMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.UserNetworksMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('user_networks');
		$tMap->setPhpName('UserNetworks');

		$tMap->setUseIdGenerator(false);

		$tMap->addForeignPrimaryKey('USERNAME', 'Username', 'string' , CreoleTypes::VARCHAR, 'user_networks', 'NETWORK', true, 50);

		$tMap->addForeignPrimaryKey('NETWORK', 'Network', 'string' , CreoleTypes::VARCHAR, 'user_networks', 'NETWORK', true, 50);

	} 
} 