<?php



/**
 * This class defines the structure of the 'tool_comments' table.
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
class ToolCommentsTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.ToolCommentsTableMap';

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
		$this->setName('tool_comments');
		$this->setPhpName('ToolComments');
		$this->setClassname('ToolComments');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('PK', 'Pk', 'INTEGER', true, null, null);
		$this->addColumn('ID', 'Id', 'INTEGER', true, null, null);
		$this->addColumn('COMMENT', 'Comment', 'LONGVARCHAR', true, 2147483647, null);
		$this->addColumn('USERNAME', 'Username', 'CHAR', true, 200, null);
		$this->addColumn('POSTED_ON', 'PostedOn', 'TIMESTAMP', true, null, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // ToolCommentsTableMap
