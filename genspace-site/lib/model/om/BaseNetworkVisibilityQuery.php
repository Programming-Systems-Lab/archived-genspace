<?php


/**
 * Base class that represents a query for the 'network_visibility' table.
 *
 * 
 *
 * @method     NetworkVisibilityQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     NetworkVisibilityQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     NetworkVisibilityQuery orderByUserDataOption($order = Criteria::ASC) Order by the user_data_option column
 * @method     NetworkVisibilityQuery orderByNetworkname($order = Criteria::ASC) Order by the networkname column
 *
 * @method     NetworkVisibilityQuery groupById() Group by the id column
 * @method     NetworkVisibilityQuery groupByUsername() Group by the username column
 * @method     NetworkVisibilityQuery groupByUserDataOption() Group by the user_data_option column
 * @method     NetworkVisibilityQuery groupByNetworkname() Group by the networkname column
 *
 * @method     NetworkVisibilityQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     NetworkVisibilityQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     NetworkVisibilityQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     NetworkVisibilityQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     NetworkVisibilityQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     NetworkVisibilityQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     NetworkVisibility findOne(PropelPDO $con = null) Return the first NetworkVisibility matching the query
 * @method     NetworkVisibility findOneOrCreate(PropelPDO $con = null) Return the first NetworkVisibility matching the query, or a new NetworkVisibility object populated from the query conditions when no match is found
 *
 * @method     NetworkVisibility findOneById(int $id) Return the first NetworkVisibility filtered by the id column
 * @method     NetworkVisibility findOneByUsername(string $username) Return the first NetworkVisibility filtered by the username column
 * @method     NetworkVisibility findOneByUserDataOption(int $user_data_option) Return the first NetworkVisibility filtered by the user_data_option column
 * @method     NetworkVisibility findOneByNetworkname(string $networkname) Return the first NetworkVisibility filtered by the networkname column
 *
 * @method     array findById(int $id) Return NetworkVisibility objects filtered by the id column
 * @method     array findByUsername(string $username) Return NetworkVisibility objects filtered by the username column
 * @method     array findByUserDataOption(int $user_data_option) Return NetworkVisibility objects filtered by the user_data_option column
 * @method     array findByNetworkname(string $networkname) Return NetworkVisibility objects filtered by the networkname column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseNetworkVisibilityQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseNetworkVisibilityQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'NetworkVisibility', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new NetworkVisibilityQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    NetworkVisibilityQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof NetworkVisibilityQuery) {
			return $criteria;
		}
		$query = new NetworkVisibilityQuery();
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
	 * @return    NetworkVisibility|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = NetworkVisibilityPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(NetworkVisibilityPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(NetworkVisibilityPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(NetworkVisibilityPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterByUsername($username = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($username)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $username)) {
				$username = str_replace('*', '%', $username);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(NetworkVisibilityPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the user_data_option column
	 * 
	 * @param     int|array $userDataOption The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterByUserDataOption($userDataOption = null, $comparison = null)
	{
		if (is_array($userDataOption)) {
			$useMinMax = false;
			if (isset($userDataOption['min'])) {
				$this->addUsingAlias(NetworkVisibilityPeer::USER_DATA_OPTION, $userDataOption['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($userDataOption['max'])) {
				$this->addUsingAlias(NetworkVisibilityPeer::USER_DATA_OPTION, $userDataOption['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(NetworkVisibilityPeer::USER_DATA_OPTION, $userDataOption, $comparison);
	}

	/**
	 * Filter the query on the networkname column
	 * 
	 * @param     string $networkname The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterByNetworkname($networkname = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($networkname)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $networkname)) {
				$networkname = str_replace('*', '%', $networkname);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(NetworkVisibilityPeer::NETWORKNAME, $networkname, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(NetworkVisibilityPeer::USERNAME, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function joinRegistration($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Registration');
		
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
			$this->addJoinObject($join, 'Registration');
		}
		
		return $this;
	}

	/**
	 * Use the Registration relation Registration object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery A secondary query class using the current class as primary query
	 */
	public function useRegistrationQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinRegistration($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Registration', 'RegistrationQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     NetworkVisibility $networkVisibility Object to remove from the list of results
	 *
	 * @return    NetworkVisibilityQuery The current query, for fluid interface
	 */
	public function prune($networkVisibility = null)
	{
		if ($networkVisibility) {
			$this->addUsingAlias(NetworkVisibilityPeer::ID, $networkVisibility->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseNetworkVisibilityQuery
