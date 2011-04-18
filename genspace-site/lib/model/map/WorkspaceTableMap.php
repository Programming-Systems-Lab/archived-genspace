<?php



/**
 * This class defines the structure of the 'workspace' table.
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
class WorkspaceTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.WorkspaceTableMap';

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
		$this->setName('workspace');
		$this->setPhpName('Workspace');
		$this->setClassname('Workspace');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('TITLE', 'Title', 'VARCHAR', false, 200, null);
		$this->addForeignKey('CREATOR', 'Creator', 'INTEGER', 'registration', 'ID', false, 11, null);
		$this->addColumn('DESCRIPTION', 'Description', 'VARCHAR', false, 1000, null);
		$this->addColumn('LOCATION', 'Location', 'VARCHAR', false, 1000, null);
		$this->addColumn('CREATEDAT', 'Createdat', 'TIMESTAMP', true, null, 'CURRENT_TIMESTAMP');
		$this->addColumn('VERSION', 'Version', 'INTEGER', false, 11, null);
		$this->addColumn('LASTSYNC', 'Lastsync', 'TIMESTAMP', true, null, '0000-00-00 00:00:00');
		$this->addColumn('LOCKED', 'Locked', 'TINYINT', false, 1, null);
		$this->addColumn('LASTLOCKEDUSER', 'Lastlockeduser', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('Registration', 'Registration', RelationMap::MANY_TO_ONE, array('creator' => 'ID', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('Annotation', 'Annotation', RelationMap::ONE_TO_MANY, array('id' => 'wspid', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('History', 'History', RelationMap::ONE_TO_MANY, array('id' => 'wspid', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('WorkspaceUser', 'WorkspaceUser', RelationMap::ONE_TO_MANY, array('id' => 'wspid', ), 'CASCADE', 'RESTRICT');
	} // buildRelations()

} // WorkspaceTableMap
