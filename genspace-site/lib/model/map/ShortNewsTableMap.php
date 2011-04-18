<?php



/**
 * This class defines the structure of the 'short_news' table.
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
class ShortNewsTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.ShortNewsTableMap';

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
		$this->setName('short_news');
		$this->setPhpName('ShortNews');
		$this->setClassname('ShortNews');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('SNID', 'Snid', 'BIGINT', true, 20, null);
		$this->addColumn('PUBLISHING_TIME', 'PublishingTime', 'TIMESTAMP', true, null, 'CURRENT_TIMESTAMP');
		$this->addColumn('NEWS_TYPE', 'NewsType', 'VARCHAR', false, 10, null);
		$this->addColumn('AUTHOR', 'Author', 'VARCHAR', true, 128, null);
		$this->addColumn('SUBJECT', 'Subject', 'VARCHAR', true, 128, null);
		$this->addColumn('BODY', 'Body', 'VARCHAR', true, 1024, null);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
	} // buildRelations()

} // ShortNewsTableMap
