<?php



/**
 * This class defines the structure of the 'audit' table.
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
class AuditTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.AuditTableMap';

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
		$this->setName('audit');
		$this->setPhpName('Audit');
		$this->setClassname('Audit');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addForeignKey('USERNAME', 'Username', 'VARCHAR', 'registration', 'USERNAME', true, 50, null);
		$this->addColumn('ACTION', 'Action', 'VARCHAR', true, 500, null);
		$this->addColumn('TABLENAME', 'Tablename', 'VARCHAR', true, 50, null);
		$this->addColumn('BEFOREVALUE', 'Beforevalue', 'VARCHAR', true, 1000, null);
		$this->addColumn('AFTERVALUE', 'Aftervalue', 'VARCHAR', true, 1000, null);
		$this->addColumn('TIME', 'Time', 'TIMESTAMP', true, null, null);
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, null, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('Registration', 'Registration', RelationMap::MANY_TO_ONE, array('username' => 'username', ), null, null);
	} // buildRelations()

} // AuditTableMap
