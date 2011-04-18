<?php


/**
 * Base class that represents a query for the 'NETWORK' table.
 *
 * 
 *
 * @method     NetworkQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     NetworkQuery orderByName($order = Criteria::ASC) Order by the NAME column
 * @method     NetworkQuery orderByOwner($order = Criteria::ASC) Order by the owner column
 *
 * @method     NetworkQuery groupById() Group by the ID column
 * @method     NetworkQuery groupByName() Group by the NAME column
 * @method     NetworkQuery groupByOwner() Group by the owner column
 *
 * @method     NetworkQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     NetworkQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     NetworkQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Network findOne(PropelPDO $con = null) Return the first Network matching the query
 * @method     Network findOneOrCreate(PropelPDO $con = null) Return the first Network matching the query, or a new Network object populated from the query conditions when no match is found
 *
 * @method     Network findOneById(int $ID) Return the first Network filtered by the ID column
 * @method     Network findOneByName(string $NAME) Return the first Network filtered by the NAME column
 * @method     Network findOneByOwner(int $owner) Return the first Network filtered by the owner column
 *
 * @method     array findById(int $ID) Return Network objects filtered by the ID column
 * @method     array findByName(string $NAME) Return Network objects filtered by the NAME column
 * @method     array findByOwner(int $owner) Return Network objects filtered by the owner column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseNetworkQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseNetworkQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Network', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new NetworkQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    NetworkQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof NetworkQuery) {
			return $criteria;
		}
		$query = new NetworkQuery();
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
	 * @return    Network|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = NetworkPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    NetworkQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(NetworkPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    NetworkQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(NetworkPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(NetworkPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the NAME column
	 * 
	 * @param     string $name The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkQuery The current query, for fluid interface
	 */
	public function filterByName($name = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($name)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $name)) {
				$name = str_replace('*', '%', $name);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(NetworkPeer::NAME, $name, $comparison);
	}

	/**
	 * Filter the query on the owner column
	 * 
	 * @param     int|array $owner The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworkQuery The current query, for fluid interface
	 */
	public function filterByOwner($owner = null, $comparison = null)
	{
		if (is_array($owner)) {
			$useMinMax = false;
			if (isset($owner['min'])) {
				$this->addUsingAlias(NetworkPeer::OWNER, $owner['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($owner['max'])) {
				$this->addUsingAlias(NetworkPeer::OWNER, $owner['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(NetworkPeer::OWNER, $owner, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Network $network Object to remove from the list of results
	 *
	 * @return    NetworkQuery The current query, for fluid interface
	 */
	public function prune($network = null)
	{
		if ($network) {
			$this->addUsingAlias(NetworkPeer::ID, $network->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseNetworkQuery
