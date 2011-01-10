<?php


/**
 * Base class that represents a query for the 'users' table.
 *
 * 
 *
 * @method     UsersQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     UsersQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     UsersQuery orderByPassword($order = Criteria::ASC) Order by the password column
 * @method     UsersQuery orderByEmail($order = Criteria::ASC) Order by the email column
 * @method     UsersQuery orderByOrganization($order = Criteria::ASC) Order by the organization column
 *
 * @method     UsersQuery groupById() Group by the id column
 * @method     UsersQuery groupByUsername() Group by the username column
 * @method     UsersQuery groupByPassword() Group by the password column
 * @method     UsersQuery groupByEmail() Group by the email column
 * @method     UsersQuery groupByOrganization() Group by the organization column
 *
 * @method     UsersQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     UsersQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     UsersQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Users findOne(PropelPDO $con = null) Return the first Users matching the query
 * @method     Users findOneOrCreate(PropelPDO $con = null) Return the first Users matching the query, or a new Users object populated from the query conditions when no match is found
 *
 * @method     Users findOneById(int $id) Return the first Users filtered by the id column
 * @method     Users findOneByUsername(string $username) Return the first Users filtered by the username column
 * @method     Users findOneByPassword(string $password) Return the first Users filtered by the password column
 * @method     Users findOneByEmail(string $email) Return the first Users filtered by the email column
 * @method     Users findOneByOrganization(string $organization) Return the first Users filtered by the organization column
 *
 * @method     array findById(int $id) Return Users objects filtered by the id column
 * @method     array findByUsername(string $username) Return Users objects filtered by the username column
 * @method     array findByPassword(string $password) Return Users objects filtered by the password column
 * @method     array findByEmail(string $email) Return Users objects filtered by the email column
 * @method     array findByOrganization(string $organization) Return Users objects filtered by the organization column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseUsersQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseUsersQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Users', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new UsersQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    UsersQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof UsersQuery) {
			return $criteria;
		}
		$query = new UsersQuery();
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
	 * @return    Users|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = UsersPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(UsersPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(UsersPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(UsersPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UsersQuery The current query, for fluid interface
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
		return $this->addUsingAlias(UsersPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the password column
	 * 
	 * @param     string $password The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function filterByPassword($password = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($password)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $password)) {
				$password = str_replace('*', '%', $password);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(UsersPeer::PASSWORD, $password, $comparison);
	}

	/**
	 * Filter the query on the email column
	 * 
	 * @param     string $email The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function filterByEmail($email = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($email)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $email)) {
				$email = str_replace('*', '%', $email);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(UsersPeer::EMAIL, $email, $comparison);
	}

	/**
	 * Filter the query on the organization column
	 * 
	 * @param     string $organization The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function filterByOrganization($organization = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($organization)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $organization)) {
				$organization = str_replace('*', '%', $organization);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(UsersPeer::ORGANIZATION, $organization, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Users $users Object to remove from the list of results
	 *
	 * @return    UsersQuery The current query, for fluid interface
	 */
	public function prune($users = null)
	{
		if ($users) {
			$this->addUsingAlias(UsersPeer::ID, $users->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseUsersQuery
