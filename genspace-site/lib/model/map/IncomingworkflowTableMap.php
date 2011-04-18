<?php



/**
 * This class defines the structure of the 'INCOMINGWORKFLOW' table.
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
class IncomingworkflowTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.IncomingworkflowTableMap';

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
		$this->setName('INCOMINGWORKFLOW');
		$this->setPhpName('Incomingworkflow');
		$this->setClassname('Incomingworkflow');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('CREATEDAT', 'Createdat', 'TIMESTAMP', false, null, null);
		$this->addColumn('NAME', 'Name', 'VARCHAR', false, 255, null);
		$this->addColumn('SENDER_ID', 'SenderId', 'INTEGER', false, 11, null);
		$this->addColumn('WORKFLOW_ID', 'WorkflowId', 'INTEGER', false, 11, null);
		$this->addColumn('RECEIVER_ID', 'ReceiverId', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // IncomingworkflowTableMap
