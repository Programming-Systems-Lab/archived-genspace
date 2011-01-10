<?php


/**
 * Base class that represents a query for the 'workflows' table.
 *
 * 
 *
 * @method     WorkflowsQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     WorkflowsQuery orderByParent($order = Criteria::ASC) Order by the parent column
 * @method     WorkflowsQuery orderByTool($order = Criteria::ASC) Order by the tool column
 *
 * @method     WorkflowsQuery groupById() Group by the id column
 * @method     WorkflowsQuery groupByParent() Group by the parent column
 * @method     WorkflowsQuery groupByTool() Group by the tool column
 *
 * @method     WorkflowsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Workflows findOne(PropelPDO $con = null) Return the first Workflows matching the query
 * @method     Workflows findOneOrCreate(PropelPDO $con = null) Return the first Workflows matching the query, or a new Workflows object populated from the query conditions when no match is found
 *
 * @method     Workflows findOneById(int $id) Return the first Workflows filtered by the id column
 * @method     Workflows findOneByParent(int $parent) Return the first Workflows filtered by the parent column
 * @method     Workflows findOneByTool(string $tool) Return the first Workflows filtered by the tool column
 *
 * @method     array findById(int $id) Return Workflows objects filtered by the id column
 * @method     array findByParent(int $parent) Return Workflows objects filtered by the parent column
 * @method     array findByTool(string $tool) Return Workflows objects filtered by the tool column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflows', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowsQuery) {
			return $criteria;
		}
		$query = new WorkflowsQuery();
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
	 * @return    Workflows|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the parent column
	 * 
	 * @param     int|array $parent The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowsQuery The current query, for fluid interface
	 */
	public function filterByParent($parent = null, $comparison = null)
	{
		if (is_array($parent)) {
			$useMinMax = false;
			if (isset($parent['min'])) {
				$this->addUsingAlias(WorkflowsPeer::PARENT, $parent['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($parent['max'])) {
				$this->addUsingAlias(WorkflowsPeer::PARENT, $parent['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowsPeer::PARENT, $parent, $comparison);
	}

	/**
	 * Filter the query on the tool column
	 * 
	 * @param     string $tool The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(WorkflowsPeer::TOOL, $tool, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflows $workflows Object to remove from the list of results
	 *
	 * @return    WorkflowsQuery The current query, for fluid interface
	 */
	public function prune($workflows = null)
	{
		if ($workflows) {
			$this->addUsingAlias(WorkflowsPeer::ID, $workflows->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowsQuery
