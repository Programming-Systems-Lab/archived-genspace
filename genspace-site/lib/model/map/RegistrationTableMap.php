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
		$this->setUseIdGenerator(false);
		// columns
		$this->addPrimaryKey('USERNAME', 'Username', 'VARCHAR', true, 50, null);
		$this->addColumn('PASSWORD', 'Password', 'VARCHAR', true, 50, null);
		$this->addColumn('EMAIL', 'Email', 'VARCHAR', false, 50, null);
		$this->addColumn('IM_EMAIL', 'ImEmail', 'VARCHAR', false, 50, null);
		$this->addColumn('IM_PASSWORD', 'ImPassword', 'VARCHAR', false, 50, null);
		$this->addColumn('FIRST_NAME', 'FirstName', 'VARCHAR', true, 50, null);
		$this->addColumn('LAST_NAME', 'LastName', 'VARCHAR', true, 50, null);
		$this->addColumn('WORK_TITLE', 'WorkTitle', 'VARCHAR', false, 50, null);
		$this->addColumn('PHONE', 'Phone', 'VARCHAR', false, 50, null);
		$this->addColumn('LAB_AFFILIATION', 'LabAffiliation', 'VARCHAR', true, 100, null);
		$this->addColumn('ADDR1', 'Addr1', 'VARCHAR', false, 50, null);
		$this->addColumn('ADDR2', 'Addr2', 'VARCHAR', false, 50, null);
		$this->addColumn('CITY', 'City', 'VARCHAR', false, 50, null);
		$this->addColumn('STATE', 'State', 'VARCHAR', false, 50, null);
		$this->addColumn('ZIPCODE', 'Zipcode', 'VARCHAR', false, 5, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('DataVisibility', 'DataVisibility', RelationMap::ONE_TO_ONE, array('username' => 'username', ), null, null);
    $this->addRelation('UserVisibility', 'UserVisibility', RelationMap::ONE_TO_MANY, array('username' => 'username', ), null, null);
    $this->addRelation('Audit', 'Audit', RelationMap::ONE_TO_MANY, array('username' => 'username', ), null, null);
    $this->addRelation('NetworkVisibility', 'NetworkVisibility', RelationMap::ONE_TO_MANY, array('username' => 'username', ), null, null);
    $this->addRelation('OutboxRelatedByFromuser', 'Outbox', RelationMap::ONE_TO_MANY, array('username' => 'FromUser', ), null, null);
    $this->addRelation('OutboxRelatedByTouser', 'Outbox', RelationMap::ONE_TO_MANY, array('username' => 'ToUser', ), null, null);
    $this->addRelation('InboxRelatedByFromuser', 'Inbox', RelationMap::ONE_TO_MANY, array('username' => 'FromUser', ), null, null);
    $this->addRelation('InboxRelatedByTouser', 'Inbox', RelationMap::ONE_TO_MANY, array('username' => 'ToUser', ), null, null);
	} // buildRelations()

} // RegistrationTableMap
