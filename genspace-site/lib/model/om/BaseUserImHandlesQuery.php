<?php


/**
 * Base class that represents a query for the 'user_IM_handles' table.
 *
 * 
 *
 * @method     UserImHandlesQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     UserImHandlesQuery orderByImHandle($order = Criteria::ASC) Order by the IM_handle column
 * @method     UserImHandlesQuery orderByImService($order = Criteria::ASC) Order by the IM_service column
 * @method     UserImHandlesQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     UserImHandlesQuery groupByUsername() Group by the username column
 * @method     UserImHandlesQuery groupByImHandle() Group by the IM_handle column
 * @method     UserImHandlesQuery groupByImService() Group by the IM_service column
 * @method     UserImHandlesQuery groupById() Group by the id column
 *
 * @method     UserImHandlesQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     UserImHandlesQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     UserImHandlesQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     UserImHandles findOne(PropelPDO $con = null) Return the first UserImHandles matching the query
 * @method     UserImHandles findOneOrCreate(PropelPDO $con = null) Return the first UserImHandles matching the query, or a new UserImHandles object populated from the query conditions when no match is found
 *
 * @method     UserImHandles findOneByUsername(string $username) Return the first UserImHandles filtered by the username column
 * @method     UserImHandles findOneByImHandle(string $IM_handle) Return the first UserImHandles filtered by the IM_handle column
 * @method     UserImHandles findOneByImService(string $IM_service) Return the first UserImHandles filtered by the IM_service column
 * @method     UserImHandles findOneById(int $id) Return the first UserImHandles filtered by the id column
 *
 * @method     array findByUsername(string $username) Return UserImHandles objects filtered by the username column
 * @method     array findByImHandle(string $IM_handle) Return UserImHandles objects filtered by the IM_handle column
 * @method     array findByImService(string $IM_service) Return UserImHandles objects filtered by the IM_service column
 * @method     array findById(int $id) Return UserImHandles objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseUserImHandlesQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseUserImHandlesQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'UserImHandles', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new UserImHandlesQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    UserImHandlesQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof UserImHandlesQuery) {
			return $criteria;
		}
		$query = new UserImHandlesQuery();
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
	 * @return    UserImHandles|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = UserImHandlesPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    UserImHandlesQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(UserImHandlesPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    UserImHandlesQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(UserImHandlesPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserImHandlesQuery The current query, for fluid interface
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
		return $this->addUsingAlias(UserImHandlesPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the IM_handle column
	 * 
	 * @param     string $imHandle The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserImHandlesQuery The current query, for fluid interface
	 */
	public function filterByImHandle($imHandle = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($imHandle)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $imHandle)) {
				$imHandle = str_replace('*', '%', $imHandle);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(UserImHandlesPeer::IM_HANDLE, $imHandle, $comparison);
	}

	/**
	 * Filter the query on the IM_service column
	 * 
	 * @param     string $imService The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserImHandlesQuery The current query, for fluid interface
	 */
	public function filterByImService($imService = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($imService)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $imService)) {
				$imService = str_replace('*', '%', $imService);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(UserImHandlesPeer::IM_SERVICE, $imService, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserImHandlesQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(UserImHandlesPeer::ID, $id, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     UserImHandles $userImHandles Object to remove from the list of results
	 *
	 * @return    UserImHandlesQuery The current query, for fluid interface
	 */
	public function prune($userImHandles = null)
	{
		if ($userImHandles) {
			$this->addUsingAlias(UserImHandlesPeer::ID, $userImHandles->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseUserImHandlesQuery
