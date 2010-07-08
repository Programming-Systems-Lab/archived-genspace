<?php


abstract class BaseTestAnalysisEventsPeer {

	
	const DATABASE_NAME = 'propel';

	
	const TABLE_NAME = 'test_analysis_events';

	
	const CLASS_DEFAULT = 'lib.model.TestAnalysisEvents';

	
	const NUM_COLUMNS = 8;

	
	const NUM_LAZY_LOAD_COLUMNS = 0;


	
	const USERNAME = 'test_analysis_events.USERNAME';

	
	const HOST = 'test_analysis_events.HOST';

	
	const DATE = 'test_analysis_events.DATE';

	
	const ANALYSIS = 'test_analysis_events.ANALYSIS';

	
	const DATASET = 'test_analysis_events.DATASET';

	
	const TRANSACTION_ID = 'test_analysis_events.TRANSACTION_ID';

	
	const IS_GENSPACE_USER = 'test_analysis_events.IS_GENSPACE_USER';

	
	const ID = 'test_analysis_events.ID';

	
	private static $phpNameMap = null;


	
	private static $fieldNames = array (
		BasePeer::TYPE_PHPNAME => array ('Username', 'Host', 'Date', 'Analysis', 'Dataset', 'TransactionId', 'IsGenspaceUser', 'Id', ),
		BasePeer::TYPE_COLNAME => array (TestAnalysisEventsPeer::USERNAME, TestAnalysisEventsPeer::HOST, TestAnalysisEventsPeer::DATE, TestAnalysisEventsPeer::ANALYSIS, TestAnalysisEventsPeer::DATASET, TestAnalysisEventsPeer::TRANSACTION_ID, TestAnalysisEventsPeer::IS_GENSPACE_USER, TestAnalysisEventsPeer::ID, ),
		BasePeer::TYPE_FIELDNAME => array ('username', 'host', 'date', 'analysis', 'dataset', 'transaction_id', 'is_genspace_user', 'id', ),
		BasePeer::TYPE_NUM => array (0, 1, 2, 3, 4, 5, 6, 7, )
	);

	
	private static $fieldKeys = array (
		BasePeer::TYPE_PHPNAME => array ('Username' => 0, 'Host' => 1, 'Date' => 2, 'Analysis' => 3, 'Dataset' => 4, 'TransactionId' => 5, 'IsGenspaceUser' => 6, 'Id' => 7, ),
		BasePeer::TYPE_COLNAME => array (TestAnalysisEventsPeer::USERNAME => 0, TestAnalysisEventsPeer::HOST => 1, TestAnalysisEventsPeer::DATE => 2, TestAnalysisEventsPeer::ANALYSIS => 3, TestAnalysisEventsPeer::DATASET => 4, TestAnalysisEventsPeer::TRANSACTION_ID => 5, TestAnalysisEventsPeer::IS_GENSPACE_USER => 6, TestAnalysisEventsPeer::ID => 7, ),
		BasePeer::TYPE_FIELDNAME => array ('username' => 0, 'host' => 1, 'date' => 2, 'analysis' => 3, 'dataset' => 4, 'transaction_id' => 5, 'is_genspace_user' => 6, 'id' => 7, ),
		BasePeer::TYPE_NUM => array (0, 1, 2, 3, 4, 5, 6, 7, )
	);

	
	public static function getMapBuilder()
	{
		return BasePeer::getMapBuilder('lib.model.map.TestAnalysisEventsMapBuilder');
	}
	
	public static function getPhpNameMap()
	{
		if (self::$phpNameMap === null) {
			$map = TestAnalysisEventsPeer::getTableMap();
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
		return str_replace(TestAnalysisEventsPeer::TABLE_NAME.'.', $alias.'.', $column);
	}

	
	public static function addSelectColumns(Criteria $criteria)
	{

		$criteria->addSelectColumn(TestAnalysisEventsPeer::USERNAME);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::HOST);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::DATE);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::ANALYSIS);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::DATASET);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::TRANSACTION_ID);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::IS_GENSPACE_USER);

		$criteria->addSelectColumn(TestAnalysisEventsPeer::ID);

	}

	const COUNT = 'COUNT(test_analysis_events.ID)';
	const COUNT_DISTINCT = 'COUNT(DISTINCT test_analysis_events.ID)';

	
	public static function doCount(Criteria $criteria, $distinct = false, $con = null)
	{
				$criteria = clone $criteria;

				$criteria->clearSelectColumns()->clearOrderByColumns();
		if ($distinct || in_array(Criteria::DISTINCT, $criteria->getSelectModifiers())) {
			$criteria->addSelectColumn(TestAnalysisEventsPeer::COUNT_DISTINCT);
		} else {
			$criteria->addSelectColumn(TestAnalysisEventsPeer::COUNT);
		}

				foreach($criteria->getGroupByColumns() as $column)
		{
			$criteria->addSelectColumn($column);
		}

		$rs = TestAnalysisEventsPeer::doSelectRS($criteria, $con);
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
		$objects = TestAnalysisEventsPeer::doSelect($critcopy, $con);
		if ($objects) {
			return $objects[0];
		}
		return null;
	}
	
	public static function doSelect(Criteria $criteria, $con = null)
	{
		return TestAnalysisEventsPeer::populateObjects(TestAnalysisEventsPeer::doSelectRS($criteria, $con));
	}
	
	public static function doSelectRS(Criteria $criteria, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		if (!$criteria->getSelectColumns()) {
			$criteria = clone $criteria;
			TestAnalysisEventsPeer::addSelectColumns($criteria);
		}

				$criteria->setDbName(self::DATABASE_NAME);

						return BasePeer::doSelect($criteria, $con);
	}
	
	public static function populateObjects(ResultSet $rs)
	{
		$results = array();
	
				$cls = TestAnalysisEventsPeer::getOMClass();
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
		return TestAnalysisEventsPeer::CLASS_DEFAULT;
	}

	
	public static function doInsert($values, $con = null)
	{
		if ($con === null) {
			$con = Propel::getConnection(self::DATABASE_NAME);
		}

		if ($values instanceof Criteria) {
			$criteria = clone $values; 		} else {
			$criteria = $values->buildCriteria(); 		}

		$criteria->remove(TestAnalysisEventsPeer::ID); 

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
			$comparison = $criteria->getComparison(TestAnalysisEventsPeer::ID);
			$selectCriteria->add(TestAnalysisEventsPeer::ID, $criteria->remove(TestAnalysisEventsPeer::ID), $comparison);

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
			$affectedRows += BasePeer::doDeleteAll(TestAnalysisEventsPeer::TABLE_NAME, $con);
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
			$con = Propel::getConnection(TestAnalysisEventsPeer::DATABASE_NAME);
		}

		if ($values instanceof Criteria) {
			$criteria = clone $values; 		} elseif ($values instanceof TestAnalysisEvents) {

			$criteria = $values->buildPkeyCriteria();
		} else {
						$criteria = new Criteria(self::DATABASE_NAME);
			$criteria->add(TestAnalysisEventsPeer::ID, (array) $values, Criteria::IN);
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

	
	public static function doValidate(TestAnalysisEvents $obj, $cols = null)
	{
		$columns = array();

		if ($cols) {
			$dbMap = Propel::getDatabaseMap(TestAnalysisEventsPeer::DATABASE_NAME);
			$tableMap = $dbMap->getTable(TestAnalysisEventsPeer::TABLE_NAME);

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

		$res =  BasePeer::doValidate(TestAnalysisEventsPeer::DATABASE_NAME, TestAnalysisEventsPeer::TABLE_NAME, $columns);
    if ($res !== true) {
        $request = sfContext::getInstance()->getRequest();
        foreach ($res as $failed) {
            $col = TestAnalysisEventsPeer::translateFieldname($failed->getColumn(), BasePeer::TYPE_COLNAME, BasePeer::TYPE_PHPNAME);
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

		$criteria = new Criteria(TestAnalysisEventsPeer::DATABASE_NAME);

		$criteria->add(TestAnalysisEventsPeer::ID, $pk);


		$v = TestAnalysisEventsPeer::doSelect($criteria, $con);

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
			$criteria->add(TestAnalysisEventsPeer::ID, $pks, Criteria::IN);
			$objs = TestAnalysisEventsPeer::doSelect($criteria, $con);
		}
		return $objs;
	}

} 
if (Propel::isInit()) {
			try {
		BaseTestAnalysisEventsPeer::getMapBuilder();
	} catch (Exception $e) {
		Propel::log('Could not initialize Peer: ' . $e->getMessage(), Propel::LOG_ERR);
	}
} else {
			Propel::registerMapBuilder('lib.model.map.TestAnalysisEventsMapBuilder');
}
