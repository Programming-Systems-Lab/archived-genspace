<?php



class IncludeExcludeAnalysisMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.IncludeExcludeAnalysisMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('include_exclude_analysis');
		$tMap->setPhpName('IncludeExcludeAnalysis');

		$tMap->setUseIdGenerator(true);

		$tMap->addColumn('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('ANALYSIS', 'Analysis', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('ACTION', 'Action', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 