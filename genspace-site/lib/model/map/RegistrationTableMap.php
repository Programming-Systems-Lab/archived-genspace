<?php



/**
 * This class defines the structure of the 'registration' table.
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
class RegistrationTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.RegistrationTableMap';

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
		$this->setName('registration');
		$this->setPhpName('Registration');
		$this->setClassname('Registration');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('PHONE', 'Phone', 'VARCHAR', false, 50, null);
		$this->addColumn('INTERESTS', 'Interests', 'LONGVARCHAR', false, null, null);
		$this->addColumn('STATE', 'State', 'VARCHAR', false, 50, null);
		$this->addColumn('ONLINE_STATUS', 'OnlineStatus', 'INTEGER', false, 11, null);
		$this->addColumn('PASSWORD', 'Password', 'VARCHAR', true, 50, null);
		$this->addColumn('CITY', 'City', 'VARCHAR', false, 50, null);
		$this->addColumn('USERNAME', 'Username', 'VARCHAR', true, 50, null);
		$this->addColumn('CREATEDAT', 'Createdat', 'TIMESTAMP', false, null, null);
		$this->addColumn('FIRST_NAME', 'FirstName', 'VARCHAR', true, 50, null);
		$this->addColumn('DATAVISIBILITY', 'Datavisibility', 'INTEGER', false, 11, null);
		$this->addColumn('WORK_TITLE', 'WorkTitle', 'VARCHAR', false, 50, null);
		$this->addColumn('LAST_NAME', 'LastName', 'VARCHAR', true, 50, null);
		$this->addColumn('ZIPCODE', 'Zipcode', 'VARCHAR', false, 5, null);
		$this->addColumn('LAB_AFFILIATION', 'LabAffiliation', 'VARCHAR', false, 100, null);
		$this->addColumn('ADDR1', 'Addr1', 'VARCHAR', false, 50, null);
		$this->addColumn('ADDR2', 'Addr2', 'VARCHAR', false, 50, null);
		$this->addColumn('EMAIL', 'Email', 'VARCHAR', false, 50, null);
		$this->addColumn('LOGDATA', 'Logdata', 'INTEGER', false, 11, null);
		$this->addColumn('ROOTFOLDER_ID', 'RootfolderId', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('Toolcomments', 'Toolcomments', RelationMap::ONE_TO_MANY, array('ID' => 'CREATOR_ID', ), null, null);
    $this->addRelation('Toolratings', 'Toolratings', RelationMap::ONE_TO_MANY, array('ID' => 'CREATOR_ID', ), null, null);
    $this->addRelation('Workflowcomments', 'Workflowcomments', RelationMap::ONE_TO_MANY, array('ID' => 'CREATOR_ID', ), null, null);
    $this->addRelation('Workflowratings', 'Workflowratings', RelationMap::ONE_TO_MANY, array('ID' => 'CREATOR_ID', ), null, null);
    $this->addRelation('Annotation', 'Annotation', RelationMap::ONE_TO_MANY, array('ID' => 'creator', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('History', 'History', RelationMap::ONE_TO_MANY, array('ID' => 'uid', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('Workspace', 'Workspace', RelationMap::ONE_TO_MANY, array('ID' => 'creator', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('WorkspaceUser', 'WorkspaceUser', RelationMap::ONE_TO_MANY, array('ID' => 'uid', ), 'CASCADE', 'RESTRICT');
	} // buildRelations()

} // RegistrationTableMap
