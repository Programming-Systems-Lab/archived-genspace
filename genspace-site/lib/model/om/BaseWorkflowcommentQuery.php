<?php


/**
 * Base class that represents a query for the 'WORKFLOWCOMMENT' table.
 *
 * 
 *
 * @method     WorkflowcommentQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     WorkflowcommentQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     WorkflowcommentQuery orderByComment($order = Criteria::ASC) Order by the COMMENT column
 * @method     WorkflowcommentQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 * @method     WorkflowcommentQuery orderByCreatorId($order = Criteria::ASC) Order by the CREATOR_ID column
 *
 * @method     WorkflowcommentQuery groupById() Group by the ID column
 * @method     WorkflowcommentQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     WorkflowcommentQuery groupByComment() Group by the COMMENT column
 * @method     WorkflowcommentQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 * @method     WorkflowcommentQuery groupByCreatorId() Group by the CREATOR_ID column
 *
 * @method     WorkflowcommentQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowcommentQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowcommentQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Workflowcomment findOne(PropelPDO $con = null) Return the first Workflowcomment matching the query
 * @method     Workflowcomment findOneOrCreate(PropelPDO $con = null) Return the first Workflowcomment matching the query, or a new Workflowcomment object populated from the query conditions when no match is found
 *
 * @method     Workflowcomment findOneById(int $ID) Return the first Workflowcomment filtered by the ID column
 * @method     Workflowcomment findOneByCreatedat(string $CREATEDAT) Return the first Workflowcomment filtered by the CREATEDAT column
 * @method     Workflowcomment findOneByComment(string $COMMENT) Return the first Workflowcomment filtered by the COMMENT column
 * @method     Workflowcomment findOneByWorkflowId(int $WORKFLOW_ID) Return the first Workflowcomment filtered by the WORKFLOW_ID column
 * @method     Workflowcomment findOneByCreatorId(int $CREATOR_ID) Return the first Workflowcomment filtered by the CREATOR_ID column
 *
 * @method     array findById(int $ID) Return Workflowcomment objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Workflowcomment objects filtered by the CREATEDAT column
 * @method     array findByComment(string $COMMENT) Return Workflowcomment objects filtered by the COMMENT column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Workflowcomment objects filtered by the WORKFLOW_ID column
 * @method     array findByCreatorId(int $CREATOR_ID) Return Workflowcomment objects filtered by the CREATOR_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowcommentQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowcommentQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflowcomment', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowcommentQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowcommentQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowcommentQuery) {
			return $criteria;
		}
		$query = new WorkflowcommentQuery();
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
	 * @return    Workflowcomment|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowcommentPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowcommentPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowcommentPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowcommentPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(WorkflowcommentPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(WorkflowcommentPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowcommentPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the COMMENT column
	 * 
	 * @param     string $comment The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterByComment($comment = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($comment)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $comment)) {
				$comment = str_replace('*', '%', $comment);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(WorkflowcommentPeer::COMMENT, $comment, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(WorkflowcommentPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(WorkflowcommentPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowcommentPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Filter the query on the CREATOR_ID column
	 * 
	 * @param     int|array $creatorId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function filterByCreatorId($creatorId = null, $comparison = null)
	{
		if (is_array($creatorId)) {
			$useMinMax = false;
			if (isset($creatorId['min'])) {
				$this->addUsingAlias(WorkflowcommentPeer::CREATOR_ID, $creatorId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creatorId['max'])) {
				$this->addUsingAlias(WorkflowcommentPeer::CREATOR_ID, $creatorId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowcommentPeer::CREATOR_ID, $creatorId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflowcomment $workflowcomment Object to remove from the list of results
	 *
	 * @return    WorkflowcommentQuery The current query, for fluid interface
	 */
	public function prune($workflowcomment = null)
	{
		if ($workflowcomment) {
			$this->addUsingAlias(WorkflowcommentPeer::ID, $workflowcomment->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowcommentQuery
