<?php


/**
 * Base class that represents a query for the 'data_visibility' table.
 *
 * 
 *
 * @method     DataVisibilityQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     DataVisibilityQuery orderByLogdata($order = Criteria::ASC) Order by the logdata column
 * @method     DataVisibilityQuery orderByDatavisibility($order = Criteria::ASC) Order by the datavisibility column
 *
 * @method     DataVisibilityQuery groupByUsername() Group by the username column
 * @method     DataVisibilityQuery groupByLogdata() Group by the logdata column
 * @method     DataVisibilityQuery groupByDatavisibility() Group by the datavisibility column
 *
 * @method     DataVisibilityQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     DataVisibilityQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     DataVisibilityQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     DataVisibilityQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     DataVisibilityQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     DataVisibilityQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     DataVisibility findOne(PropelPDO $con = null) Return the first DataVisibility matching the query
 * @method     DataVisibility findOneOrCreate(PropelPDO $con = null) Return the first DataVisibility matching the query, or a new DataVisibility object populated from the query conditions when no match is found
 *
 * @method     DataVisibility findOneByUsername(string $username) Return the first DataVisibility filtered by the username column
 * @method     DataVisibility findOneByLogdata(int $logdata) Return the first DataVisibility filtered by the logdata column
 * @method     DataVisibility findOneByDatavisibility(int $datavisibility) Return the first DataVisibility filtered by the datavisibility column
 *
 * @method     array findByUsername(string $username) Return DataVisibility objects filtered by the username column
 * @method     array findByLogdata(int $logdata) Return DataVisibility objects filtered by the logdata column
 * @method     array findByDatavisibility(int $datavisibility) Return DataVisibility objects filtered by the datavisibility column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseDataVisibilityQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseDataVisibilityQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'DataVisibility', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new DataVisibilityQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    DataVisibilityQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof DataVisibilityQuery) {
			return $criteria;
		}
		$query = new DataVisibilityQuery();
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
	 * @return    DataVisibility|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = DataVisibilityPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    DataVisibilityQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(DataVisibilityPeer::USERNAME, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(DataVisibilityPeer::USERNAME, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
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
		return $this->addUsingAlias(DataVisibilityPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the logdata column
	 * 
	 * @param     int|array $logdata The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
	 */
	public function filterByLogdata($logdata = null, $comparison = null)
	{
		if (is_array($logdata)) {
			$useMinMax = false;
			if (isset($logdata['min'])) {
				$this->addUsingAlias(DataVisibilityPeer::LOGDATA, $logdata['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($logdata['max'])) {
				$this->addUsingAlias(DataVisibilityPeer::LOGDATA, $logdata['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(DataVisibilityPeer::LOGDATA, $logdata, $comparison);
	}

	/**
	 * Filter the query on the datavisibility column
	 * 
	 * @param     int|array $datavisibility The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
	 */
	public function filterByDatavisibility($datavisibility = null, $comparison = null)
	{
		if (is_array($datavisibility)) {
			$useMinMax = false;
			if (isset($datavisibility['min'])) {
				$this->addUsingAlias(DataVisibilityPeer::DATAVISIBILITY, $datavisibility['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($datavisibility['max'])) {
				$this->addUsingAlias(DataVisibilityPeer::DATAVISIBILITY, $datavisibility['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(DataVisibilityPeer::DATAVISIBILITY, $datavisibility, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(DataVisibilityPeer::USERNAME, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
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
	 * @param     DataVisibility $dataVisibility Object to remove from the list of results
	 *
	 * @return    DataVisibilityQuery The current query, for fluid interface
	 */
	public function prune($dataVisibility = null)
	{
		if ($dataVisibility) {
			$this->addUsingAlias(DataVisibilityPeer::USERNAME, $dataVisibility->getUsername(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseDataVisibilityQuery
