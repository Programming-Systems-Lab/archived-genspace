<?php



class FriendsMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.FriendsMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('friends');
		$tMap->setPhpName('Friends');

		$tMap->setUseIdGenerator(true);

		$tMap->addColumn('USER1', 'User1', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('USER2', 'User2', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 