<?php



/**
 * This class defines the structure of the 'TOOLRATING' table.
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
class ToolratingsTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.ToolratingsTableMap';

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
		$this->setName('TOOLRATING');
		$this->setPhpName('Toolratings');
		$this->setClassname('Toolratings');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('CREATEDAT', 'Createdat', 'TIMESTAMP', false, null, null);
		$this->addColumn('RATING', 'Rating', 'INTEGER', false, 11, null);
		$this->addColumn('TOOL_ID', 'ToolId', 'INTEGER', false, 11, null);
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

} // ToolratingsTableMap
