<?php



/**
 * This class defines the structure of the 'test_analysis_events' table.
 *
 *
 *
 * This map class is used by Propel to do runtime db structure discovery.
 * For example, the createSelectSql() method checks the type of a given column used in an
 * ORDER BY clause to know whether it needs to apply SQL to make the ORDER BY case-insensitive
 * (i.e. if it's a text column type).
 *
 * @package    propel.generator.lib.model.map
 */
class TestAnalysisEventsTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.TestAnalysisEventsTableMap';

	/**
	 * Initialize the table attributes, columns and validators
	 * Relations are not initialized by this method since they are lazy loaded
	 *
	 * @return     void
	 * @throws     PropelException
	 */
	public function initialize()
	{
	  // attributes
		$this->setName('test_analysis_events');
		$this->setPhpName('TestAnalysisEvents');
		$this->setClassname('TestAnalysisEvents');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addColumn('USERNAME', 'Username', 'VARCHAR', false, 50, null);
		$this->addColumn('HOST', 'Host', 'VARCHAR', false, 50, null);
		$this->addColumn('DATE', 'Date', 'TIMESTAMP', false, null, null);
		$this->addColumn('ANALYSIS', 'Analysis', 'VARCHAR', false, 50, null);
		$this->addColumn('DATASET', 'Dataset', 'VARCHAR', false, 255, null);
		$this->addColumn('TRANSACTION_ID', 'TransactionId', 'VARCHAR', false, 255, null);
		$this->addColumn('IS_GENSPACE_USER', 'IsGenspaceUser', 'VARCHAR', false, 1, null);
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, null, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // TestAnalysisEventsTableMap
