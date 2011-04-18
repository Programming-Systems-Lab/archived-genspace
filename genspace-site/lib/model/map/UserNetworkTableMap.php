<?php



/**
 * This class defines the structure of the 'User_Network' table.
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
class UserNetworkTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.UserNetworkTableMap';

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
		$this->setName('User_Network');
		$this->setPhpName('UserNetwork');
		$this->setClassname('UserNetwork');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('VISIBLE', 'Visible', 'TINYINT', false, 1, 0);
		$this->addColumn('VERIFIED', 'Verified', 'TINYINT', false, 1, 0);
		$this->addColumn('NETWORK_ID', 'NetworkId', 'INTEGER', false, 11, null);
		$this->addColumn('USER_ID', 'UserId', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // UserNetworkTableMap
