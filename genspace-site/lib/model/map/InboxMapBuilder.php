<?php



class InboxMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.InboxMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('Inbox');
		$tMap->setPhpName('Inbox');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('MESSAGEID', 'Messageid', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('DATE', 'Date', 'int', CreoleTypes::TIMESTAMP, true, null);

		$tMap->addForeignKey('FROMUSER', 'Fromuser', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addForeignKey('TOUSER', 'Touser', 'string', CreoleTypes::VARCHAR, 'registration', 'USERNAME', true, 50);

		$tMap->addColumn('MESSAGE', 'Message', 'string', CreoleTypes::VARCHAR, true, 200);

	} 
} 