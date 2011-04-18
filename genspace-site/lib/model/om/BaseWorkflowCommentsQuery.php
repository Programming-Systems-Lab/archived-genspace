<?php


/**
 * Base class that represents a query for the 'WORKFLOWCOMMENT' table.
 *
 * 
 *
 * @method     WorkflowcommentsQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     WorkflowcommentsQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     WorkflowcommentsQuery orderByComment($order = Criteria::ASC) Order by the COMMENT column
 * @method     WorkflowcommentsQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 * @method     WorkflowcommentsQuery orderByCreatorId($order = Criteria::ASC) Order by the CREATOR_ID column
 *
 * @method     WorkflowcommentsQuery groupById() Group by the ID column
 * @method     WorkflowcommentsQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     WorkflowcommentsQuery groupByComment() Group by the COMMENT column
 * @method     WorkflowcommentsQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 * @method     WorkflowcommentsQuery groupByCreatorId() Group by the CREATOR_ID column
 *
 * @method     WorkflowcommentsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowcommentsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowcommentsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     WorkflowcommentsQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     WorkflowcommentsQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     WorkflowcommentsQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     Workflowcomments findOne(PropelPDO $con = null) Return the first Workflowcomments matching the query
 * @method     Workflowcomments findOneOrCreate(PropelPDO $con = null) Return the first Workflowcomments matching the query, or a new Workflowcomments object populated from the query conditions when no match is found
 *
 * @method     Workflowcomments findOneById(int $ID) Return the first Workflowcomments filtered by the ID column
 * @method     Workflowcomments findOneByCreatedat(string $CREATEDAT) Return the first Workflowcomments filtered by the CREATEDAT column
 * @method     Workflowcomments findOneByComment(string $COMMENT) Return the first Workflowcomments filtered by the COMMENT column
 * @method     Workflowcomments findOneByWorkflowId(int $WORKFLOW_ID) Return the first Workflowcomments filtered by the WORKFLOW_ID column
 * @method     Workflowcomments findOneByCreatorId(int $CREATOR_ID) Return the first Workflowcomments filtered by the CREATOR_ID column
 *
 * @method     array findById(int $ID) Return Workflowcomments objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Workflowcomments objects filtered by the CREATEDAT column
 * @method     array findByComment(string $COMMENT) Return Workflowcomments objects filtered by the COMMENT column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Workflowcomments objects filtered by the WORKFLOW_ID column
 * @method     array findByCreatorId(int $CREATOR_ID) Return Workflowcomments objects filtered by the CREATOR_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowcommentsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowcommentsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflowcomments', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowcommentsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowcommentsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowcommentsQuery) {
			return $criteria;
		}
		$query = new WorkflowcommentsQuery();
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
	 * @return    Workflowcomments|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowcommentsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowcommentsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowcommentsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowcommentsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(WorkflowcommentsPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(WorkflowcommentsPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowcommentsPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the COMMENT column
	 * 
	 * @param     string $comment The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(WorkflowcommentsPeer::COMMENT, $comment, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(WorkflowcommentsPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(WorkflowcommentsPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowcommentsPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Filter the query on the CREATOR_ID column
	 * 
	 * @param     int|array $creatorId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterByCreatorId($creatorId = null, $comparison = null)
	{
		if (is_array($creatorId)) {
			$useMinMax = false;
			if (isset($creatorId['min'])) {
				$this->addUsingAlias(WorkflowcommentsPeer::CREATOR_ID, $creatorId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creatorId['max'])) {
				$this->addUsingAlias(WorkflowcommentsPeer::CREATOR_ID, $creatorId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowcommentsPeer::CREATOR_ID, $creatorId, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkflowcommentsPeer::CREATOR_ID, $registration->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function joinRegistration($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Registration');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'Registration');
		}
		
		return $this;
	}

	/**
	 * Use the Registration relation Registration object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery A secondary query class using the current class as primary query
	 */
	public function useRegistrationQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinRegistration($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Registration', 'RegistrationQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflowcomments $workflowcomments Object to remove from the list of results
	 *
	 * @return    WorkflowcommentsQuery The current query, for fluid interface
	 */
	public function prune($workflowcomments = null)
	{
		if ($workflowcomments) {
			$this->addUsingAlias(WorkflowcommentsPeer::ID, $workflowcomments->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowcommentsQuery
