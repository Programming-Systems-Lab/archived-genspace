<?php


/**
 * Base class that represents a query for the 'include_exclude_analysis' table.
 *
 * 
 *
 * @method     IncludeExcludeAnalysisQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     IncludeExcludeAnalysisQuery orderByAnalysis($order = Criteria::ASC) Order by the analysis column
 * @method     IncludeExcludeAnalysisQuery orderByAction($order = Criteria::ASC) Order by the action column
 * @method     IncludeExcludeAnalysisQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     IncludeExcludeAnalysisQuery groupByUsername() Group by the username column
 * @method     IncludeExcludeAnalysisQuery groupByAnalysis() Group by the analysis column
 * @method     IncludeExcludeAnalysisQuery groupByAction() Group by the action column
 * @method     IncludeExcludeAnalysisQuery groupById() Group by the id column
 *
 * @method     IncludeExcludeAnalysisQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     IncludeExcludeAnalysisQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     IncludeExcludeAnalysisQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     IncludeExcludeAnalysis findOne(PropelPDO $con = null) Return the first IncludeExcludeAnalysis matching the query
 * @method     IncludeExcludeAnalysis findOneOrCreate(PropelPDO $con = null) Return the first IncludeExcludeAnalysis matching the query, or a new IncludeExcludeAnalysis object populated from the query conditions when no match is found
 *
 * @method     IncludeExcludeAnalysis findOneByUsername(string $username) Return the first IncludeExcludeAnalysis filtered by the username column
 * @method     IncludeExcludeAnalysis findOneByAnalysis(string $analysis) Return the first IncludeExcludeAnalysis filtered by the analysis column
 * @method     IncludeExcludeAnalysis findOneByAction(string $action) Return the first IncludeExcludeAnalysis filtered by the action column
 * @method     IncludeExcludeAnalysis findOneById(int $id) Return the first IncludeExcludeAnalysis filtered by the id column
 *
 * @method     array findByUsername(string $username) Return IncludeExcludeAnalysis objects filtered by the username column
 * @method     array findByAnalysis(string $analysis) Return IncludeExcludeAnalysis objects filtered by the analysis column
 * @method     array findByAction(string $action) Return IncludeExcludeAnalysis objects filtered by the action column
 * @method     array findById(int $id) Return IncludeExcludeAnalysis objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseIncludeExcludeAnalysisQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseIncludeExcludeAnalysisQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'IncludeExcludeAnalysis', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new IncludeExcludeAnalysisQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    IncludeExcludeAnalysisQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof IncludeExcludeAnalysisQuery) {
			return $criteria;
		}
		$query = new IncludeExcludeAnalysisQuery();
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
	 * @return    IncludeExcludeAnalysis|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = IncludeExcludeAnalysisPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(IncludeExcludeAnalysisPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(IncludeExcludeAnalysisPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
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
		return $this->addUsingAlias(IncludeExcludeAnalysisPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the analysis column
	 * 
	 * @param     string $analysis The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
	 */
	public function filterByAnalysis($analysis = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($analysis)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $analysis)) {
				$analysis = str_replace('*', '%', $analysis);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(IncludeExcludeAnalysisPeer::ANALYSIS, $analysis, $comparison);
	}

	/**
	 * Filter the query on the action column
	 * 
	 * @param     string $action The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
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
		return $this->addUsingAlias(IncludeExcludeAnalysisPeer::ACTION, $action, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(IncludeExcludeAnalysisPeer::ID, $id, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     IncludeExcludeAnalysis $includeExcludeAnalysis Object to remove from the list of results
	 *
	 * @return    IncludeExcludeAnalysisQuery The current query, for fluid interface
	 */
	public function prune($includeExcludeAnalysis = null)
	{
		if ($includeExcludeAnalysis) {
			$this->addUsingAlias(IncludeExcludeAnalysisPeer::ID, $includeExcludeAnalysis->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseIncludeExcludeAnalysisQuery
