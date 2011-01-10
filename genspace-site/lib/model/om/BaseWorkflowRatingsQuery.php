<?php


/**
 * Base class that represents a query for the 'workflow_ratings' table.
 *
 * 
 *
 * @method     WorkflowRatingsQuery orderByPk($order = Criteria::ASC) Order by the pk column
 * @method     WorkflowRatingsQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     WorkflowRatingsQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     WorkflowRatingsQuery orderByRating($order = Criteria::ASC) Order by the rating column
 *
 * @method     WorkflowRatingsQuery groupByPk() Group by the pk column
 * @method     WorkflowRatingsQuery groupById() Group by the id column
 * @method     WorkflowRatingsQuery groupByUsername() Group by the username column
 * @method     WorkflowRatingsQuery groupByRating() Group by the rating column
 *
 * @method     WorkflowRatingsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowRatingsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowRatingsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     WorkflowRatings findOne(PropelPDO $con = null) Return the first WorkflowRatings matching the query
 * @method     WorkflowRatings findOneOrCreate(PropelPDO $con = null) Return the first WorkflowRatings matching the query, or a new WorkflowRatings object populated from the query conditions when no match is found
 *
 * @method     WorkflowRatings findOneByPk(int $pk) Return the first WorkflowRatings filtered by the pk column
 * @method     WorkflowRatings findOneById(int $id) Return the first WorkflowRatings filtered by the id column
 * @method     WorkflowRatings findOneByUsername(string $username) Return the first WorkflowRatings filtered by the username column
 * @method     WorkflowRatings findOneByRating(int $rating) Return the first WorkflowRatings filtered by the rating column
 *
 * @method     array findByPk(int $pk) Return WorkflowRatings objects filtered by the pk column
 * @method     array findById(int $id) Return WorkflowRatings objects filtered by the id column
 * @method     array findByUsername(string $username) Return WorkflowRatings objects filtered by the username column
 * @method     array findByRating(int $rating) Return WorkflowRatings objects filtered by the rating column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowRatingsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowRatingsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'WorkflowRatings', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowRatingsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowRatingsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowRatingsQuery) {
			return $criteria;
		}
		$query = new WorkflowRatingsQuery();
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
	 * @return    WorkflowRatings|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowRatingsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowRatingsPeer::PK, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowRatingsPeer::PK, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the pk column
	 * 
	 * @param     int|array $pk The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
	 */
	public function filterByPk($pk = null, $comparison = null)
	{
		if (is_array($pk) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowRatingsPeer::PK, $pk, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id)) {
			$useMinMax = false;
			if (isset($id['min'])) {
				$this->addUsingAlias(WorkflowRatingsPeer::ID, $id['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($id['max'])) {
				$this->addUsingAlias(WorkflowRatingsPeer::ID, $id['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowRatingsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(WorkflowRatingsPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the rating column
	 * 
	 * @param     int|array $rating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
	 */
	public function filterByRating($rating = null, $comparison = null)
	{
		if (is_array($rating)) {
			$useMinMax = false;
			if (isset($rating['min'])) {
				$this->addUsingAlias(WorkflowRatingsPeer::RATING, $rating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($rating['max'])) {
				$this->addUsingAlias(WorkflowRatingsPeer::RATING, $rating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowRatingsPeer::RATING, $rating, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     WorkflowRatings $workflowRatings Object to remove from the list of results
	 *
	 * @return    WorkflowRatingsQuery The current query, for fluid interface
	 */
	public function prune($workflowRatings = null)
	{
		if ($workflowRatings) {
			$this->addUsingAlias(WorkflowRatingsPeer::PK, $workflowRatings->getPk(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowRatingsQuery
