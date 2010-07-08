<?php



class UserVisibilityMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.UserVisibilityMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('user_visibility');
		$tMap->setPhpName('UserVisibility');

		$tMap->setUseIdGenerator(true);

		$tMap->addForeignKey('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addColumn('USERVISIBILITY', 'Uservisibility', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 