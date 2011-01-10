<?php



/**
 * This class defines the structure of the 'Outbox' table.
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
class OutboxTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.OutboxTableMap';

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
		$this->setName('Outbox');
		$this->setPhpName('Outbox');
		$this->setClassname('Outbox');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('MESSAGEID', 'Messageid', 'INTEGER', true, null, null);
		$this->addColumn('DATE', 'Date', 'TIMESTAMP', true, null, null);
		$this->addForeignKey('FROMUSER', 'Fromuser', 'VARCHAR', 'registration', 'USERNAME', true, 50, null);
		$this->addForeignKey('TOUSER', 'Touser', 'VARCHAR', 'registration', 'USERNAME', true, 50, null);
		$this->addColumn('MESSAGE', 'Message', 'VARCHAR', true, 200, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('RegistrationRelatedByFromuser', 'Registration', RelationMap::MANY_TO_ONE, array('FromUser' => 'username', ), null, null);
    $this->addRelation('RegistrationRelatedByTouser', 'Registration', RelationMap::MANY_TO_ONE, array('ToUser' => 'username', ), null, null);
	} // buildRelations()

} // OutboxTableMap
