<?php



/**
 * This class defines the structure of the 'networks' table.
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
class NetworksTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.NetworksTableMap';

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
		$this->setName('networks');
		$this->setPhpName('Networks');
		$this->setClassname('Networks');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, null, null);
		$this->addColumn('NETWORK', 'Network', 'VARCHAR', true, 50, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('UserNetworksRelatedByUsername', 'UserNetworks', RelationMap::ONE_TO_MANY, array('id' => 'username', ), null, null);
    $this->addRelation('UserNetworksRelatedByNetwork', 'UserNetworks', RelationMap::ONE_TO_MANY, array('network' => 'network', ), null, null);
	} // buildRelations()

} // NetworksTableMap
