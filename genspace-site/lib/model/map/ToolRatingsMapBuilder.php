<?php



class ToolRatingsMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.ToolRatingsMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('tool_ratings');
		$tMap->setPhpName('ToolRatings');

		$tMap->setUseIdGenerator(true);

		$tMap->addPrimaryKey('PK', 'Pk', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('RATING', 'Rating', 'int', CreoleTypes::INTEGER, true, null);

		$tMap->addColumn('USERNAME', 'Username', 'string', CreoleTypes::CHAR, true, 200);

	} 
} 