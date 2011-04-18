<?php


/**
 * Base class that represents a query for the 'ANALYSISEVENTPARAMETER' table.
 *
 * 
 *
 * @method     AnalysiseventparameterQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     AnalysiseventparameterQuery orderByParametervalue($order = Criteria::ASC) Order by the PARAMETERVALUE column
 * @method     AnalysiseventparameterQuery orderByParameterkey($order = Criteria::ASC) Order by the PARAMETERKEY column
 * @method     AnalysiseventparameterQuery orderByEventId($order = Criteria::ASC) Order by the EVENT_ID column
 *
 * @method     AnalysiseventparameterQuery groupById() Group by the ID column
 * @method     AnalysiseventparameterQuery groupByParametervalue() Group by the PARAMETERVALUE column
 * @method     AnalysiseventparameterQuery groupByParameterkey() Group by the PARAMETERKEY column
 * @method     AnalysiseventparameterQuery groupByEventId() Group by the EVENT_ID column
 *
 * @method     AnalysiseventparameterQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     AnalysiseventparameterQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     AnalysiseventparameterQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Analysiseventparameter findOne(PropelPDO $con = null) Return the first Analysiseventparameter matching the query
 * @method     Analysiseventparameter findOneOrCreate(PropelPDO $con = null) Return the first Analysiseventparameter matching the query, or a new Analysiseventparameter object populated from the query conditions when no match is found
 *
 * @method     Analysiseventparameter findOneById(int $ID) Return the first Analysiseventparameter filtered by the ID column
 * @method     Analysiseventparameter findOneByParametervalue(string $PARAMETERVALUE) Return the first Analysiseventparameter filtered by the PARAMETERVALUE column
 * @method     Analysiseventparameter findOneByParameterkey(string $PARAMETERKEY) Return the first Analysiseventparameter filtered by the PARAMETERKEY column
 * @method     Analysiseventparameter findOneByEventId(int $EVENT_ID) Return the first Analysiseventparameter filtered by the EVENT_ID column
 *
 * @method     array findById(int $ID) Return Analysiseventparameter objects filtered by the ID column
 * @method     array findByParametervalue(string $PARAMETERVALUE) Return Analysiseventparameter objects filtered by the PARAMETERVALUE column
 * @method     array findByParameterkey(string $PARAMETERKEY) Return Analysiseventparameter objects filtered by the PARAMETERKEY column
 * @method     array findByEventId(int $EVENT_ID) Return Analysiseventparameter objects filtered by the EVENT_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseAnalysiseventparameterQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseAnalysiseventparameterQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Analysiseventparameter', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new AnalysiseventparameterQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    AnalysiseventparameterQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof AnalysiseventparameterQuery) {
			return $criteria;
		}
		$query = new AnalysiseventparameterQuery();
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
	 * @return    Analysiseventparameter|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = AnalysiseventparameterPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(AnalysiseventparameterPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(AnalysiseventparameterPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(AnalysiseventparameterPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the PARAMETERVALUE column
	 * 
	 * @param     string $parametervalue The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function filterByParametervalue($parametervalue = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($parametervalue)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $parametervalue)) {
				$parametervalue = str_replace('*', '%', $parametervalue);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnalysiseventparameterPeer::PARAMETERVALUE, $parametervalue, $comparison);
	}

	/**
	 * Filter the query on the PARAMETERKEY column
	 * 
	 * @param     string $parameterkey The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function filterByParameterkey($parameterkey = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($parameterkey)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $parameterkey)) {
				$parameterkey = str_replace('*', '%', $parameterkey);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnalysiseventparameterPeer::PARAMETERKEY, $parameterkey, $comparison);
	}

	/**
	 * Filter the query on the EVENT_ID column
	 * 
	 * @param     int|array $eventId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function filterByEventId($eventId = null, $comparison = null)
	{
		if (is_array($eventId)) {
			$useMinMax = false;
			if (isset($eventId['min'])) {
				$this->addUsingAlias(AnalysiseventparameterPeer::EVENT_ID, $eventId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($eventId['max'])) {
				$this->addUsingAlias(AnalysiseventparameterPeer::EVENT_ID, $eventId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnalysiseventparameterPeer::EVENT_ID, $eventId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Analysiseventparameter $analysiseventparameter Object to remove from the list of results
	 *
	 * @return    AnalysiseventparameterQuery The current query, for fluid interface
	 */
	public function prune($analysiseventparameter = null)
	{
		if ($analysiseventparameter) {
			$this->addUsingAlias(AnalysiseventparameterPeer::ID, $analysiseventparameter->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseAnalysiseventparameterQuery
