<?php



/**
 * This class defines the structure of the 'tig_nodes' table.
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
class TigNodesTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.TigNodesTableMap';

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
		$this->setName('tig_nodes');
		$this->setPhpName('TigNodes');
		$this->setClassname('TigNodes');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('NID', 'Nid', 'BIGINT', true, 20, null);
		$this->addColumn('PARENT_NID', 'ParentNid', 'BIGINT', false, 20, null);
		$this->addForeignKey('UID', 'Uid', 'BIGINT', 'tig_users', 'UID', true, 20, null);
		$this->addColumn('NODE', 'Node', 'VARCHAR', true, 255, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('TigUsers', 'TigUsers', RelationMap::MANY_TO_ONE, array('uid' => 'uid', ), 'RESTRICT', 'RESTRICT');
    $this->addRelation('TigPairs', 'TigPairs', RelationMap::ONE_TO_MANY, array('nid' => 'nid', ), 'RESTRICT', 'RESTRICT');
	} // buildRelations()

} // TigNodesTableMap
