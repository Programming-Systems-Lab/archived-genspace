<?php


/**
 * Base class that represents a query for the 'setting' table.
 *
 * 
 *
 * @method     SettingQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     SettingQuery orderByDataValue($order = Criteria::ASC) Order by the data_value column
 * @method     SettingQuery orderByDataKey($order = Criteria::ASC) Order by the data_key column
 *
 * @method     SettingQuery groupById() Group by the ID column
 * @method     SettingQuery groupByDataValue() Group by the data_value column
 * @method     SettingQuery groupByDataKey() Group by the data_key column
 *
 * @method     SettingQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     SettingQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     SettingQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Setting findOne(PropelPDO $con = null) Return the first Setting matching the query
 * @method     Setting findOneOrCreate(PropelPDO $con = null) Return the first Setting matching the query, or a new Setting object populated from the query conditions when no match is found
 *
 * @method     Setting findOneById(int $ID) Return the first Setting filtered by the ID column
 * @method     Setting findOneByDataValue(string $data_value) Return the first Setting filtered by the data_value column
 * @method     Setting findOneByDataKey(string $data_key) Return the first Setting filtered by the data_key column
 *
 * @method     array findById(int $ID) Return Setting objects filtered by the ID column
 * @method     array findByDataValue(string $data_value) Return Setting objects filtered by the data_value column
 * @method     array findByDataKey(string $data_key) Return Setting objects filtered by the data_key column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseSettingQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseSettingQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Setting', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new SettingQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    SettingQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof SettingQuery) {
			return $criteria;
		}
		$query = new SettingQuery();
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
	 * @return    Setting|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = SettingPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    SettingQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(SettingPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    SettingQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(SettingPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    SettingQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(SettingPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the data_value column
	 * 
	 * @param     string $dataValue The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    SettingQuery The current query, for fluid interface
	 */
	public function filterByDataValue($dataValue = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($dataValue)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $dataValue)) {
				$dataValue = str_replace('*', '%', $dataValue);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(SettingPeer::DATA_VALUE, $dataValue, $comparison);
	}

	/**
	 * Filter the query on the data_key column
	 * 
	 * @param     string $dataKey The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    SettingQuery The current query, for fluid interface
	 */
	public function filterByDataKey($dataKey = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($dataKey)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $dataKey)) {
				$dataKey = str_replace('*', '%', $dataKey);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(SettingPeer::DATA_KEY, $dataKey, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Setting $setting Object to remove from the list of results
	 *
	 * @return    SettingQuery The current query, for fluid interface
	 */
	public function prune($setting = null)
	{
		if ($setting) {
			$this->addUsingAlias(SettingPeer::ID, $setting->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseSettingQuery
