<?php



/**
 * This class defines the structure of the 'ANALYSISEVENTPARAMETER' table.
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
class AnalysiseventparameterTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.AnalysiseventparameterTableMap';

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
		$this->setName('ANALYSISEVENTPARAMETER');
		$this->setPhpName('Analysiseventparameter');
		$this->setClassname('Analysiseventparameter');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('PARAMETERVALUE', 'Parametervalue', 'VARCHAR', false, 255, null);
		$this->addColumn('PARAMETERKEY', 'Parameterkey', 'VARCHAR', false, 255, null);
		$this->addColumn('EVENT_ID', 'EventId', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // AnalysiseventparameterTableMap
