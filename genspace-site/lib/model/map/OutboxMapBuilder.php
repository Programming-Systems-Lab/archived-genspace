<?php



class OutboxMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.OutboxMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('Outbox');
		$tMap->setPhpName('Outbox');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('MESSAGEID', 'Messageid', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('DATE', 'Date', 'int', CreoleTypes::TIMESTAMP, true, null);

		$tMap->addForeignKey('FROMUSER', 'Fromuser', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addForeignKey('TOUSER', 'Touser', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addColumn('MESSAGE', 'Message', 'string', CreoleTypes::VARCHAR, true, 200);

	} 
} 