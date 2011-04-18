<?php



/**
 * This class defines the structure of the 'TRANSACTION' table.
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
class TransactionTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.TransactionTableMap';

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
		$this->setName('TRANSACTION');
		$this->setPhpName('Transaction');
		$this->setClassname('Transaction');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('DATASETNAME', 'Datasetname', 'VARCHAR', false, 255, null);
		$this->addColumn('CLIENTID', 'Clientid', 'VARCHAR', false, 255, null);
		$this->addColumn('HOSTNAME', 'Hostname', 'VARCHAR', false, 255, null);
		$this->addColumn('DATE', 'Date', 'TIMESTAMP', false, null, null);
		$this->addColumn('USER_ID', 'UserId', 'INTEGER', false, 11, null);
		$this->addColumn('WORKFLOW_ID', 'WorkflowId', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // TransactionTableMap
