<?php


/**
 * Base class that represents a query for the 'tools' table.
 *
 * 
 *
 * @method     ToolsQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     ToolsQuery orderByTool($order = Criteria::ASC) Order by the tool column
 * @method     ToolsQuery orderByDescription($order = Criteria::ASC) Order by the description column
 * @method     ToolsQuery orderByReplacedbyId($order = Criteria::ASC) Order by the replacedby_id column
 *
 * @method     ToolsQuery groupById() Group by the id column
 * @method     ToolsQuery groupByTool() Group by the tool column
 * @method     ToolsQuery groupByDescription() Group by the description column
 * @method     ToolsQuery groupByReplacedbyId() Group by the replacedby_id column
 *
 * @method     ToolsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ToolsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ToolsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Tools findOne(PropelPDO $con = null) Return the first Tools matching the query
 * @method     Tools findOneOrCreate(PropelPDO $con = null) Return the first Tools matching the query, or a new Tools object populated from the query conditions when no match is found
 *
 * @method     Tools findOneById(int $id) Return the first Tools filtered by the id column
 * @method     Tools findOneByTool(string $tool) Return the first Tools filtered by the tool column
 * @method     Tools findOneByDescription(string $description) Return the first Tools filtered by the description column
 * @method     Tools findOneByReplacedbyId(int $replacedby_id) Return the first Tools filtered by the replacedby_id column
 *
 * @method     array findById(int $id) Return Tools objects filtered by the id column
 * @method     array findByTool(string $tool) Return Tools objects filtered by the tool column
 * @method     array findByDescription(string $description) Return Tools objects filtered by the description column
 * @method     array findByReplacedbyId(int $replacedby_id) Return Tools objects filtered by the replacedby_id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseToolsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseToolsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Tools', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ToolsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ToolsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ToolsQuery) {
			return $criteria;
		}
		$query = new ToolsQuery();
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
	 * @return    Tools|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ToolsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ToolsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ToolsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ToolsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the tool column
	 * 
	 * @param     string $tool The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function filterByTool($tool = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($tool)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $tool)) {
				$tool = str_replace('*', '%', $tool);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ToolsPeer::TOOL, $tool, $comparison);
	}

	/**
	 * Filter the query on the description column
	 * 
	 * @param     string $description The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function filterByDescription($description = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($description)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $description)) {
				$description = str_replace('*', '%', $description);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ToolsPeer::DESCRIPTION, $description, $comparison);
	}

	/**
	 * Filter the query on the replacedby_id column
	 * 
	 * @param     int|array $replacedbyId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function filterByReplacedbyId($replacedbyId = null, $comparison = null)
	{
		if (is_array($replacedbyId)) {
			$useMinMax = false;
			if (isset($replacedbyId['min'])) {
				$this->addUsingAlias(ToolsPeer::REPLACEDBY_ID, $replacedbyId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($replacedbyId['max'])) {
				$this->addUsingAlias(ToolsPeer::REPLACEDBY_ID, $replacedbyId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolsPeer::REPLACEDBY_ID, $replacedbyId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Tools $tools Object to remove from the list of results
	 *
	 * @return    ToolsQuery The current query, for fluid interface
	 */
	public function prune($tools = null)
	{
		if ($tools) {
			$this->addUsingAlias(ToolsPeer::ID, $tools->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseToolsQuery
