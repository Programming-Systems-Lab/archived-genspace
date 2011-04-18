<?php



/**
 * This class defines the structure of the 'tig_users' table.
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
class TigUsersTableMap extends TableMap {

	/**
	 * The (dot-path) name of this class
	 */
	const CLASS_NAME = 'lib.model.map.TigUsersTableMap';

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
		$this->setName('tig_users');
		$this->setPhpName('TigUsers');
		$this->setClassname('TigUsers');
		$this->setPackage('lib.model');
		$this->setUseIdGenerator(true);
		// columns
		$this->addPrimaryKey('UID', 'Uid', 'BIGINT', true, 20, null);
		$this->addColumn('USER_ID', 'UserId', 'VARCHAR', true, 2049, null);
		$this->addColumn('SHA1_USER_ID', 'Sha1UserId', 'CHAR', true, 128, null);
		$this->addColumn('USER_PW', 'UserPw', 'VARCHAR', false, 255, null);
		$this->addColumn('ACC_CREATE_TIME', 'AccCreateTime', 'TIMESTAMP', true, null, 'CURRENT_TIMESTAMP');
		$this->addColumn('LAST_LOGIN', 'LastLogin', 'TIMESTAMP', true, null, '0000-00-00 00:00:00');
		$this->addColumn('LAST_LOGOUT', 'LastLogout', 'TIMESTAMP', true, null, '0000-00-00 00:00:00');
		$this->addColumn('ONLINE_STATUS', 'OnlineStatus', 'INTEGER', false, 11, 0);
		$this->addColumn('FAILED_LOGINS', 'FailedLogins', 'INTEGER', false, 11, 0);
		$this->addColumn('ACCOUNT_STATUS', 'AccountStatus', 'INTEGER', false, 11, 1);
		// validators
	} // initialize()

	/**
	 * Build the RelationMap objects for this table relationships
	 */
	public function buildRelations()
	{
    $this->addRelation('TigNodes', 'TigNodes', RelationMap::ONE_TO_MANY, array('uid' => 'uid', ), 'RESTRICT', 'RESTRICT');
    $this->addRelation('TigPairs', 'TigPairs', RelationMap::ONE_TO_MANY, array('uid' => 'uid', ), 'RESTRICT', 'RESTRICT');
	} // buildRelations()

} // TigUsersTableMap
