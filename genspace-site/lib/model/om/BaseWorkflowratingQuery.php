<?php


/**
 * Base class that represents a query for the 'WORKFLOWRATING' table.
 *
 * 
 *
 * @method     WorkflowratingQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     WorkflowratingQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     WorkflowratingQuery orderByRating($order = Criteria::ASC) Order by the RATING column
 * @method     WorkflowratingQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 * @method     WorkflowratingQuery orderByCreatorId($order = Criteria::ASC) Order by the CREATOR_ID column
 *
 * @method     WorkflowratingQuery groupById() Group by the ID column
 * @method     WorkflowratingQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     WorkflowratingQuery groupByRating() Group by the RATING column
 * @method     WorkflowratingQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 * @method     WorkflowratingQuery groupByCreatorId() Group by the CREATOR_ID column
 *
 * @method     WorkflowratingQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowratingQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowratingQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Workflowrating findOne(PropelPDO $con = null) Return the first Workflowrating matching the query
 * @method     Workflowrating findOneOrCreate(PropelPDO $con = null) Return the first Workflowrating matching the query, or a new Workflowrating object populated from the query conditions when no match is found
 *
 * @method     Workflowrating findOneById(int $ID) Return the first Workflowrating filtered by the ID column
 * @method     Workflowrating findOneByCreatedat(string $CREATEDAT) Return the first Workflowrating filtered by the CREATEDAT column
 * @method     Workflowrating findOneByRating(int $RATING) Return the first Workflowrating filtered by the RATING column
 * @method     Workflowrating findOneByWorkflowId(int $WORKFLOW_ID) Return the first Workflowrating filtered by the WORKFLOW_ID column
 * @method     Workflowrating findOneByCreatorId(int $CREATOR_ID) Return the first Workflowrating filtered by the CREATOR_ID column
 *
 * @method     array findById(int $ID) Return Workflowrating objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Workflowrating objects filtered by the CREATEDAT column
 * @method     array findByRating(int $RATING) Return Workflowrating objects filtered by the RATING column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Workflowrating objects filtered by the WORKFLOW_ID column
 * @method     array findByCreatorId(int $CREATOR_ID) Return Workflowrating objects filtered by the CREATOR_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowratingQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowratingQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflowrating', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowratingQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowratingQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowratingQuery) {
			return $criteria;
		}
		$query = new WorkflowratingQuery();
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
	 * @return    Workflowrating|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowratingPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowratingPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowratingPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowratingPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(WorkflowratingPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(WorkflowratingPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowratingPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the RATING column
	 * 
	 * @param     int|array $rating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterByRating($rating = null, $comparison = null)
	{
		if (is_array($rating)) {
			$useMinMax = false;
			if (isset($rating['min'])) {
				$this->addUsingAlias(WorkflowratingPeer::RATING, $rating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($rating['max'])) {
				$this->addUsingAlias(WorkflowratingPeer::RATING, $rating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowratingPeer::RATING, $rating, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(WorkflowratingPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(WorkflowratingPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowratingPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Filter the query on the CREATOR_ID column
	 * 
	 * @param     int|array $creatorId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function filterByCreatorId($creatorId = null, $comparison = null)
	{
		if (is_array($creatorId)) {
			$useMinMax = false;
			if (isset($creatorId['min'])) {
				$this->addUsingAlias(WorkflowratingPeer::CREATOR_ID, $creatorId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creatorId['max'])) {
				$this->addUsingAlias(WorkflowratingPeer::CREATOR_ID, $creatorId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowratingPeer::CREATOR_ID, $creatorId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflowrating $workflowrating Object to remove from the list of results
	 *
	 * @return    WorkflowratingQuery The current query, for fluid interface
	 */
	public function prune($workflowrating = null)
	{
		if ($workflowrating) {
			$this->addUsingAlias(WorkflowratingPeer::ID, $workflowrating->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowratingQuery
