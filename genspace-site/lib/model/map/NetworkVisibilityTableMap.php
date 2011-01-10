<?php



/**
 * This class defines the structure of the 'network_visibility' table.
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
class NetworkVisibilityTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.NetworkVisibilityTableMap';

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
		$this->setName('network_visibility');
		$this->setPhpName('NetworkVisibility');
		$this->setClassname('NetworkVisibility');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, null, null);
		$this->addForeignKey('USERNAME', 'Username', 'VARCHAR', 'registration', 'USERNAME', true, 50, null);
		$this->addColumn('USER_DATA_OPTION', 'UserDataOption', 'INTEGER', true, null, null);
		$this->addColumn('NETWORKNAME', 'Networkname', 'VARCHAR', true, 50, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('Registration', 'Registration', RelationMap::MANY_TO_ONE, array('username' => 'username', ), null, null);
	} // buildRelations()

} // NetworkVisibilityTableMap
