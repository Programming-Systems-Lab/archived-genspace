<?php



class TestAnalysisEventsMapBuilder {

	
	const CLASS_NAME = 'lib.model.map.TestAnalysisEventsMapBuilder';

	
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

		$tMap = $this->dbMap->addTable('test_analysis_events');
		$tMap->setPhpName('TestAnalysisEvents');

		$tMap->setUseIdGenerator(true);

		$tMap->addColumn('USERNAME', 'Username', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('HOST', 'Host', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('DATE', 'Date', 'int', CreoleTypes::TIMESTAMP, false, null);

		$tMap->addColumn('ANALYSIS', 'Analysis', 'string', CreoleTypes::VARCHAR, false, 50);

		$tMap->addColumn('DATASET', 'Dataset', 'string', CreoleTypes::VARCHAR, false, 255);

		$tMap->addColumn('TRANSACTION_ID', 'TransactionId', 'string', CreoleTypes::VARCHAR, false, 255);

		$tMap->addColumn('IS_GENSPACE_USER', 'IsGenspaceUser', 'string', CreoleTypes::VARCHAR, false, 1);

		$tMap->addPrimaryKey('ID', 'Id', 'int', CreoleTypes::INTEGER, true, null);

	} 
} 