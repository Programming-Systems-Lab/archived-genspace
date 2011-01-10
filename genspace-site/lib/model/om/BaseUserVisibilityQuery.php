<?php


/**
 * Base class that represents a query for the 'user_visibility' table.
 *
 * 
 *
 * @method     UserVisibilityQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     UserVisibilityQuery orderByUservisibility($order = Criteria::ASC) Order by the uservisibility column
 * @method     UserVisibilityQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     UserVisibilityQuery groupByUsername() Group by the username column
 * @method     UserVisibilityQuery groupByUservisibility() Group by the uservisibility column
 * @method     UserVisibilityQuery groupById() Group by the id column
 *
 * @method     UserVisibilityQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     UserVisibilityQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     UserVisibilityQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     UserVisibilityQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     UserVisibilityQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     UserVisibilityQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     UserVisibility findOne(PropelPDO $con = null) Return the first UserVisibility matching the query
 * @method     UserVisibility findOneOrCreate(PropelPDO $con = null) Return the first UserVisibility matching the query, or a new UserVisibility object populated from the query conditions when no match is found
 *
 * @method     UserVisibility findOneByUsername(string $username) Return the first UserVisibility filtered by the username column
 * @method     UserVisibility findOneByUservisibility(int $uservisibility) Return the first UserVisibility filtered by the uservisibility column
 * @method     UserVisibility findOneById(int $id) Return the first UserVisibility filtered by the id column
 *
 * @method     array findByUsername(string $username) Return UserVisibility objects filtered by the username column
 * @method     array findByUservisibility(int $uservisibility) Return UserVisibility objects filtered by the uservisibility column
 * @method     array findById(int $id) Return UserVisibility objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseUserVisibilityQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseUserVisibilityQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'UserVisibility', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new UserVisibilityQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    UserVisibilityQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof UserVisibilityQuery) {
			return $criteria;
		}
		$query = new UserVisibilityQuery();
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
	 * @return    UserVisibility|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = UserVisibilityPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    UserVisibilityQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(UserVisibilityPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(UserVisibilityPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
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
		return $this->addUsingAlias(UserVisibilityPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the uservisibility column
	 * 
	 * @param     int|array $uservisibility The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
	 */
	public function filterByUservisibility($uservisibility = null, $comparison = null)
	{
		if (is_array($uservisibility)) {
			$useMinMax = false;
			if (isset($uservisibility['min'])) {
				$this->addUsingAlias(UserVisibilityPeer::USERVISIBILITY, $uservisibility['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($uservisibility['max'])) {
				$this->addUsingAlias(UserVisibilityPeer::USERVISIBILITY, $uservisibility['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserVisibilityPeer::USERVISIBILITY, $uservisibility, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(UserVisibilityPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(UserVisibilityPeer::USERNAME, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
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
	 * @param     UserVisibility $userVisibility Object to remove from the list of results
	 *
	 * @return    UserVisibilityQuery The current query, for fluid interface
	 */
	public function prune($userVisibility = null)
	{
		if ($userVisibility) {
			$this->addUsingAlias(UserVisibilityPeer::ID, $userVisibility->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseUserVisibilityQuery
