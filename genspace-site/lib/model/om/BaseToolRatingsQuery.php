<?php


/**
 * Base class that represents a query for the 'tool_ratings' table.
 *
 * 
 *
 * @method     ToolRatingsQuery orderByPk($order = Criteria::ASC) Order by the pk column
 * @method     ToolRatingsQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     ToolRatingsQuery orderByRating($order = Criteria::ASC) Order by the rating column
 * @method     ToolRatingsQuery orderByUsername($order = Criteria::ASC) Order by the username column
 *
 * @method     ToolRatingsQuery groupByPk() Group by the pk column
 * @method     ToolRatingsQuery groupById() Group by the id column
 * @method     ToolRatingsQuery groupByRating() Group by the rating column
 * @method     ToolRatingsQuery groupByUsername() Group by the username column
 *
 * @method     ToolRatingsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ToolRatingsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ToolRatingsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     ToolRatings findOne(PropelPDO $con = null) Return the first ToolRatings matching the query
 * @method     ToolRatings findOneOrCreate(PropelPDO $con = null) Return the first ToolRatings matching the query, or a new ToolRatings object populated from the query conditions when no match is found
 *
 * @method     ToolRatings findOneByPk(int $pk) Return the first ToolRatings filtered by the pk column
 * @method     ToolRatings findOneById(int $id) Return the first ToolRatings filtered by the id column
 * @method     ToolRatings findOneByRating(int $rating) Return the first ToolRatings filtered by the rating column
 * @method     ToolRatings findOneByUsername(string $username) Return the first ToolRatings filtered by the username column
 *
 * @method     array findByPk(int $pk) Return ToolRatings objects filtered by the pk column
 * @method     array findById(int $id) Return ToolRatings objects filtered by the id column
 * @method     array findByRating(int $rating) Return ToolRatings objects filtered by the rating column
 * @method     array findByUsername(string $username) Return ToolRatings objects filtered by the username column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseToolRatingsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseToolRatingsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'ToolRatings', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ToolRatingsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ToolRatingsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ToolRatingsQuery) {
			return $criteria;
		}
		$query = new ToolRatingsQuery();
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
	 * @return    ToolRatings|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ToolRatingsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ToolRatingsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ToolRatingsPeer::PK, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ToolRatingsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ToolRatingsPeer::PK, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the pk column
	 * 
	 * @param     int|array $pk The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolRatingsQuery The current query, for fluid interface
	 */
	public function filterByPk($pk = null, $comparison = null)
	{
		if (is_array($pk) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ToolRatingsPeer::PK, $pk, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolRatingsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id)) {
			$useMinMax = false;
			if (isset($id['min'])) {
				$this->addUsingAlias(ToolRatingsPeer::ID, $id['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($id['max'])) {
				$this->addUsingAlias(ToolRatingsPeer::ID, $id['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolRatingsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the rating column
	 * 
	 * @param     int|array $rating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolRatingsQuery The current query, for fluid interface
	 */
	public function filterByRating($rating = null, $comparison = null)
	{
		if (is_array($rating)) {
			$useMinMax = false;
			if (isset($rating['min'])) {
				$this->addUsingAlias(ToolRatingsPeer::RATING, $rating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($rating['max'])) {
				$this->addUsingAlias(ToolRatingsPeer::RATING, $rating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolRatingsPeer::RATING, $rating, $comparison);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolRatingsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(ToolRatingsPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     ToolRatings $toolRatings Object to remove from the list of results
	 *
	 * @return    ToolRatingsQuery The current query, for fluid interface
	 */
	public function prune($toolRatings = null)
	{
		if ($toolRatings) {
			$this->addUsingAlias(ToolRatingsPeer::PK, $toolRatings->getPk(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseToolRatingsQuery
