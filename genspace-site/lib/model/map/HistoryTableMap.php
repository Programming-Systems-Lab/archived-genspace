<?php



/**
 * This class defines the structure of the 'history' table.
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
class HistoryTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.HistoryTableMap';

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
		$this->setName('history');
		$this->setPhpName('History');
		$this->setClassname('History');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addForeignKey('WSPID', 'Wspid', 'INTEGER', 'workspace', 'ID', false, 11, null);
		$this->addForeignKey('UID', 'Uid', 'INTEGER', 'registration', 'ID', false, 11, null);
		$this->addColumn('TYPE', 'Type', 'CHAR', false, 1, null);
		$this->addColumn('ACCESSEDAT', 'Accessedat', 'TIMESTAMP', true, null, 'CURRENT_TIMESTAMP');
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('Workspace', 'Workspace', RelationMap::MANY_TO_ONE, array('wspid' => 'id', ), 'CASCADE', 'RESTRICT');
    $this->addRelation('Registration', 'Registration', RelationMap::MANY_TO_ONE, array('uid' => 'ID', ), 'CASCADE', 'RESTRICT');
	} // buildRelations()

} // HistoryTableMap
