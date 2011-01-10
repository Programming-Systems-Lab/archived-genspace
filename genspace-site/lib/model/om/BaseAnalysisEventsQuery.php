<?php


/**
 * Base class that represents a query for the 'analysis_events' table.
 *
 * 
 *
 * @method     AnalysisEventsQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     AnalysisEventsQuery orderByHost($order = Criteria::ASC) Order by the host column
 * @method     AnalysisEventsQuery orderByDate($order = Criteria::ASC) Order by the date column
 * @method     AnalysisEventsQuery orderByAnalysis($order = Criteria::ASC) Order by the analysis column
 * @method     AnalysisEventsQuery orderByDataset($order = Criteria::ASC) Order by the dataset column
 * @method     AnalysisEventsQuery orderByTransactionId($order = Criteria::ASC) Order by the transaction_id column
 * @method     AnalysisEventsQuery orderByIsGenspaceUser($order = Criteria::ASC) Order by the is_genspace_user column
 * @method     AnalysisEventsQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     AnalysisEventsQuery groupByUsername() Group by the username column
 * @method     AnalysisEventsQuery groupByHost() Group by the host column
 * @method     AnalysisEventsQuery groupByDate() Group by the date column
 * @method     AnalysisEventsQuery groupByAnalysis() Group by the analysis column
 * @method     AnalysisEventsQuery groupByDataset() Group by the dataset column
 * @method     AnalysisEventsQuery groupByTransactionId() Group by the transaction_id column
 * @method     AnalysisEventsQuery groupByIsGenspaceUser() Group by the is_genspace_user column
 * @method     AnalysisEventsQuery groupById() Group by the id column
 *
 * @method     AnalysisEventsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     AnalysisEventsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     AnalysisEventsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     AnalysisEvents findOne(PropelPDO $con = null) Return the first AnalysisEvents matching the query
 * @method     AnalysisEvents findOneOrCreate(PropelPDO $con = null) Return the first AnalysisEvents matching the query, or a new AnalysisEvents object populated from the query conditions when no match is found
 *
 * @method     AnalysisEvents findOneByUsername(string $username) Return the first AnalysisEvents filtered by the username column
 * @method     AnalysisEvents findOneByHost(string $host) Return the first AnalysisEvents filtered by the host column
 * @method     AnalysisEvents findOneByDate(string $date) Return the first AnalysisEvents filtered by the date column
 * @method     AnalysisEvents findOneByAnalysis(string $analysis) Return the first AnalysisEvents filtered by the analysis column
 * @method     AnalysisEvents findOneByDataset(string $dataset) Return the first AnalysisEvents filtered by the dataset column
 * @method     AnalysisEvents findOneByTransactionId(string $transaction_id) Return the first AnalysisEvents filtered by the transaction_id column
 * @method     AnalysisEvents findOneByIsGenspaceUser(string $is_genspace_user) Return the first AnalysisEvents filtered by the is_genspace_user column
 * @method     AnalysisEvents findOneById(int $id) Return the first AnalysisEvents filtered by the id column
 *
 * @method     array findByUsername(string $username) Return AnalysisEvents objects filtered by the username column
 * @method     array findByHost(string $host) Return AnalysisEvents objects filtered by the host column
 * @method     array findByDate(string $date) Return AnalysisEvents objects filtered by the date column
 * @method     array findByAnalysis(string $analysis) Return AnalysisEvents objects filtered by the analysis column
 * @method     array findByDataset(string $dataset) Return AnalysisEvents objects filtered by the dataset column
 * @method     array findByTransactionId(string $transaction_id) Return AnalysisEvents objects filtered by the transaction_id column
 * @method     array findByIsGenspaceUser(string $is_genspace_user) Return AnalysisEvents objects filtered by the is_genspace_user column
 * @method     array findById(int $id) Return AnalysisEvents objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseAnalysisEventsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseAnalysisEventsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'AnalysisEvents', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new AnalysisEventsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    AnalysisEventsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof AnalysisEventsQuery) {
			return $criteria;
		}
		$query = new AnalysisEventsQuery();
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
	 * @return    AnalysisEvents|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = AnalysisEventsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(AnalysisEventsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(AnalysisEventsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(AnalysisEventsPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the host column
	 * 
	 * @param     string $host The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByHost($host = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($host)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $host)) {
				$host = str_replace('*', '%', $host);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnalysisEventsPeer::HOST, $host, $comparison);
	}

	/**
	 * Filter the query on the date column
	 * 
	 * @param     string|array $date The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByDate($date = null, $comparison = null)
	{
		if (is_array($date)) {
			$useMinMax = false;
			if (isset($date['min'])) {
				$this->addUsingAlias(AnalysisEventsPeer::DATE, $date['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($date['max'])) {
				$this->addUsingAlias(AnalysisEventsPeer::DATE, $date['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnalysisEventsPeer::DATE, $date, $comparison);
	}

	/**
	 * Filter the query on the analysis column
	 * 
	 * @param     string $analysis The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(AnalysisEventsPeer::ANALYSIS, $analysis, $comparison);
	}

	/**
	 * Filter the query on the dataset column
	 * 
	 * @param     string $dataset The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByDataset($dataset = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($dataset)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $dataset)) {
				$dataset = str_replace('*', '%', $dataset);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnalysisEventsPeer::DATASET, $dataset, $comparison);
	}

	/**
	 * Filter the query on the transaction_id column
	 * 
	 * @param     string $transactionId The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByTransactionId($transactionId = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($transactionId)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $transactionId)) {
				$transactionId = str_replace('*', '%', $transactionId);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnalysisEventsPeer::TRANSACTION_ID, $transactionId, $comparison);
	}

	/**
	 * Filter the query on the is_genspace_user column
	 * 
	 * @param     string $isGenspaceUser The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByIsGenspaceUser($isGenspaceUser = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($isGenspaceUser)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $isGenspaceUser)) {
				$isGenspaceUser = str_replace('*', '%', $isGenspaceUser);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnalysisEventsPeer::IS_GENSPACE_USER, $isGenspaceUser, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(AnalysisEventsPeer::ID, $id, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     AnalysisEvents $analysisEvents Object to remove from the list of results
	 *
	 * @return    AnalysisEventsQuery The current query, for fluid interface
	 */
	public function prune($analysisEvents = null)
	{
		if ($analysisEvents) {
			$this->addUsingAlias(AnalysisEventsPeer::ID, $analysisEvents->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseAnalysisEventsQuery
