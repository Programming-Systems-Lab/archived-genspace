<?php


/**
 * Base class that represents a query for the 'TOOL' table.
 *
 * 
 *
 * @method     ToolQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     ToolQuery orderByMostcommonparameters($order = Criteria::ASC) Order by the MOSTCOMMONPARAMETERS column
 * @method     ToolQuery orderByUsagecount($order = Criteria::ASC) Order by the USAGECOUNT column
 * @method     ToolQuery orderByDescription($order = Criteria::ASC) Order by the DESCRIPTION column
 * @method     ToolQuery orderByName($order = Criteria::ASC) Order by the NAME column
 * @method     ToolQuery orderByMostcommonparameterscount($order = Criteria::ASC) Order by the MOSTCOMMONPARAMETERSCOUNT column
 * @method     ToolQuery orderByWfcounthead($order = Criteria::ASC) Order by the WFCOUNTHEAD column
 * @method     ToolQuery orderByNumrating($order = Criteria::ASC) Order by the NUMRATING column
 * @method     ToolQuery orderBySumrating($order = Criteria::ASC) Order by the SUMRATING column
 *
 * @method     ToolQuery groupById() Group by the ID column
 * @method     ToolQuery groupByMostcommonparameters() Group by the MOSTCOMMONPARAMETERS column
 * @method     ToolQuery groupByUsagecount() Group by the USAGECOUNT column
 * @method     ToolQuery groupByDescription() Group by the DESCRIPTION column
 * @method     ToolQuery groupByName() Group by the NAME column
 * @method     ToolQuery groupByMostcommonparameterscount() Group by the MOSTCOMMONPARAMETERSCOUNT column
 * @method     ToolQuery groupByWfcounthead() Group by the WFCOUNTHEAD column
 * @method     ToolQuery groupByNumrating() Group by the NUMRATING column
 * @method     ToolQuery groupBySumrating() Group by the SUMRATING column
 *
 * @method     ToolQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ToolQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ToolQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Tool findOne(PropelPDO $con = null) Return the first Tool matching the query
 * @method     Tool findOneOrCreate(PropelPDO $con = null) Return the first Tool matching the query, or a new Tool object populated from the query conditions when no match is found
 *
 * @method     Tool findOneById(int $ID) Return the first Tool filtered by the ID column
 * @method     Tool findOneByMostcommonparameters(string $MOSTCOMMONPARAMETERS) Return the first Tool filtered by the MOSTCOMMONPARAMETERS column
 * @method     Tool findOneByUsagecount(int $USAGECOUNT) Return the first Tool filtered by the USAGECOUNT column
 * @method     Tool findOneByDescription(string $DESCRIPTION) Return the first Tool filtered by the DESCRIPTION column
 * @method     Tool findOneByName(string $NAME) Return the first Tool filtered by the NAME column
 * @method     Tool findOneByMostcommonparameterscount(int $MOSTCOMMONPARAMETERSCOUNT) Return the first Tool filtered by the MOSTCOMMONPARAMETERSCOUNT column
 * @method     Tool findOneByWfcounthead(int $WFCOUNTHEAD) Return the first Tool filtered by the WFCOUNTHEAD column
 * @method     Tool findOneByNumrating(int $NUMRATING) Return the first Tool filtered by the NUMRATING column
 * @method     Tool findOneBySumrating(int $SUMRATING) Return the first Tool filtered by the SUMRATING column
 *
 * @method     array findById(int $ID) Return Tool objects filtered by the ID column
 * @method     array findByMostcommonparameters(string $MOSTCOMMONPARAMETERS) Return Tool objects filtered by the MOSTCOMMONPARAMETERS column
 * @method     array findByUsagecount(int $USAGECOUNT) Return Tool objects filtered by the USAGECOUNT column
 * @method     array findByDescription(string $DESCRIPTION) Return Tool objects filtered by the DESCRIPTION column
 * @method     array findByName(string $NAME) Return Tool objects filtered by the NAME column
 * @method     array findByMostcommonparameterscount(int $MOSTCOMMONPARAMETERSCOUNT) Return Tool objects filtered by the MOSTCOMMONPARAMETERSCOUNT column
 * @method     array findByWfcounthead(int $WFCOUNTHEAD) Return Tool objects filtered by the WFCOUNTHEAD column
 * @method     array findByNumrating(int $NUMRATING) Return Tool objects filtered by the NUMRATING column
 * @method     array findBySumrating(int $SUMRATING) Return Tool objects filtered by the SUMRATING column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseToolQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseToolQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Tool', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ToolQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ToolQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ToolQuery) {
			return $criteria;
		}
		$query = new ToolQuery();
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
	 * @return    Tool|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ToolPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ToolPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ToolPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ToolPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the MOSTCOMMONPARAMETERS column
	 * 
	 * @param     string $mostcommonparameters The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByMostcommonparameters($mostcommonparameters = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($mostcommonparameters)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $mostcommonparameters)) {
				$mostcommonparameters = str_replace('*', '%', $mostcommonparameters);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ToolPeer::MOSTCOMMONPARAMETERS, $mostcommonparameters, $comparison);
	}

	/**
	 * Filter the query on the USAGECOUNT column
	 * 
	 * @param     int|array $usagecount The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByUsagecount($usagecount = null, $comparison = null)
	{
		if (is_array($usagecount)) {
			$useMinMax = false;
			if (isset($usagecount['min'])) {
				$this->addUsingAlias(ToolPeer::USAGECOUNT, $usagecount['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($usagecount['max'])) {
				$this->addUsingAlias(ToolPeer::USAGECOUNT, $usagecount['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolPeer::USAGECOUNT, $usagecount, $comparison);
	}

	/**
	 * Filter the query on the DESCRIPTION column
	 * 
	 * @param     string $description The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByDescription($description = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($description)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $description)) {
				$description = str_replace('*', '%', $description);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ToolPeer::DESCRIPTION, $description, $comparison);
	}

	/**
	 * Filter the query on the NAME column
	 * 
	 * @param     string $name The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
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
		return $this->addUsingAlias(ToolPeer::NAME, $name, $comparison);
	}

	/**
	 * Filter the query on the MOSTCOMMONPARAMETERSCOUNT column
	 * 
	 * @param     int|array $mostcommonparameterscount The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByMostcommonparameterscount($mostcommonparameterscount = null, $comparison = null)
	{
		if (is_array($mostcommonparameterscount)) {
			$useMinMax = false;
			if (isset($mostcommonparameterscount['min'])) {
				$this->addUsingAlias(ToolPeer::MOSTCOMMONPARAMETERSCOUNT, $mostcommonparameterscount['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($mostcommonparameterscount['max'])) {
				$this->addUsingAlias(ToolPeer::MOSTCOMMONPARAMETERSCOUNT, $mostcommonparameterscount['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolPeer::MOSTCOMMONPARAMETERSCOUNT, $mostcommonparameterscount, $comparison);
	}

	/**
	 * Filter the query on the WFCOUNTHEAD column
	 * 
	 * @param     int|array $wfcounthead The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByWfcounthead($wfcounthead = null, $comparison = null)
	{
		if (is_array($wfcounthead)) {
			$useMinMax = false;
			if (isset($wfcounthead['min'])) {
				$this->addUsingAlias(ToolPeer::WFCOUNTHEAD, $wfcounthead['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($wfcounthead['max'])) {
				$this->addUsingAlias(ToolPeer::WFCOUNTHEAD, $wfcounthead['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolPeer::WFCOUNTHEAD, $wfcounthead, $comparison);
	}

	/**
	 * Filter the query on the NUMRATING column
	 * 
	 * @param     int|array $numrating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterByNumrating($numrating = null, $comparison = null)
	{
		if (is_array($numrating)) {
			$useMinMax = false;
			if (isset($numrating['min'])) {
				$this->addUsingAlias(ToolPeer::NUMRATING, $numrating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($numrating['max'])) {
				$this->addUsingAlias(ToolPeer::NUMRATING, $numrating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolPeer::NUMRATING, $numrating, $comparison);
	}

	/**
	 * Filter the query on the SUMRATING column
	 * 
	 * @param     int|array $sumrating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function filterBySumrating($sumrating = null, $comparison = null)
	{
		if (is_array($sumrating)) {
			$useMinMax = false;
			if (isset($sumrating['min'])) {
				$this->addUsingAlias(ToolPeer::SUMRATING, $sumrating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($sumrating['max'])) {
				$this->addUsingAlias(ToolPeer::SUMRATING, $sumrating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolPeer::SUMRATING, $sumrating, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Tool $tool Object to remove from the list of results
	 *
	 * @return    ToolQuery The current query, for fluid interface
	 */
	public function prune($tool = null)
	{
		if ($tool) {
			$this->addUsingAlias(ToolPeer::ID, $tool->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseToolQuery
