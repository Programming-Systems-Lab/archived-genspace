<?php



class RegistrationMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.RegistrationMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('registration');
		$tMap->setPhpName('Registration');

		$tMap->setUseIdGenerator(false);

		$tMap->addPrimaryKey('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, true, 50);

		$tMap->addColumn('PASSWORD', 'Password', 'string', CreoleTypes::VARCHAR, true, 50);

		$tMap->addColumn('EMAIL', 'Email', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('IM_EMAIL', 'ImEmail', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('IM_PASSWORD', 'ImPassword', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('FIRST_NAME', 'FirstName', 'string', CreoleTypes::VARCHAR, true, 50);

		$tMap->addColumn('LAST_NAME', 'LastName', 'string', CreoleTypes::VARCHAR, true, 50);

		$tMap->addColumn('WORK_TITLE', 'WorkTitle', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('PHONE', 'Phone', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('LAB_AFFILIATION', 'LabAffiliation', 'string', CreoleTypes::VARCHAR, true, 100);

		$tMap->addColumn('ADDR1', 'Addr1', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('ADDR2', 'Addr2', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('CITY', 'City', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('STATE', 'State', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('ZIPCODE', 'Zipcode', 'string', CreoleTypes::VARCHAR, false, 5);

	} 
} 