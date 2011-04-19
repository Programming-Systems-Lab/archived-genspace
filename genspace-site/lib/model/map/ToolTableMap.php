<?php



/**
 * This class defines the structure of the 'TOOL' table.
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
class ToolTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.ToolTableMap';

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
		$this->setName('TOOL');
		$this->setPhpName('Tool');
		$this->setClassname('Tool');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('ID', 'Id', 'INTEGER', true, 11, null);
		$this->addColumn('MOSTCOMMONPARAMETERS', 'Mostcommonparameters', 'VARCHAR', false, 255, null);
		$this->addColumn('USAGECOUNT', 'Usagecount', 'INTEGER', false, 11, null);
		$this->addColumn('DESCRIPTION', 'Description', 'VARCHAR', false, 255, null);
		$this->addColumn('NAME', 'Name', 'VARCHAR', false, 255, null);
		$this->addColumn('MOSTCOMMONPARAMETERSCOUNT', 'Mostcommonparameterscount', 'INTEGER', false, 11, null);
		$this->addColumn('WFCOUNTHEAD', 'Wfcounthead', 'INTEGER', false, 11, null);
		$this->addColumn('NUMRATING', 'Numrating', 'INTEGER', false, 11, null);
		$this->addColumn('SUMRATING', 'Sumrating', 'INTEGER', false, 11, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // ToolTableMap