<?php



/**
 * This class defines the structure of the 'tig_pairs' table.
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
class TigPairsTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.TigPairsTableMap';

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
		$this->setName('tig_pairs');
		$this->setPhpName('TigPairs');
		$this->setClassname('TigPairs');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addForeignKey('NID', 'Nid', 'BIGINT', 'tig_nodes', 'NID', false, 20, null);
		$this->addForeignKey('UID', 'Uid', 'BIGINT', 'tig_users', 'UID', true, 20, null);
		$this->addColumn('PKEY', 'Pkey', 'VARCHAR', true, 255, null);
		$this->addColumn('PVAL', 'Pval', 'LONGVARCHAR', false, null, null);
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, null, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('TigNodes', 'TigNodes', RelationMap::MANY_TO_ONE, array('nid' => 'nid', ), 'RESTRICT', 'RESTRICT');
    $this->addRelation('TigUsers', 'TigUsers', RelationMap::MANY_TO_ONE, array('uid' => 'uid', ), 'RESTRICT', 'RESTRICT');
	} // buildRelations()

} // TigPairsTableMap
