<?php


/**
 * Base class that represents a query for the 'TRANSACTION' table.
 *
 * 
 *
 * @method     TransactionQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     TransactionQuery orderByDatasetname($order = Criteria::ASC) Order by the DATASETNAME column
 * @method     TransactionQuery orderByClientid($order = Criteria::ASC) Order by the CLIENTID column
 * @method     TransactionQuery orderByHostname($order = Criteria::ASC) Order by the HOSTNAME column
 * @method     TransactionQuery orderByDate($order = Criteria::ASC) Order by the DATE column
 * @method     TransactionQuery orderByUserId($order = Criteria::ASC) Order by the USER_ID column
 * @method     TransactionQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 *
 * @method     TransactionQuery groupById() Group by the ID column
 * @method     TransactionQuery groupByDatasetname() Group by the DATASETNAME column
 * @method     TransactionQuery groupByClientid() Group by the CLIENTID column
 * @method     TransactionQuery groupByHostname() Group by the HOSTNAME column
 * @method     TransactionQuery groupByDate() Group by the DATE column
 * @method     TransactionQuery groupByUserId() Group by the USER_ID column
 * @method     TransactionQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 *
 * @method     TransactionQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     TransactionQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     TransactionQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Transaction findOne(PropelPDO $con = null) Return the first Transaction matching the query
 * @method     Transaction findOneOrCreate(PropelPDO $con = null) Return the first Transaction matching the query, or a new Transaction object populated from the query conditions when no match is found
 *
 * @method     Transaction findOneById(int $ID) Return the first Transaction filtered by the ID column
 * @method     Transaction findOneByDatasetname(string $DATASETNAME) Return the first Transaction filtered by the DATASETNAME column
 * @method     Transaction findOneByClientid(string $CLIENTID) Return the first Transaction filtered by the CLIENTID column
 * @method     Transaction findOneByHostname(string $HOSTNAME) Return the first Transaction filtered by the HOSTNAME column
 * @method     Transaction findOneByDate(string $DATE) Return the first Transaction filtered by the DATE column
 * @method     Transaction findOneByUserId(int $USER_ID) Return the first Transaction filtered by the USER_ID column
 * @method     Transaction findOneByWorkflowId(int $WORKFLOW_ID) Return the first Transaction filtered by the WORKFLOW_ID column
 *
 * @method     array findById(int $ID) Return Transaction objects filtered by the ID column
 * @method     array findByDatasetname(string $DATASETNAME) Return Transaction objects filtered by the DATASETNAME column
 * @method     array findByClientid(string $CLIENTID) Return Transaction objects filtered by the CLIENTID column
 * @method     array findByHostname(string $HOSTNAME) Return Transaction objects filtered by the HOSTNAME column
 * @method     array findByDate(string $DATE) Return Transaction objects filtered by the DATE column
 * @method     array findByUserId(int $USER_ID) Return Transaction objects filtered by the USER_ID column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Transaction objects filtered by the WORKFLOW_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTransactionQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseTransactionQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Transaction', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new TransactionQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    TransactionQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof TransactionQuery) {
			return $criteria;
		}
		$query = new TransactionQuery();
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
	 * @return    Transaction|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = TransactionPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(TransactionPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(TransactionPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(TransactionPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the DATASETNAME column
	 * 
	 * @param     string $datasetname The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByDatasetname($datasetname = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($datasetname)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $datasetname)) {
				$datasetname = str_replace('*', '%', $datasetname);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TransactionPeer::DATASETNAME, $datasetname, $comparison);
	}

	/**
	 * Filter the query on the CLIENTID column
	 * 
	 * @param     string $clientid The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByClientid($clientid = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($clientid)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $clientid)) {
				$clientid = str_replace('*', '%', $clientid);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TransactionPeer::CLIENTID, $clientid, $comparison);
	}

	/**
	 * Filter the query on the HOSTNAME column
	 * 
	 * @param     string $hostname The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByHostname($hostname = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($hostname)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $hostname)) {
				$hostname = str_replace('*', '%', $hostname);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TransactionPeer::HOSTNAME, $hostname, $comparison);
	}

	/**
	 * Filter the query on the DATE column
	 * 
	 * @param     string|array $date The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByDate($date = null, $comparison = null)
	{
		if (is_array($date)) {
			$useMinMax = false;
			if (isset($date['min'])) {
				$this->addUsingAlias(TransactionPeer::DATE, $date['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($date['max'])) {
				$this->addUsingAlias(TransactionPeer::DATE, $date['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TransactionPeer::DATE, $date, $comparison);
	}

	/**
	 * Filter the query on the USER_ID column
	 * 
	 * @param     int|array $userId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByUserId($userId = null, $comparison = null)
	{
		if (is_array($userId)) {
			$useMinMax = false;
			if (isset($userId['min'])) {
				$this->addUsingAlias(TransactionPeer::USER_ID, $userId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($userId['max'])) {
				$this->addUsingAlias(TransactionPeer::USER_ID, $userId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TransactionPeer::USER_ID, $userId, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(TransactionPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(TransactionPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TransactionPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Transaction $transaction Object to remove from the list of results
	 *
	 * @return    TransactionQuery The current query, for fluid interface
	 */
	public function prune($transaction = null)
	{
		if ($transaction) {
			$this->addUsingAlias(TransactionPeer::ID, $transaction->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseTransactionQuery
