<?php


/**
 * Base class that represents a query for the 'tig_users' table.
 *
 * 
 *
 * @method     TigUsersQuery orderByUid($order = Criteria::ASC) Order by the uid column
 * @method     TigUsersQuery orderByUserId($order = Criteria::ASC) Order by the user_id column
 * @method     TigUsersQuery orderBySha1UserId($order = Criteria::ASC) Order by the sha1_user_id column
 * @method     TigUsersQuery orderByUserPw($order = Criteria::ASC) Order by the user_pw column
 * @method     TigUsersQuery orderByAccCreateTime($order = Criteria::ASC) Order by the acc_create_time column
 * @method     TigUsersQuery orderByLastLogin($order = Criteria::ASC) Order by the last_login column
 * @method     TigUsersQuery orderByLastLogout($order = Criteria::ASC) Order by the last_logout column
 * @method     TigUsersQuery orderByOnlineStatus($order = Criteria::ASC) Order by the online_status column
 * @method     TigUsersQuery orderByFailedLogins($order = Criteria::ASC) Order by the failed_logins column
 * @method     TigUsersQuery orderByAccountStatus($order = Criteria::ASC) Order by the account_status column
 *
 * @method     TigUsersQuery groupByUid() Group by the uid column
 * @method     TigUsersQuery groupByUserId() Group by the user_id column
 * @method     TigUsersQuery groupBySha1UserId() Group by the sha1_user_id column
 * @method     TigUsersQuery groupByUserPw() Group by the user_pw column
 * @method     TigUsersQuery groupByAccCreateTime() Group by the acc_create_time column
 * @method     TigUsersQuery groupByLastLogin() Group by the last_login column
 * @method     TigUsersQuery groupByLastLogout() Group by the last_logout column
 * @method     TigUsersQuery groupByOnlineStatus() Group by the online_status column
 * @method     TigUsersQuery groupByFailedLogins() Group by the failed_logins column
 * @method     TigUsersQuery groupByAccountStatus() Group by the account_status column
 *
 * @method     TigUsersQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     TigUsersQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     TigUsersQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     TigUsersQuery leftJoinTigNodes($relationAlias = null) Adds a LEFT JOIN clause to the query using the TigNodes relation
 * @method     TigUsersQuery rightJoinTigNodes($relationAlias = null) Adds a RIGHT JOIN clause to the query using the TigNodes relation
 * @method     TigUsersQuery innerJoinTigNodes($relationAlias = null) Adds a INNER JOIN clause to the query using the TigNodes relation
 *
 * @method     TigUsersQuery leftJoinTigPairs($relationAlias = null) Adds a LEFT JOIN clause to the query using the TigPairs relation
 * @method     TigUsersQuery rightJoinTigPairs($relationAlias = null) Adds a RIGHT JOIN clause to the query using the TigPairs relation
 * @method     TigUsersQuery innerJoinTigPairs($relationAlias = null) Adds a INNER JOIN clause to the query using the TigPairs relation
 *
 * @method     TigUsers findOne(PropelPDO $con = null) Return the first TigUsers matching the query
 * @method     TigUsers findOneOrCreate(PropelPDO $con = null) Return the first TigUsers matching the query, or a new TigUsers object populated from the query conditions when no match is found
 *
 * @method     TigUsers findOneByUid(string $uid) Return the first TigUsers filtered by the uid column
 * @method     TigUsers findOneByUserId(string $user_id) Return the first TigUsers filtered by the user_id column
 * @method     TigUsers findOneBySha1UserId(string $sha1_user_id) Return the first TigUsers filtered by the sha1_user_id column
 * @method     TigUsers findOneByUserPw(string $user_pw) Return the first TigUsers filtered by the user_pw column
 * @method     TigUsers findOneByAccCreateTime(string $acc_create_time) Return the first TigUsers filtered by the acc_create_time column
 * @method     TigUsers findOneByLastLogin(string $last_login) Return the first TigUsers filtered by the last_login column
 * @method     TigUsers findOneByLastLogout(string $last_logout) Return the first TigUsers filtered by the last_logout column
 * @method     TigUsers findOneByOnlineStatus(int $online_status) Return the first TigUsers filtered by the online_status column
 * @method     TigUsers findOneByFailedLogins(int $failed_logins) Return the first TigUsers filtered by the failed_logins column
 * @method     TigUsers findOneByAccountStatus(int $account_status) Return the first TigUsers filtered by the account_status column
 *
 * @method     array findByUid(string $uid) Return TigUsers objects filtered by the uid column
 * @method     array findByUserId(string $user_id) Return TigUsers objects filtered by the user_id column
 * @method     array findBySha1UserId(string $sha1_user_id) Return TigUsers objects filtered by the sha1_user_id column
 * @method     array findByUserPw(string $user_pw) Return TigUsers objects filtered by the user_pw column
 * @method     array findByAccCreateTime(string $acc_create_time) Return TigUsers objects filtered by the acc_create_time column
 * @method     array findByLastLogin(string $last_login) Return TigUsers objects filtered by the last_login column
 * @method     array findByLastLogout(string $last_logout) Return TigUsers objects filtered by the last_logout column
 * @method     array findByOnlineStatus(int $online_status) Return TigUsers objects filtered by the online_status column
 * @method     array findByFailedLogins(int $failed_logins) Return TigUsers objects filtered by the failed_logins column
 * @method     array findByAccountStatus(int $account_status) Return TigUsers objects filtered by the account_status column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTigUsersQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseTigUsersQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'TigUsers', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new TigUsersQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    TigUsersQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof TigUsersQuery) {
			return $criteria;
		}
		$query = new TigUsersQuery();
		if (null !== $modelAlias) {
			$query->setModelAlias($modelAlias);
		}
		if ($criteria instanceof Criteria) {
			$query->mergeWith($criteria);
		}
		return $query;
	}

	/**
	 * Find object by primary key
	 * Use instance pooling to avoid a database query if the object exists
	 * <code>
	 * $obj  = $c->findPk(12, $con);
	 * </code>
	 * @param     mixed $key Primary key to use for the query
	 * @param     PropelPDO $con an optional connection object
	 *
	 * @return    TigUsers|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = TigUsersPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
			// the object is alredy in the instance pool
			return $obj;
		} else {
			// the object has not been requested yet, or the formatter is not an object formatter
			$criteria = $this->isKeepQuery() ? clone $this : $this;
			$stmt = $criteria
				->filterByPrimaryKey($key)
				->getSelectStatement($con);
			return $criteria->getFormatter()->init($criteria)->formatOne($stmt);
		}
	}

	/**
	 * Find objects by primary key
	 * <code>
	 * $objs = $c->findPks(array(12, 56, 832), $con);
	 * </code>
	 * @param     array $keys Primary keys to use for the query
	 * @param     PropelPDO $con an optional connection object
	 *
	 * @return    PropelObjectCollection|array|mixed the list of results, formatted by the current formatter
	 */
	public function findPks($keys, $con = null)
	{	
		$criteria = $this->isKeepQuery() ? clone $this : $this;
		return $this
			->filterByPrimaryKeys($keys)
			->find($con);
	}

	/**
	 * Filter the query by primary key
	 *
	 * @param     mixed $key Primary key to use for the query
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(TigUsersPeer::UID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(TigUsersPeer::UID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the uid column
	 * 
	 * @param     string|array $uid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByUid($uid = null, $comparison = null)
	{
		if (is_array($uid) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(TigUsersPeer::UID, $uid, $comparison);
	}

	/**
	 * Filter the query on the user_id column
	 * 
	 * @param     string $userId The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByUserId($userId = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($userId)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $userId)) {
				$userId = str_replace('*', '%', $userId);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::USER_ID, $userId, $comparison);
	}

	/**
	 * Filter the query on the sha1_user_id column
	 * 
	 * @param     string $sha1UserId The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterBySha1UserId($sha1UserId = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($sha1UserId)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $sha1UserId)) {
				$sha1UserId = str_replace('*', '%', $sha1UserId);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::SHA1_USER_ID, $sha1UserId, $comparison);
	}

	/**
	 * Filter the query on the user_pw column
	 * 
	 * @param     string $userPw The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByUserPw($userPw = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($userPw)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $userPw)) {
				$userPw = str_replace('*', '%', $userPw);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::USER_PW, $userPw, $comparison);
	}

	/**
	 * Filter the query on the acc_create_time column
	 * 
	 * @param     string|array $accCreateTime The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByAccCreateTime($accCreateTime = null, $comparison = null)
	{
		if (is_array($accCreateTime)) {
			$useMinMax = false;
			if (isset($accCreateTime['min'])) {
				$this->addUsingAlias(TigUsersPeer::ACC_CREATE_TIME, $accCreateTime['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($accCreateTime['max'])) {
				$this->addUsingAlias(TigUsersPeer::ACC_CREATE_TIME, $accCreateTime['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::ACC_CREATE_TIME, $accCreateTime, $comparison);
	}

	/**
	 * Filter the query on the last_login column
	 * 
	 * @param     string|array $lastLogin The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByLastLogin($lastLogin = null, $comparison = null)
	{
		if (is_array($lastLogin)) {
			$useMinMax = false;
			if (isset($lastLogin['min'])) {
				$this->addUsingAlias(TigUsersPeer::LAST_LOGIN, $lastLogin['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($lastLogin['max'])) {
				$this->addUsingAlias(TigUsersPeer::LAST_LOGIN, $lastLogin['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::LAST_LOGIN, $lastLogin, $comparison);
	}

	/**
	 * Filter the query on the last_logout column
	 * 
	 * @param     string|array $lastLogout The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByLastLogout($lastLogout = null, $comparison = null)
	{
		if (is_array($lastLogout)) {
			$useMinMax = false;
			if (isset($lastLogout['min'])) {
				$this->addUsingAlias(TigUsersPeer::LAST_LOGOUT, $lastLogout['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($lastLogout['max'])) {
				$this->addUsingAlias(TigUsersPeer::LAST_LOGOUT, $lastLogout['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::LAST_LOGOUT, $lastLogout, $comparison);
	}

	/**
	 * Filter the query on the online_status column
	 * 
	 * @param     int|array $onlineStatus The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByOnlineStatus($onlineStatus = null, $comparison = null)
	{
		if (is_array($onlineStatus)) {
			$useMinMax = false;
			if (isset($onlineStatus['min'])) {
				$this->addUsingAlias(TigUsersPeer::ONLINE_STATUS, $onlineStatus['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($onlineStatus['max'])) {
				$this->addUsingAlias(TigUsersPeer::ONLINE_STATUS, $onlineStatus['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::ONLINE_STATUS, $onlineStatus, $comparison);
	}

	/**
	 * Filter the query on the failed_logins column
	 * 
	 * @param     int|array $failedLogins The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByFailedLogins($failedLogins = null, $comparison = null)
	{
		if (is_array($failedLogins)) {
			$useMinMax = false;
			if (isset($failedLogins['min'])) {
				$this->addUsingAlias(TigUsersPeer::FAILED_LOGINS, $failedLogins['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($failedLogins['max'])) {
				$this->addUsingAlias(TigUsersPeer::FAILED_LOGINS, $failedLogins['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::FAILED_LOGINS, $failedLogins, $comparison);
	}

	/**
	 * Filter the query on the account_status column
	 * 
	 * @param     int|array $accountStatus The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByAccountStatus($accountStatus = null, $comparison = null)
	{
		if (is_array($accountStatus)) {
			$useMinMax = false;
			if (isset($accountStatus['min'])) {
				$this->addUsingAlias(TigUsersPeer::ACCOUNT_STATUS, $accountStatus['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($accountStatus['max'])) {
				$this->addUsingAlias(TigUsersPeer::ACCOUNT_STATUS, $accountStatus['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigUsersPeer::ACCOUNT_STATUS, $accountStatus, $comparison);
	}

	/**
	 * Filter the query by a related TigNodes object
	 *
	 * @param     TigNodes $tigNodes  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByTigNodes($tigNodes, $comparison = null)
	{
		return $this
			->addUsingAlias(TigUsersPeer::UID, $tigNodes->getUid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the TigNodes relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function joinTigNodes($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('TigNodes');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'TigNodes');
		}
		
		return $this;
	}

	/**
	 * Use the TigNodes relation TigNodes object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigNodesQuery A secondary query class using the current class as primary query
	 */
	public function useTigNodesQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinTigNodes($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'TigNodes', 'TigNodesQuery');
	}

	/**
	 * Filter the query by a related TigPairs object
	 *
	 * @param     TigPairs $tigPairs  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function filterByTigPairs($tigPairs, $comparison = null)
	{
		return $this
			->addUsingAlias(TigUsersPeer::UID, $tigPairs->getUid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the TigPairs relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function joinTigPairs($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('TigPairs');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'TigPairs');
		}
		
		return $this;
	}

	/**
	 * Use the TigPairs relation TigPairs object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigPairsQuery A secondary query class using the current class as primary query
	 */
	public function useTigPairsQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinTigPairs($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'TigPairs', 'TigPairsQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     TigUsers $tigUsers Object to remove from the list of results
	 *
	 * @return    TigUsersQuery The current query, for fluid interface
	 */
	public function prune($tigUsers = null)
	{
		if ($tigUsers) {
			$this->addUsingAlias(TigUsersPeer::UID, $tigUsers->getUid(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseTigUsersQuery
