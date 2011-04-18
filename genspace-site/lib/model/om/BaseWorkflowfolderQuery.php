<?php


/**
 * Base class that represents a query for the 'WORKFLOWFOLDER' table.
 *
 * 
 *
 * @method     WorkflowfolderQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     WorkflowfolderQuery orderByName($order = Criteria::ASC) Order by the NAME column
 * @method     WorkflowfolderQuery orderByOwnerId($order = Criteria::ASC) Order by the OWNER_ID column
 * @method     WorkflowfolderQuery orderByParentId($order = Criteria::ASC) Order by the PARENT_ID column
 *
 * @method     WorkflowfolderQuery groupById() Group by the ID column
 * @method     WorkflowfolderQuery groupByName() Group by the NAME column
 * @method     WorkflowfolderQuery groupByOwnerId() Group by the OWNER_ID column
 * @method     WorkflowfolderQuery groupByParentId() Group by the PARENT_ID column
 *
 * @method     WorkflowfolderQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowfolderQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowfolderQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Workflowfolder findOne(PropelPDO $con = null) Return the first Workflowfolder matching the query
 * @method     Workflowfolder findOneOrCreate(PropelPDO $con = null) Return the first Workflowfolder matching the query, or a new Workflowfolder object populated from the query conditions when no match is found
 *
 * @method     Workflowfolder findOneById(int $ID) Return the first Workflowfolder filtered by the ID column
 * @method     Workflowfolder findOneByName(string $NAME) Return the first Workflowfolder filtered by the NAME column
 * @method     Workflowfolder findOneByOwnerId(int $OWNER_ID) Return the first Workflowfolder filtered by the OWNER_ID column
 * @method     Workflowfolder findOneByParentId(int $PARENT_ID) Return the first Workflowfolder filtered by the PARENT_ID column
 *
 * @method     array findById(int $ID) Return Workflowfolder objects filtered by the ID column
 * @method     array findByName(string $NAME) Return Workflowfolder objects filtered by the NAME column
 * @method     array findByOwnerId(int $OWNER_ID) Return Workflowfolder objects filtered by the OWNER_ID column
 * @method     array findByParentId(int $PARENT_ID) Return Workflowfolder objects filtered by the PARENT_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowfolderQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowfolderQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflowfolder', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowfolderQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowfolderQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowfolderQuery) {
			return $criteria;
		}
		$query = new WorkflowfolderQuery();
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
	 * @return    Workflowfolder|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowfolderPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowfolderQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowfolderPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowfolderQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowfolderPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowfolderQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowfolderPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the NAME column
	 * 
	 * @param     string $name The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowfolderQuery The current query, for fluid interface
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
		return $this->addUsingAlias(WorkflowfolderPeer::NAME, $name, $comparison);
	}

	/**
	 * Filter the query on the OWNER_ID column
	 * 
	 * @param     int|array $ownerId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowfolderQuery The current query, for fluid interface
	 */
	public function filterByOwnerId($ownerId = null, $comparison = null)
	{
		if (is_array($ownerId)) {
			$useMinMax = false;
			if (isset($ownerId['min'])) {
				$this->addUsingAlias(WorkflowfolderPeer::OWNER_ID, $ownerId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($ownerId['max'])) {
				$this->addUsingAlias(WorkflowfolderPeer::OWNER_ID, $ownerId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowfolderPeer::OWNER_ID, $ownerId, $comparison);
	}

	/**
	 * Filter the query on the PARENT_ID column
	 * 
	 * @param     int|array $parentId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowfolderQuery The current query, for fluid interface
	 */
	public function filterByParentId($parentId = null, $comparison = null)
	{
		if (is_array($parentId)) {
			$useMinMax = false;
			if (isset($parentId['min'])) {
				$this->addUsingAlias(WorkflowfolderPeer::PARENT_ID, $parentId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($parentId['max'])) {
				$this->addUsingAlias(WorkflowfolderPeer::PARENT_ID, $parentId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowfolderPeer::PARENT_ID, $parentId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflowfolder $workflowfolder Object to remove from the list of results
	 *
	 * @return    WorkflowfolderQuery The current query, for fluid interface
	 */
	public function prune($workflowfolder = null)
	{
		if ($workflowfolder) {
			$this->addUsingAlias(WorkflowfolderPeer::ID, $workflowfolder->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowfolderQuery
