<?php


/**
 * Base class that represents a query for the 'Friend' table.
 *
 * 
 *
 * @method     FriendQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     FriendQuery orderByMutual($order = Criteria::ASC) Order by the MUTUAL column
 * @method     FriendQuery orderByVisible($order = Criteria::ASC) Order by the VISIBLE column
 * @method     FriendQuery orderById1($order = Criteria::ASC) Order by the id_1 column
 * @method     FriendQuery orderById2($order = Criteria::ASC) Order by the id_2 column
 *
 * @method     FriendQuery groupById() Group by the ID column
 * @method     FriendQuery groupByMutual() Group by the MUTUAL column
 * @method     FriendQuery groupByVisible() Group by the VISIBLE column
 * @method     FriendQuery groupById1() Group by the id_1 column
 * @method     FriendQuery groupById2() Group by the id_2 column
 *
 * @method     FriendQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     FriendQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     FriendQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Friend findOne(PropelPDO $con = null) Return the first Friend matching the query
 * @method     Friend findOneOrCreate(PropelPDO $con = null) Return the first Friend matching the query, or a new Friend object populated from the query conditions when no match is found
 *
 * @method     Friend findOneById(int $ID) Return the first Friend filtered by the ID column
 * @method     Friend findOneByMutual(int $MUTUAL) Return the first Friend filtered by the MUTUAL column
 * @method     Friend findOneByVisible(int $VISIBLE) Return the first Friend filtered by the VISIBLE column
 * @method     Friend findOneById1(int $id_1) Return the first Friend filtered by the id_1 column
 * @method     Friend findOneById2(int $id_2) Return the first Friend filtered by the id_2 column
 *
 * @method     array findById(int $ID) Return Friend objects filtered by the ID column
 * @method     array findByMutual(int $MUTUAL) Return Friend objects filtered by the MUTUAL column
 * @method     array findByVisible(int $VISIBLE) Return Friend objects filtered by the VISIBLE column
 * @method     array findById1(int $id_1) Return Friend objects filtered by the id_1 column
 * @method     array findById2(int $id_2) Return Friend objects filtered by the id_2 column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseFriendQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseFriendQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Friend', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new FriendQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    FriendQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof FriendQuery) {
			return $criteria;
		}
		$query = new FriendQuery();
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
	 * @return    Friend|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = FriendPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(FriendPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(FriendPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(FriendPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the MUTUAL column
	 * 
	 * @param     int|array $mutual The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterByMutual($mutual = null, $comparison = null)
	{
		if (is_array($mutual)) {
			$useMinMax = false;
			if (isset($mutual['min'])) {
				$this->addUsingAlias(FriendPeer::MUTUAL, $mutual['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($mutual['max'])) {
				$this->addUsingAlias(FriendPeer::MUTUAL, $mutual['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(FriendPeer::MUTUAL, $mutual, $comparison);
	}

	/**
	 * Filter the query on the VISIBLE column
	 * 
	 * @param     int|array $visible The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterByVisible($visible = null, $comparison = null)
	{
		if (is_array($visible)) {
			$useMinMax = false;
			if (isset($visible['min'])) {
				$this->addUsingAlias(FriendPeer::VISIBLE, $visible['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($visible['max'])) {
				$this->addUsingAlias(FriendPeer::VISIBLE, $visible['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(FriendPeer::VISIBLE, $visible, $comparison);
	}

	/**
	 * Filter the query on the id_1 column
	 * 
	 * @param     int|array $id1 The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterById1($id1 = null, $comparison = null)
	{
		if (is_array($id1)) {
			$useMinMax = false;
			if (isset($id1['min'])) {
				$this->addUsingAlias(FriendPeer::ID_1, $id1['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($id1['max'])) {
				$this->addUsingAlias(FriendPeer::ID_1, $id1['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(FriendPeer::ID_1, $id1, $comparison);
	}

	/**
	 * Filter the query on the id_2 column
	 * 
	 * @param     int|array $id2 The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function filterById2($id2 = null, $comparison = null)
	{
		if (is_array($id2)) {
			$useMinMax = false;
			if (isset($id2['min'])) {
				$this->addUsingAlias(FriendPeer::ID_2, $id2['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($id2['max'])) {
				$this->addUsingAlias(FriendPeer::ID_2, $id2['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(FriendPeer::ID_2, $id2, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Friend $friend Object to remove from the list of results
	 *
	 * @return    FriendQuery The current query, for fluid interface
	 */
	public function prune($friend = null)
	{
		if ($friend) {
			$this->addUsingAlias(FriendPeer::ID, $friend->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseFriendQuery
