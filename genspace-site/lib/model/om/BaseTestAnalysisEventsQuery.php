<?php


/**
 * Base class that represents a query for the 'test_analysis_events' table.
 *
 * 
 *
 * @method     TestAnalysisEventsQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     TestAnalysisEventsQuery orderByHost($order = Criteria::ASC) Order by the host column
 * @method     TestAnalysisEventsQuery orderByDate($order = Criteria::ASC) Order by the date column
 * @method     TestAnalysisEventsQuery orderByAnalysis($order = Criteria::ASC) Order by the analysis column
 * @method     TestAnalysisEventsQuery orderByDataset($order = Criteria::ASC) Order by the dataset column
 * @method     TestAnalysisEventsQuery orderByTransactionId($order = Criteria::ASC) Order by the transaction_id column
 * @method     TestAnalysisEventsQuery orderByIsGenspaceUser($order = Criteria::ASC) Order by the is_genspace_user column
 * @method     TestAnalysisEventsQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     TestAnalysisEventsQuery groupByUsername() Group by the username column
 * @method     TestAnalysisEventsQuery groupByHost() Group by the host column
 * @method     TestAnalysisEventsQuery groupByDate() Group by the date column
 * @method     TestAnalysisEventsQuery groupByAnalysis() Group by the analysis column
 * @method     TestAnalysisEventsQuery groupByDataset() Group by the dataset column
 * @method     TestAnalysisEventsQuery groupByTransactionId() Group by the transaction_id column
 * @method     TestAnalysisEventsQuery groupByIsGenspaceUser() Group by the is_genspace_user column
 * @method     TestAnalysisEventsQuery groupById() Group by the id column
 *
 * @method     TestAnalysisEventsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     TestAnalysisEventsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     TestAnalysisEventsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     TestAnalysisEvents findOne(PropelPDO $con = null) Return the first TestAnalysisEvents matching the query
 * @method     TestAnalysisEvents findOneOrCreate(PropelPDO $con = null) Return the first TestAnalysisEvents matching the query, or a new TestAnalysisEvents object populated from the query conditions when no match is found
 *
 * @method     TestAnalysisEvents findOneByUsername(string $username) Return the first TestAnalysisEvents filtered by the username column
 * @method     TestAnalysisEvents findOneByHost(string $host) Return the first TestAnalysisEvents filtered by the host column
 * @method     TestAnalysisEvents findOneByDate(string $date) Return the first TestAnalysisEvents filtered by the date column
 * @method     TestAnalysisEvents findOneByAnalysis(string $analysis) Return the first TestAnalysisEvents filtered by the analysis column
 * @method     TestAnalysisEvents findOneByDataset(string $dataset) Return the first TestAnalysisEvents filtered by the dataset column
 * @method     TestAnalysisEvents findOneByTransactionId(string $transaction_id) Return the first TestAnalysisEvents filtered by the transaction_id column
 * @method     TestAnalysisEvents findOneByIsGenspaceUser(string $is_genspace_user) Return the first TestAnalysisEvents filtered by the is_genspace_user column
 * @method     TestAnalysisEvents findOneById(int $id) Return the first TestAnalysisEvents filtered by the id column
 *
 * @method     array findByUsername(string $username) Return TestAnalysisEvents objects filtered by the username column
 * @method     array findByHost(string $host) Return TestAnalysisEvents objects filtered by the host column
 * @method     array findByDate(string $date) Return TestAnalysisEvents objects filtered by the date column
 * @method     array findByAnalysis(string $analysis) Return TestAnalysisEvents objects filtered by the analysis column
 * @method     array findByDataset(string $dataset) Return TestAnalysisEvents objects filtered by the dataset column
 * @method     array findByTransactionId(string $transaction_id) Return TestAnalysisEvents objects filtered by the transaction_id column
 * @method     array findByIsGenspaceUser(string $is_genspace_user) Return TestAnalysisEvents objects filtered by the is_genspace_user column
 * @method     array findById(int $id) Return TestAnalysisEvents objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTestAnalysisEventsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseTestAnalysisEventsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'TestAnalysisEvents', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new TestAnalysisEventsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    TestAnalysisEventsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof TestAnalysisEventsQuery) {
			return $criteria;
		}
		$query = new TestAnalysisEventsQuery();
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
	 * @return    TestAnalysisEvents|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = TestAnalysisEventsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(TestAnalysisEventsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(TestAnalysisEventsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(TestAnalysisEventsPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the host column
	 * 
	 * @param     string $host The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(TestAnalysisEventsPeer::HOST, $host, $comparison);
	}

	/**
	 * Filter the query on the date column
	 * 
	 * @param     string|array $date The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterByDate($date = null, $comparison = null)
	{
		if (is_array($date)) {
			$useMinMax = false;
			if (isset($date['min'])) {
				$this->addUsingAlias(TestAnalysisEventsPeer::DATE, $date['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($date['max'])) {
				$this->addUsingAlias(TestAnalysisEventsPeer::DATE, $date['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TestAnalysisEventsPeer::DATE, $date, $comparison);
	}

	/**
	 * Filter the query on the analysis column
	 * 
	 * @param     string $analysis The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(TestAnalysisEventsPeer::ANALYSIS, $analysis, $comparison);
	}

	/**
	 * Filter the query on the dataset column
	 * 
	 * @param     string $dataset The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(TestAnalysisEventsPeer::DATASET, $dataset, $comparison);
	}

	/**
	 * Filter the query on the transaction_id column
	 * 
	 * @param     string $transactionId The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(TestAnalysisEventsPeer::TRANSACTION_ID, $transactionId, $comparison);
	}

	/**
	 * Filter the query on the is_genspace_user column
	 * 
	 * @param     string $isGenspaceUser The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(TestAnalysisEventsPeer::IS_GENSPACE_USER, $isGenspaceUser, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(TestAnalysisEventsPeer::ID, $id, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     TestAnalysisEvents $testAnalysisEvents Object to remove from the list of results
	 *
	 * @return    TestAnalysisEventsQuery The current query, for fluid interface
	 */
	public function prune($testAnalysisEvents = null)
	{
		if ($testAnalysisEvents) {
			$this->addUsingAlias(TestAnalysisEventsPeer::ID, $testAnalysisEvents->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseTestAnalysisEventsQuery
