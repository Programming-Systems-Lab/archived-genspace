<?php



class AuditMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.AuditMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('audit');
		$tMap->setPhpName('Audit');

		$tMap->setUseIdGenerator(true);

		$tMap->addForeignKey('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addColumn('ACTION', 'Action', 'string', CreoleTypes::VARCHAR, true, 500);

		$tMap->addColumn('TABLENAME', 'Tablename', 'string', CreoleTypes::VARCHAR, true, 50);

		$tMap->addColumn('BEFOREVALUE', 'Beforevalue', 'string', CreoleTypes::VARCHAR, true, 1000);

		$tMap->addColumn('AFTERVALUE', 'Aftervalue', 'string', CreoleTypes::VARCHAR, true, 1000);

		$tMap->addColumn('TIME', 'Time', 'int', CreoleTypes::TIMESTAMP, true, null);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 