<?php


abstract class BaseRegistrationPeer {

	
	const DATABASE_NAME = 'propel';

	
	const TABLE_NAME = 'registration';

	
	const CLASS_DEFAULT = 'lib.model.Registration';

	
	const NUM_COLUMNS = 15;

	
	const NUM_LAZY_LOAD_COLUMNS = 0;


	
	const USERNAME = 'registration.USERNAME';

	
	const PASSWORD = 'registration.PASSWORD';

	
	const EMAIL = 'registration.EMAIL';

	
	const IM_EMAIL = 'registration.IM_EMAIL';

	
	const IM_PASSWORD = 'registration.IM_PASSWORD';

	
	const FIRST_NAME = 'registration.FIRST_NAME';

	
	const LAST_NAME = 'registration.LAST_NAME';

	
	const WORK_TITLE = 'registration.WORK_TITLE';

	
	const PHONE = 'registration.PHONE';

	
	const LAB_AFFILIATION = 'registration.LAB_AFFILIATION';

	
	const ADDR1 = 'registration.ADDR1';

	
	const ADDR2 = 'registration.ADDR2';

	
	const CITY = 'registration.CITY';

	
	const STATE = 'registration.STATE';

	
	const ZIPCODE = 'registration.ZIPCODE';

	
	private static $phpNameMap = null;


	
	private static $fieldNames = array (
		BasePeer::TYPE_PHPNAME => array ('Username', 'Password', 'Email', 'ImEmail', 'ImPassword', 'FirstName', 'LastName', 'WorkTitle', 'Phone', 'LabAffiliation', 'Addr1', 'Addr2', 'City', 'State', 'Zipcode', ),
		BasePeer::TYPE_COLNAME => array (RegistrationPeer::USERNAME, RegistrationPeer::PASSWORD, RegistrationPeer::EMAIL, RegistrationPeer::IM_EMAIL, RegistrationPeer::IM_PASSWORD, RegistrationPeer::FIRST_NAME, RegistrationPeer::LAST_NAME, RegistrationPeer::WORK_TITLE, RegistrationPeer::PHONE, RegistrationPeer::LAB_AFFILIATION, RegistrationPeer::ADDR1, RegistrationPeer::ADDR2, RegistrationPeer::CITY, RegistrationPeer::STATE, RegistrationPeer::ZIPCODE, ),
		BasePeer::TYPE_FIELDNAME => array ('username', 'password', 'email', 'im_email', 'im_password', 'first_name', 'last_name', 'work_title', 'phone', 'lab_affiliation', 'addr1', 'addr2', 'city', 'state', 'zipcode', ),
		BasePeer::TYPE_NUM => array (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, )
	);

	
	private static $fieldKeys = array (
		BasePeer::TYPE_PHPNAME => array ('Username' => 0, 'Password' => 1, 'Email' => 2, 'ImEmail' => 3, 'ImPassword' => 4, 'FirstName' => 5, 'LastName' => 6, 'WorkTitle' => 7, 'Phone' => 8, 'LabAffiliation' => 9, 'Addr1' => 10, 'Addr2' => 11, 'City' => 12, 'State' => 13, 'Zipcode' => 14, ),
		BasePeer::TYPE_COLNAME => array (RegistrationPeer::USERNAME => 0, RegistrationPeer::PASSWORD => 1, RegistrationPeer::EMAIL => 2, RegistrationPeer::IM_EMAIL => 3, RegistrationPeer::IM_PASSWORD => 4, RegistrationPeer::FIRST_NAME => 5, RegistrationPeer::LAST_NAME => 6, RegistrationPeer::WORK_TITLE => 7, RegistrationPeer::PHONE => 8, RegistrationPeer::LAB_AFFILIATION => 9, RegistrationPeer::ADDR1 => 10, RegistrationPeer::ADDR2 => 11, RegistrationPeer::CITY => 12, RegistrationPeer::STATE => 13, RegistrationPeer::ZIPCODE => 14, ),
		BasePeer::TYPE_FIELDNAME => array ('username' => 0, 'password' => 1, 'email' => 2, 'im_email' => 3, 'im_password' => 4, 'first_name' => 5, 'last_name' => 6, 'work_title' => 7, 'phone' => 8, 'lab_affiliation' => 9, 'addr1' => 10, 'addr2' => 11, 'city' => 12, 'state' => 13, 'zipcode' => 14, ),
		BasePeer::TYPE_NUM => array (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, )
	);

	
	public static function getMapBuilder()
	{
		return BasePeer::getMapBuilder('lib.model.map.RegistrationMapBuilder');
	}
	
	public static function getPhpNameMap()
	{
		if (self::$phpNameMap === null) {
			$map = RegistrationPeer::getTableMap();
			$columns = $map->getColumns();
			$nameMap = array();
			foreach ($columns as $column) {
				$nameMap[$column->getPhpName()] = $column->getColumnName();
			}
			self::$phpNameMap = $nameMap;
		}
		return self::$phpNameMap;
	}
	
	static public function translateFieldName($name, $fromType, $toType)
	{
		$toNames = self::getFieldNames($toType);
		$key = isset(self::$fieldKeys[$fromType][$name]) ? self::$fieldKeys[$fromType][$name] : null;
		if ($key === null) {
			throw new PropelException("'$name' could not be found in the field names of type '$fromType'. These are: " . print_r(self::$fieldKeys[$fromType], true));
		}
		return $toNames[$key];
	}

	

	static public function getFieldNames($type = BasePeer::TYPE_PHPNAME)
	{
		if (!array_key_exists($type, self::$fieldNames)) {
			throw new PropelException('Method getFieldNames() expects the parameter $type to be one of the class constants TYPE_PHPNAME, TYPE_COLNAME, TYPE_FIELDNAME, TYPE_NUM. ' . $type . ' was given.');
		}
		return self::$fieldNames[$type];
	}

	
	public static function alias($alias, $column)
	{
		return str_replace(RegistrationPeer::TABLE_NAME.'.', $alias.'.', $column);
	}

	
	public static function addSelectColumns(Criteria $criteria)
	{

		$criteria->addSelectColumn(RegistrationPeer::USERNAME);

		$criteria->addSelectColumn(RegistrationPeer::PASSWORD);

		$criteria->addSelectColumn(RegistrationPeer::EMAIL);

		$criteria->addSelectColumn(RegistrationPeer::IM_EMAIL);

		$criteria->addSelectColumn(RegistrationPeer::IM_PASSWORD);

		$criteria->addSelectColumn(RegistrationPeer::FIRST_NAME);

		$criteria->addSelectColumn(RegistrationPeer::LAST_NAME);

		$criteria->addSelectColumn(RegistrationPeer::WORK_TITLE);

		$criteria->addSelectColumn(RegistrationPeer::PHONE);

		$criteria->addSelectColumn(RegistrationPeer::LAB_AFFILIATION);

		$criteria->addSelectColumn(RegistrationPeer::ADDR1);

		$criteria->addSelectColumn(RegistrationPeer::ADDR2);

		$criteria->addSelectColumn(RegistrationPeer::CITY);

		$criteria->addSelectColumn(RegistrationPeer::STATE);

		$criteria->addSelectColumn(RegistrationPeer::ZIPCODE);

	}

	const COUNT = 'COUNT(registration.USERNAME)';
	const COUNT_DISTINCT = 'COUNT(DISTINCT registration.USERNAME)';

	
	public static function doCount(Criteria $criteria, $distinct = false, $con = null)
	{
				$criteria = clone $criteria;

				$criteria->clearSelectColumns()->clearOrderByColumns();
		if ($distinct || in_array(Criteria::DISTINCT, $criteria->getSelectModifiers())) {
			$criteria->addSelectColumn(RegistrationPeer::COUNT_DISTINCT);
		} else {
			$criteria->addSelectColumn(RegistrationPeer::COUNT);
		}

				foreach($criteria->getGroupByColumns() as $column)
		{
			$criteria->addSelectColumn($column);
		}

		$rs = RegistrationPeer::doSelectRS($criteria, $con);
		if ($rs->next()) {
			return $rs->getInt(1);
		} else {
						return 0;
		}
	}
	
	public static function doSelectOne(Criteria $criteria, $con = null)
	{
		$critcopy = clone $criteria;
		$critcopy->setLimit(1);
		$objects = RegistrationPeer::doSelect($critcopy, $con);
		if ($objects) {
			return $objects[0];
		}
		return null;
	}
	
	public static function doSelect(Criteria $criteria, $con = null)
	{
		return RegistrationPeer::populateObjects(RegistrationPeer::doSelectRS($criteria, $con));
	}
	
	public static function doSelectRS(Criteria $criteria, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		if (!$criteria->getSelectColumns()) {
			$criteria = clone $criteria;
			RegistrationPeer::addSelectColumns($criteria);
		}

				$criteria->setDbName(self::DATABASE_NAME);

						return BasePeer::doSelect($criteria, $con);
	}
	
	public static function populateObjects(ResultSet $rs)
	{
		$results = array();
	
				$cls = RegistrationPeer::getOMClass();
		$cls = sfPropel::import($cls);
				while($rs->next()) {
		
			$obj = new $cls();
			$obj->hydrate($rs);
			$results[] = $obj;
			
		}
		return $results;
	}

  static public function getUniqueColumnNames()
  {
    return array();
  }
	
	public static function getTableMap()
	{
		return Propel::getDatabaseMap(self::DATABASE_NAME)->getTable(self::TABLE_NAME);
	}

	
	public static function getOMClass()
	{
		return RegistrationPeer::CLASS_DEFAULT;
	}

	
	public static function doInsert($values, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		if ($values instanceof Criteria) {
			$criteria = clone $values; 		} else {
			$criteria = $values->buildCriteria(); 		}


				$criteria->setDbName(self::DATABASE_NAME);

		try {
									$con->begin();
			$pk = BasePeer::doInsert($criteria, $con);
			$con->commit();
		} catch(PropelException $e) {
			$con->rollback();
			throw $e;
		}

		return $pk;
	}

	
	public static function doUpdate($values, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		$selectCriteria = new Criteria(self::DATABASE_NAME);

		if ($values instanceof Criteria) {
			$criteria = clone $values; 
			$comparison = $criteria->getComparison(RegistrationPeer::USERNAME);
			$selectCriteria->add(RegistrationPeer::USERNAME, $criteria->remove(RegistrationPeer::USERNAME), $comparison);

		} else { 			$criteria = $values->buildCriteria(); 			$selectCriteria = $values->buildPkeyCriteria(); 		}

				$criteria->setDbName(self::DATABASE_NAME);

		return BasePeer::doUpdate($selectCriteria, $criteria, $con);
	}

	
	public static function doDeleteAll($con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}
		$affectedRows = 0; 		try {
									$con->begin();
			$affectedRows += BasePeer::doDeleteAll(RegistrationPeer::TABLE_NAME, $con);
			$con->commit();
			return $affectedRows;
		} catch (PropelException $e) {
			$con->rollback();
			throw $e;
		}
	}

	
	 public static function doDelete($values, $con = null)
	 {
		if ($con === null) {
			$con = Propel::getConnection(RegistrationPeer::DATABASE_NAME);
		}

		if ($values instanceof Criteria) {
			$criteria = clone $values; 		} elseif ($values instanceof Registration) {

			$criteria = $values->buildPkeyCriteria();
		} else {
						$criteria = new Criteria(self::DATABASE_NAME);
			$criteria->add(RegistrationPeer::USERNAME, (array) $values, Criteria::IN);
		}

				$criteria->setDbName(self::DATABASE_NAME);

		$affectedRows = 0; 
		try {
									$con->begin();
			
			$affectedRows += BasePeer::doDelete($criteria, $con);
			$con->commit();
			return $affectedRows;
		} catch (PropelException $e) {
			$con->rollback();
			throw $e;
		}
	}

	
	public static function doValidate(Registration $obj, $cols = null)
	{
		$columns = array();

		if ($cols) {
			$dbMap = Propel::getDatabaseMap(RegistrationPeer::DATABASE_NAME);
			$tableMap = $dbMap->getTable(RegistrationPeer::TABLE_NAME);

			if (! is_array($cols)) {
				$cols = array($cols);
			}

			foreach($cols as $colName) {
				if ($tableMap->containsColumn($colName)) {
					$get = 'get' . $tableMap->getColumn($colName)->getPhpName();
					$columns[$colName] = $obj->$get();
				}
			}
		} else {

		}

		$res =  BasePeer::doValidate(RegistrationPeer::DATABASE_NAME, RegistrationPeer::TABLE_NAME, $columns);
    if ($res !== true) {
        $request = sfContext::getInstance()->getRequest();
        foreach ($res as $failed) {
            $col = RegistrationPeer::translateFieldname($failed->getColumn(), BasePeer::TYPE_COLNAME, BasePeer::TYPE_PHPNAME);
            $request->setError($col, $failed->getMessage());
        }
    }

    return $res;
	}

	
	public static function retrieveByPK($pk, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		$criteria = new Criteria(RegistrationPeer::DATABASE_NAME);

		$criteria->add(RegistrationPeer::USERNAME, $pk);


		$v = RegistrationPeer::doSelect($criteria, $con);

		return !empty($v) > 0 ? $v[0] : null;
	}

	
	public static function retrieveByPKs($pks, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		$objs = null;
		if (empty($pks)) {
			$objs = array();
		} else {
			$criteria = new Criteria();
			$criteria->add(RegistrationPeer::USERNAME, $pks, Criteria::IN);
			$objs = RegistrationPeer::doSelect($criteria, $con);
		}
		return $objs;
	}

} 
if (Propel::isInit()) {
			try {
		BaseRegistrationPeer::getMapBuilder();
	} catch (Exception $e) {
		Propel::log('Could not initialize Peer: ' . $e->getMessage(), Propel::LOG_ERR);
	}
} else {
			Propel::registerMapBuilder('lib.model.map.RegistrationMapBuilder');
}
