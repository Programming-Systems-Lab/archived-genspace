<?php


/**
 * Base class that represents a query for the 'login_events' table.
 *
 * 
 *
 * @method     LoginEventsQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     LoginEventsQuery orderByDate($order = Criteria::ASC) Order by the date column
 * @method     LoginEventsQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     LoginEventsQuery groupByUsername() Group by the username column
 * @method     LoginEventsQuery groupByDate() Group by the date column
 * @method     LoginEventsQuery groupById() Group by the id column
 *
 * @method     LoginEventsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     LoginEventsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     LoginEventsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     LoginEvents findOne(PropelPDO $con = null) Return the first LoginEvents matching the query
 * @method     LoginEvents findOneOrCreate(PropelPDO $con = null) Return the first LoginEvents matching the query, or a new LoginEvents object populated from the query conditions when no match is found
 *
 * @method     LoginEvents findOneByUsername(string $username) Return the first LoginEvents filtered by the username column
 * @method     LoginEvents findOneByDate(string $date) Return the first LoginEvents filtered by the date column
 * @method     LoginEvents findOneById(int $id) Return the first LoginEvents filtered by the id column
 *
 * @method     array findByUsername(string $username) Return LoginEvents objects filtered by the username column
 * @method     array findByDate(string $date) Return LoginEvents objects filtered by the date column
 * @method     array findById(int $id) Return LoginEvents objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseLoginEventsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseLoginEventsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'LoginEvents', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new LoginEventsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    LoginEventsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof LoginEventsQuery) {
			return $criteria;
		}
		$query = new LoginEventsQuery();
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
	 * @return    LoginEvents|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = LoginEventsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    LoginEventsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(LoginEventsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    LoginEventsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(LoginEventsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    LoginEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(LoginEventsPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the date column
	 * 
	 * @param     string|array $date The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    LoginEventsQuery The current query, for fluid interface
	 */
	public function filterByDate($date = null, $comparison = null)
	{
		if (is_array($date)) {
			$useMinMax = false;
			if (isset($date['min'])) {
				$this->addUsingAlias(LoginEventsPeer::DATE, $date['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($date['max'])) {
				$this->addUsingAlias(LoginEventsPeer::DATE, $date['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(LoginEventsPeer::DATE, $date, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    LoginEventsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(LoginEventsPeer::ID, $id, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     LoginEvents $loginEvents Object to remove from the list of results
	 *
	 * @return    LoginEventsQuery The current query, for fluid interface
	 */
	public function prune($loginEvents = null)
	{
		if ($loginEvents) {
			$this->addUsingAlias(LoginEventsPeer::ID, $loginEvents->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseLoginEventsQuery
