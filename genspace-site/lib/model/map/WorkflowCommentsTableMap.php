<?php



/**
 * This class defines the structure of the 'WORKFLOWCOMMENT' table.
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
class WorkflowcommentsTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.WorkflowcommentsTableMap';

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
		$this->setName('WORKFLOWCOMMENT');
		$this->setPhpName('Workflowcomments');
		$this->setClassname('Workflowcomments');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('CREATEDAT', 'Createdat', 'TIMESTAMP', false, null, null);
		$this->addColumn('COMMENT', 'Comment', 'VARCHAR', false, 255, null);
		$this->addColumn('WORKFLOW_ID', 'WorkflowId', 'INTEGER', false, 11, null);
		$this->addForeignKey('CREATOR_ID', 'CreatorId', 'INTEGER', 'registration', 'ID', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('Registration', 'Registration', RelationMap::MANY_TO_ONE, array('CREATOR_ID' => 'ID', ), null, null);
	} // buildRelations()

} // WorkflowcommentsTableMap
