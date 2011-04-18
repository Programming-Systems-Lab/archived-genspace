<?php


/**
 * Base class that represents a query for the 'ANALYSISEVENT' table.
 *
 * 
 *
 * @method     AnalysiseventQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     AnalysiseventQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     AnalysiseventQuery orderByTransactionId($order = Criteria::ASC) Order by the TRANSACTION_ID column
 * @method     AnalysiseventQuery orderByToolId($order = Criteria::ASC) Order by the TOOL_ID column
 *
 * @method     AnalysiseventQuery groupById() Group by the ID column
 * @method     AnalysiseventQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     AnalysiseventQuery groupByTransactionId() Group by the TRANSACTION_ID column
 * @method     AnalysiseventQuery groupByToolId() Group by the TOOL_ID column
 *
 * @method     AnalysiseventQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     AnalysiseventQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     AnalysiseventQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Analysisevent findOne(PropelPDO $con = null) Return the first Analysisevent matching the query
 * @method     Analysisevent findOneOrCreate(PropelPDO $con = null) Return the first Analysisevent matching the query, or a new Analysisevent object populated from the query conditions when no match is found
 *
 * @method     Analysisevent findOneById(int $ID) Return the first Analysisevent filtered by the ID column
 * @method     Analysisevent findOneByCreatedat(string $CREATEDAT) Return the first Analysisevent filtered by the CREATEDAT column
 * @method     Analysisevent findOneByTransactionId(int $TRANSACTION_ID) Return the first Analysisevent filtered by the TRANSACTION_ID column
 * @method     Analysisevent findOneByToolId(int $TOOL_ID) Return the first Analysisevent filtered by the TOOL_ID column
 *
 * @method     array findById(int $ID) Return Analysisevent objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Analysisevent objects filtered by the CREATEDAT column
 * @method     array findByTransactionId(int $TRANSACTION_ID) Return Analysisevent objects filtered by the TRANSACTION_ID column
 * @method     array findByToolId(int $TOOL_ID) Return Analysisevent objects filtered by the TOOL_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseAnalysiseventQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseAnalysiseventQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Analysisevent', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new AnalysiseventQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    AnalysiseventQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof AnalysiseventQuery) {
			return $criteria;
		}
		$query = new AnalysiseventQuery();
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
	 * @return    Analysisevent|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = AnalysiseventPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(AnalysiseventPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(AnalysiseventPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(AnalysiseventPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(AnalysiseventPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(AnalysiseventPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnalysiseventPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the TRANSACTION_ID column
	 * 
	 * @param     int|array $transactionId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function filterByTransactionId($transactionId = null, $comparison = null)
	{
		if (is_array($transactionId)) {
			$useMinMax = false;
			if (isset($transactionId['min'])) {
				$this->addUsingAlias(AnalysiseventPeer::TRANSACTION_ID, $transactionId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($transactionId['max'])) {
				$this->addUsingAlias(AnalysiseventPeer::TRANSACTION_ID, $transactionId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnalysiseventPeer::TRANSACTION_ID, $transactionId, $comparison);
	}

	/**
	 * Filter the query on the TOOL_ID column
	 * 
	 * @param     int|array $toolId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function filterByToolId($toolId = null, $comparison = null)
	{
		if (is_array($toolId)) {
			$useMinMax = false;
			if (isset($toolId['min'])) {
				$this->addUsingAlias(AnalysiseventPeer::TOOL_ID, $toolId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($toolId['max'])) {
				$this->addUsingAlias(AnalysiseventPeer::TOOL_ID, $toolId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnalysiseventPeer::TOOL_ID, $toolId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Analysisevent $analysisevent Object to remove from the list of results
	 *
	 * @return    AnalysiseventQuery The current query, for fluid interface
	 */
	public function prune($analysisevent = null)
	{
		if ($analysisevent) {
			$this->addUsingAlias(AnalysiseventPeer::ID, $analysisevent->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseAnalysiseventQuery
