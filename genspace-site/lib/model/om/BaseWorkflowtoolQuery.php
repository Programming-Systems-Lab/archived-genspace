<?php


/**
 * Base class that represents a query for the 'WORKFLOWTOOL' table.
 *
 * 
 *
 * @method     WorkflowtoolQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     WorkflowtoolQuery orderByCardinality($order = Criteria::ASC) Order by the CARDINALITY column
 * @method     WorkflowtoolQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 * @method     WorkflowtoolQuery orderByToolId($order = Criteria::ASC) Order by the TOOL_ID column
 *
 * @method     WorkflowtoolQuery groupById() Group by the ID column
 * @method     WorkflowtoolQuery groupByCardinality() Group by the CARDINALITY column
 * @method     WorkflowtoolQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 * @method     WorkflowtoolQuery groupByToolId() Group by the TOOL_ID column
 *
 * @method     WorkflowtoolQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowtoolQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowtoolQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Workflowtool findOne(PropelPDO $con = null) Return the first Workflowtool matching the query
 * @method     Workflowtool findOneOrCreate(PropelPDO $con = null) Return the first Workflowtool matching the query, or a new Workflowtool object populated from the query conditions when no match is found
 *
 * @method     Workflowtool findOneById(int $ID) Return the first Workflowtool filtered by the ID column
 * @method     Workflowtool findOneByCardinality(int $CARDINALITY) Return the first Workflowtool filtered by the CARDINALITY column
 * @method     Workflowtool findOneByWorkflowId(int $WORKFLOW_ID) Return the first Workflowtool filtered by the WORKFLOW_ID column
 * @method     Workflowtool findOneByToolId(int $TOOL_ID) Return the first Workflowtool filtered by the TOOL_ID column
 *
 * @method     array findById(int $ID) Return Workflowtool objects filtered by the ID column
 * @method     array findByCardinality(int $CARDINALITY) Return Workflowtool objects filtered by the CARDINALITY column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Workflowtool objects filtered by the WORKFLOW_ID column
 * @method     array findByToolId(int $TOOL_ID) Return Workflowtool objects filtered by the TOOL_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowtoolQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowtoolQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflowtool', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowtoolQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowtoolQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowtoolQuery) {
			return $criteria;
		}
		$query = new WorkflowtoolQuery();
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
	 * @return    Workflowtool|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowtoolPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowtoolPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowtoolPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowtoolPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CARDINALITY column
	 * 
	 * @param     int|array $cardinality The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function filterByCardinality($cardinality = null, $comparison = null)
	{
		if (is_array($cardinality)) {
			$useMinMax = false;
			if (isset($cardinality['min'])) {
				$this->addUsingAlias(WorkflowtoolPeer::CARDINALITY, $cardinality['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($cardinality['max'])) {
				$this->addUsingAlias(WorkflowtoolPeer::CARDINALITY, $cardinality['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowtoolPeer::CARDINALITY, $cardinality, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(WorkflowtoolPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(WorkflowtoolPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowtoolPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Filter the query on the TOOL_ID column
	 * 
	 * @param     int|array $toolId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function filterByToolId($toolId = null, $comparison = null)
	{
		if (is_array($toolId)) {
			$useMinMax = false;
			if (isset($toolId['min'])) {
				$this->addUsingAlias(WorkflowtoolPeer::TOOL_ID, $toolId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($toolId['max'])) {
				$this->addUsingAlias(WorkflowtoolPeer::TOOL_ID, $toolId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowtoolPeer::TOOL_ID, $toolId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflowtool $workflowtool Object to remove from the list of results
	 *
	 * @return    WorkflowtoolQuery The current query, for fluid interface
	 */
	public function prune($workflowtool = null)
	{
		if ($workflowtool) {
			$this->addUsingAlias(WorkflowtoolPeer::ID, $workflowtool->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowtoolQuery
