<?php


/**
 * Base class that represents a query for the 'WORKFLOW' table.
 *
 * 
 *
 * @method     WorkflowQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     WorkflowQuery orderByUsagecount($order = Criteria::ASC) Order by the USAGECOUNT column
 * @method     WorkflowQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     WorkflowQuery orderByCreatorId($order = Criteria::ASC) Order by the CREATOR_ID column
 * @method     WorkflowQuery orderByParentId($order = Criteria::ASC) Order by the PARENT_ID column
 * @method     WorkflowQuery orderByCreationtransactionId($order = Criteria::ASC) Order by the CREATIONTRANSACTION_ID column
 * @method     WorkflowQuery orderByNumrating($order = Criteria::ASC) Order by the NUMRATING column
 * @method     WorkflowQuery orderBySumrating($order = Criteria::ASC) Order by the SUMRATING column
 * @method     WorkflowQuery orderByLegacyId($order = Criteria::ASC) Order by the legacy_id column
 *
 * @method     WorkflowQuery groupById() Group by the ID column
 * @method     WorkflowQuery groupByUsagecount() Group by the USAGECOUNT column
 * @method     WorkflowQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     WorkflowQuery groupByCreatorId() Group by the CREATOR_ID column
 * @method     WorkflowQuery groupByParentId() Group by the PARENT_ID column
 * @method     WorkflowQuery groupByCreationtransactionId() Group by the CREATIONTRANSACTION_ID column
 * @method     WorkflowQuery groupByNumrating() Group by the NUMRATING column
 * @method     WorkflowQuery groupBySumrating() Group by the SUMRATING column
 * @method     WorkflowQuery groupByLegacyId() Group by the legacy_id column
 *
 * @method     WorkflowQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkflowQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkflowQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Workflow findOne(PropelPDO $con = null) Return the first Workflow matching the query
 * @method     Workflow findOneOrCreate(PropelPDO $con = null) Return the first Workflow matching the query, or a new Workflow object populated from the query conditions when no match is found
 *
 * @method     Workflow findOneById(int $ID) Return the first Workflow filtered by the ID column
 * @method     Workflow findOneByUsagecount(int $USAGECOUNT) Return the first Workflow filtered by the USAGECOUNT column
 * @method     Workflow findOneByCreatedat(string $CREATEDAT) Return the first Workflow filtered by the CREATEDAT column
 * @method     Workflow findOneByCreatorId(int $CREATOR_ID) Return the first Workflow filtered by the CREATOR_ID column
 * @method     Workflow findOneByParentId(int $PARENT_ID) Return the first Workflow filtered by the PARENT_ID column
 * @method     Workflow findOneByCreationtransactionId(int $CREATIONTRANSACTION_ID) Return the first Workflow filtered by the CREATIONTRANSACTION_ID column
 * @method     Workflow findOneByNumrating(int $NUMRATING) Return the first Workflow filtered by the NUMRATING column
 * @method     Workflow findOneBySumrating(int $SUMRATING) Return the first Workflow filtered by the SUMRATING column
 * @method     Workflow findOneByLegacyId(int $legacy_id) Return the first Workflow filtered by the legacy_id column
 *
 * @method     array findById(int $ID) Return Workflow objects filtered by the ID column
 * @method     array findByUsagecount(int $USAGECOUNT) Return Workflow objects filtered by the USAGECOUNT column
 * @method     array findByCreatedat(string $CREATEDAT) Return Workflow objects filtered by the CREATEDAT column
 * @method     array findByCreatorId(int $CREATOR_ID) Return Workflow objects filtered by the CREATOR_ID column
 * @method     array findByParentId(int $PARENT_ID) Return Workflow objects filtered by the PARENT_ID column
 * @method     array findByCreationtransactionId(int $CREATIONTRANSACTION_ID) Return Workflow objects filtered by the CREATIONTRANSACTION_ID column
 * @method     array findByNumrating(int $NUMRATING) Return Workflow objects filtered by the NUMRATING column
 * @method     array findBySumrating(int $SUMRATING) Return Workflow objects filtered by the SUMRATING column
 * @method     array findByLegacyId(int $legacy_id) Return Workflow objects filtered by the legacy_id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkflowQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkflowQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workflow', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkflowQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkflowQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkflowQuery) {
			return $criteria;
		}
		$query = new WorkflowQuery();
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
	 * @return    Workflow|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkflowPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkflowPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkflowPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkflowPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the USAGECOUNT column
	 * 
	 * @param     int|array $usagecount The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByUsagecount($usagecount = null, $comparison = null)
	{
		if (is_array($usagecount)) {
			$useMinMax = false;
			if (isset($usagecount['min'])) {
				$this->addUsingAlias(WorkflowPeer::USAGECOUNT, $usagecount['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($usagecount['max'])) {
				$this->addUsingAlias(WorkflowPeer::USAGECOUNT, $usagecount['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::USAGECOUNT, $usagecount, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(WorkflowPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(WorkflowPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the CREATOR_ID column
	 * 
	 * @param     int|array $creatorId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByCreatorId($creatorId = null, $comparison = null)
	{
		if (is_array($creatorId)) {
			$useMinMax = false;
			if (isset($creatorId['min'])) {
				$this->addUsingAlias(WorkflowPeer::CREATOR_ID, $creatorId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creatorId['max'])) {
				$this->addUsingAlias(WorkflowPeer::CREATOR_ID, $creatorId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::CREATOR_ID, $creatorId, $comparison);
	}

	/**
	 * Filter the query on the PARENT_ID column
	 * 
	 * @param     int|array $parentId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByParentId($parentId = null, $comparison = null)
	{
		if (is_array($parentId)) {
			$useMinMax = false;
			if (isset($parentId['min'])) {
				$this->addUsingAlias(WorkflowPeer::PARENT_ID, $parentId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($parentId['max'])) {
				$this->addUsingAlias(WorkflowPeer::PARENT_ID, $parentId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::PARENT_ID, $parentId, $comparison);
	}

	/**
	 * Filter the query on the CREATIONTRANSACTION_ID column
	 * 
	 * @param     int|array $creationtransactionId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByCreationtransactionId($creationtransactionId = null, $comparison = null)
	{
		if (is_array($creationtransactionId)) {
			$useMinMax = false;
			if (isset($creationtransactionId['min'])) {
				$this->addUsingAlias(WorkflowPeer::CREATIONTRANSACTION_ID, $creationtransactionId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creationtransactionId['max'])) {
				$this->addUsingAlias(WorkflowPeer::CREATIONTRANSACTION_ID, $creationtransactionId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::CREATIONTRANSACTION_ID, $creationtransactionId, $comparison);
	}

	/**
	 * Filter the query on the NUMRATING column
	 * 
	 * @param     int|array $numrating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByNumrating($numrating = null, $comparison = null)
	{
		if (is_array($numrating)) {
			$useMinMax = false;
			if (isset($numrating['min'])) {
				$this->addUsingAlias(WorkflowPeer::NUMRATING, $numrating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($numrating['max'])) {
				$this->addUsingAlias(WorkflowPeer::NUMRATING, $numrating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::NUMRATING, $numrating, $comparison);
	}

	/**
	 * Filter the query on the SUMRATING column
	 * 
	 * @param     int|array $sumrating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterBySumrating($sumrating = null, $comparison = null)
	{
		if (is_array($sumrating)) {
			$useMinMax = false;
			if (isset($sumrating['min'])) {
				$this->addUsingAlias(WorkflowPeer::SUMRATING, $sumrating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($sumrating['max'])) {
				$this->addUsingAlias(WorkflowPeer::SUMRATING, $sumrating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::SUMRATING, $sumrating, $comparison);
	}

	/**
	 * Filter the query on the legacy_id column
	 * 
	 * @param     int|array $legacyId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function filterByLegacyId($legacyId = null, $comparison = null)
	{
		if (is_array($legacyId)) {
			$useMinMax = false;
			if (isset($legacyId['min'])) {
				$this->addUsingAlias(WorkflowPeer::LEGACY_ID, $legacyId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($legacyId['max'])) {
				$this->addUsingAlias(WorkflowPeer::LEGACY_ID, $legacyId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkflowPeer::LEGACY_ID, $legacyId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Workflow $workflow Object to remove from the list of results
	 *
	 * @return    WorkflowQuery The current query, for fluid interface
	 */
	public function prune($workflow = null)
	{
		if ($workflow) {
			$this->addUsingAlias(WorkflowPeer::ID, $workflow->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkflowQuery
