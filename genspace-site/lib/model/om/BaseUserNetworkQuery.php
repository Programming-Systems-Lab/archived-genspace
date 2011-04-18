<?php


/**
 * Base class that represents a query for the 'User_Network' table.
 *
 * 
 *
 * @method     UserNetworkQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     UserNetworkQuery orderByVisible($order = Criteria::ASC) Order by the VISIBLE column
 * @method     UserNetworkQuery orderByVerified($order = Criteria::ASC) Order by the VERIFIED column
 * @method     UserNetworkQuery orderByNetworkId($order = Criteria::ASC) Order by the network_id column
 * @method     UserNetworkQuery orderByUserId($order = Criteria::ASC) Order by the user_id column
 *
 * @method     UserNetworkQuery groupById() Group by the ID column
 * @method     UserNetworkQuery groupByVisible() Group by the VISIBLE column
 * @method     UserNetworkQuery groupByVerified() Group by the VERIFIED column
 * @method     UserNetworkQuery groupByNetworkId() Group by the network_id column
 * @method     UserNetworkQuery groupByUserId() Group by the user_id column
 *
 * @method     UserNetworkQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     UserNetworkQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     UserNetworkQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     UserNetwork findOne(PropelPDO $con = null) Return the first UserNetwork matching the query
 * @method     UserNetwork findOneOrCreate(PropelPDO $con = null) Return the first UserNetwork matching the query, or a new UserNetwork object populated from the query conditions when no match is found
 *
 * @method     UserNetwork findOneById(int $ID) Return the first UserNetwork filtered by the ID column
 * @method     UserNetwork findOneByVisible(int $VISIBLE) Return the first UserNetwork filtered by the VISIBLE column
 * @method     UserNetwork findOneByVerified(int $VERIFIED) Return the first UserNetwork filtered by the VERIFIED column
 * @method     UserNetwork findOneByNetworkId(int $network_id) Return the first UserNetwork filtered by the network_id column
 * @method     UserNetwork findOneByUserId(int $user_id) Return the first UserNetwork filtered by the user_id column
 *
 * @method     array findById(int $ID) Return UserNetwork objects filtered by the ID column
 * @method     array findByVisible(int $VISIBLE) Return UserNetwork objects filtered by the VISIBLE column
 * @method     array findByVerified(int $VERIFIED) Return UserNetwork objects filtered by the VERIFIED column
 * @method     array findByNetworkId(int $network_id) Return UserNetwork objects filtered by the network_id column
 * @method     array findByUserId(int $user_id) Return UserNetwork objects filtered by the user_id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseUserNetworkQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseUserNetworkQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'UserNetwork', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new UserNetworkQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    UserNetworkQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof UserNetworkQuery) {
			return $criteria;
		}
		$query = new UserNetworkQuery();
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
	 * @return    UserNetwork|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = UserNetworkPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(UserNetworkPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(UserNetworkPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(UserNetworkPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the VISIBLE column
	 * 
	 * @param     int|array $visible The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterByVisible($visible = null, $comparison = null)
	{
		if (is_array($visible)) {
			$useMinMax = false;
			if (isset($visible['min'])) {
				$this->addUsingAlias(UserNetworkPeer::VISIBLE, $visible['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($visible['max'])) {
				$this->addUsingAlias(UserNetworkPeer::VISIBLE, $visible['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserNetworkPeer::VISIBLE, $visible, $comparison);
	}

	/**
	 * Filter the query on the VERIFIED column
	 * 
	 * @param     int|array $verified The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterByVerified($verified = null, $comparison = null)
	{
		if (is_array($verified)) {
			$useMinMax = false;
			if (isset($verified['min'])) {
				$this->addUsingAlias(UserNetworkPeer::VERIFIED, $verified['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($verified['max'])) {
				$this->addUsingAlias(UserNetworkPeer::VERIFIED, $verified['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserNetworkPeer::VERIFIED, $verified, $comparison);
	}

	/**
	 * Filter the query on the network_id column
	 * 
	 * @param     int|array $networkId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterByNetworkId($networkId = null, $comparison = null)
	{
		if (is_array($networkId)) {
			$useMinMax = false;
			if (isset($networkId['min'])) {
				$this->addUsingAlias(UserNetworkPeer::NETWORK_ID, $networkId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($networkId['max'])) {
				$this->addUsingAlias(UserNetworkPeer::NETWORK_ID, $networkId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserNetworkPeer::NETWORK_ID, $networkId, $comparison);
	}

	/**
	 * Filter the query on the user_id column
	 * 
	 * @param     int|array $userId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function filterByUserId($userId = null, $comparison = null)
	{
		if (is_array($userId)) {
			$useMinMax = false;
			if (isset($userId['min'])) {
				$this->addUsingAlias(UserNetworkPeer::USER_ID, $userId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($userId['max'])) {
				$this->addUsingAlias(UserNetworkPeer::USER_ID, $userId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserNetworkPeer::USER_ID, $userId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     UserNetwork $userNetwork Object to remove from the list of results
	 *
	 * @return    UserNetworkQuery The current query, for fluid interface
	 */
	public function prune($userNetwork = null)
	{
		if ($userNetwork) {
			$this->addUsingAlias(UserNetworkPeer::ID, $userNetwork->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseUserNetworkQuery
