<?php


/**
 * Base class that represents a query for the 'friends' table.
 *
 * 
 *
 * @method     FriendsQuery orderByUser1($order = Criteria::ASC) Order by the user1 column
 * @method     FriendsQuery orderByUser2($order = Criteria::ASC) Order by the user2 column
 * @method     FriendsQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     FriendsQuery groupByUser1() Group by the user1 column
 * @method     FriendsQuery groupByUser2() Group by the user2 column
 * @method     FriendsQuery groupById() Group by the id column
 *
 * @method     FriendsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     FriendsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     FriendsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Friends findOne(PropelPDO $con = null) Return the first Friends matching the query
 * @method     Friends findOneOrCreate(PropelPDO $con = null) Return the first Friends matching the query, or a new Friends object populated from the query conditions when no match is found
 *
 * @method     Friends findOneByUser1(string $user1) Return the first Friends filtered by the user1 column
 * @method     Friends findOneByUser2(string $user2) Return the first Friends filtered by the user2 column
 * @method     Friends findOneById(int $id) Return the first Friends filtered by the id column
 *
 * @method     array findByUser1(string $user1) Return Friends objects filtered by the user1 column
 * @method     array findByUser2(string $user2) Return Friends objects filtered by the user2 column
 * @method     array findById(int $id) Return Friends objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseFriendsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseFriendsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Friends', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new FriendsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    FriendsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof FriendsQuery) {
			return $criteria;
		}
		$query = new FriendsQuery();
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
	 * @return    Friends|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = FriendsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    FriendsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(FriendsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    FriendsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(FriendsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the user1 column
	 * 
	 * @param     string $user1 The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendsQuery The current query, for fluid interface
	 */
	public function filterByUser1($user1 = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($user1)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $user1)) {
				$user1 = str_replace('*', '%', $user1);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(FriendsPeer::USER1, $user1, $comparison);
	}

	/**
	 * Filter the query on the user2 column
	 * 
	 * @param     string $user2 The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendsQuery The current query, for fluid interface
	 */
	public function filterByUser2($user2 = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($user2)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $user2)) {
				$user2 = str_replace('*', '%', $user2);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(FriendsPeer::USER2, $user2, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    FriendsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(FriendsPeer::ID, $id, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Friends $friends Object to remove from the list of results
	 *
	 * @return    FriendsQuery The current query, for fluid interface
	 */
	public function prune($friends = null)
	{
		if ($friends) {
			$this->addUsingAlias(FriendsPeer::ID, $friends->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseFriendsQuery
