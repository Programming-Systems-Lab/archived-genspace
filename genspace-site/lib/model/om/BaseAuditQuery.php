<?php


/**
 * Base class that represents a query for the 'audit' table.
 *
 * 
 *
 * @method     AuditQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     AuditQuery orderByAction($order = Criteria::ASC) Order by the action column
 * @method     AuditQuery orderByTablename($order = Criteria::ASC) Order by the tablename column
 * @method     AuditQuery orderByBeforevalue($order = Criteria::ASC) Order by the beforevalue column
 * @method     AuditQuery orderByAftervalue($order = Criteria::ASC) Order by the aftervalue column
 * @method     AuditQuery orderByTime($order = Criteria::ASC) Order by the time column
 * @method     AuditQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     AuditQuery groupByUsername() Group by the username column
 * @method     AuditQuery groupByAction() Group by the action column
 * @method     AuditQuery groupByTablename() Group by the tablename column
 * @method     AuditQuery groupByBeforevalue() Group by the beforevalue column
 * @method     AuditQuery groupByAftervalue() Group by the aftervalue column
 * @method     AuditQuery groupByTime() Group by the time column
 * @method     AuditQuery groupById() Group by the id column
 *
 * @method     AuditQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     AuditQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     AuditQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     AuditQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     AuditQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     AuditQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     Audit findOne(PropelPDO $con = null) Return the first Audit matching the query
 * @method     Audit findOneOrCreate(PropelPDO $con = null) Return the first Audit matching the query, or a new Audit object populated from the query conditions when no match is found
 *
 * @method     Audit findOneByUsername(string $username) Return the first Audit filtered by the username column
 * @method     Audit findOneByAction(string $action) Return the first Audit filtered by the action column
 * @method     Audit findOneByTablename(string $tablename) Return the first Audit filtered by the tablename column
 * @method     Audit findOneByBeforevalue(string $beforevalue) Return the first Audit filtered by the beforevalue column
 * @method     Audit findOneByAftervalue(string $aftervalue) Return the first Audit filtered by the aftervalue column
 * @method     Audit findOneByTime(string $time) Return the first Audit filtered by the time column
 * @method     Audit findOneById(int $id) Return the first Audit filtered by the id column
 *
 * @method     array findByUsername(string $username) Return Audit objects filtered by the username column
 * @method     array findByAction(string $action) Return Audit objects filtered by the action column
 * @method     array findByTablename(string $tablename) Return Audit objects filtered by the tablename column
 * @method     array findByBeforevalue(string $beforevalue) Return Audit objects filtered by the beforevalue column
 * @method     array findByAftervalue(string $aftervalue) Return Audit objects filtered by the aftervalue column
 * @method     array findByTime(string $time) Return Audit objects filtered by the time column
 * @method     array findById(int $id) Return Audit objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseAuditQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseAuditQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Audit', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new AuditQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    AuditQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof AuditQuery) {
			return $criteria;
		}
		$query = new AuditQuery();
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
	 * @return    Audit|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = AuditPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(AuditPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(AuditPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
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
		return $this->addUsingAlias(AuditPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the action column
	 * 
	 * @param     string $action The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByAction($action = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($action)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $action)) {
				$action = str_replace('*', '%', $action);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AuditPeer::ACTION, $action, $comparison);
	}

	/**
	 * Filter the query on the tablename column
	 * 
	 * @param     string $tablename The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByTablename($tablename = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($tablename)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $tablename)) {
				$tablename = str_replace('*', '%', $tablename);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AuditPeer::TABLENAME, $tablename, $comparison);
	}

	/**
	 * Filter the query on the beforevalue column
	 * 
	 * @param     string $beforevalue The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByBeforevalue($beforevalue = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($beforevalue)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $beforevalue)) {
				$beforevalue = str_replace('*', '%', $beforevalue);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AuditPeer::BEFOREVALUE, $beforevalue, $comparison);
	}

	/**
	 * Filter the query on the aftervalue column
	 * 
	 * @param     string $aftervalue The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByAftervalue($aftervalue = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($aftervalue)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $aftervalue)) {
				$aftervalue = str_replace('*', '%', $aftervalue);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AuditPeer::AFTERVALUE, $aftervalue, $comparison);
	}

	/**
	 * Filter the query on the time column
	 * 
	 * @param     string|array $time The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByTime($time = null, $comparison = null)
	{
		if (is_array($time)) {
			$useMinMax = false;
			if (isset($time['min'])) {
				$this->addUsingAlias(AuditPeer::TIME, $time['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($time['max'])) {
				$this->addUsingAlias(AuditPeer::TIME, $time['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AuditPeer::TIME, $time, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(AuditPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(AuditPeer::USERNAME, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AuditQuery The current query, for fluid interface
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
	 * @param     Audit $audit Object to remove from the list of results
	 *
	 * @return    AuditQuery The current query, for fluid interface
	 */
	public function prune($audit = null)
	{
		if ($audit) {
			$this->addUsingAlias(AuditPeer::ID, $audit->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseAuditQuery
