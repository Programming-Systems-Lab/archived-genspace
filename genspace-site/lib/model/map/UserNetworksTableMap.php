<?php



/**
 * This class defines the structure of the 'user_networks' table.
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
class UserNetworksTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.UserNetworksTableMap';

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
		$this->setName('user_networks');
		$this->setPhpName('UserNetworks');
		$this->setClassname('UserNetworks');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(false);
		// columns
		$this->addForeignPrimaryKey('USERNAME', 'Username', 'VARCHAR' , 'networks', 'ID', true, 50, null);
		$this->addForeignPrimaryKey('NETWORK', 'Network', 'VARCHAR' , 'networks', 'NETWORK', true, 50, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('NetworksRelatedByUsername', 'Networks', RelationMap::MANY_TO_ONE, array('username' => 'id', ), null, null);
    $this->addRelation('NetworksRelatedByNetwork', 'Networks', RelationMap::MANY_TO_ONE, array('network' => 'network', ), null, null);
	} // buildRelations()

} // UserNetworksTableMap
