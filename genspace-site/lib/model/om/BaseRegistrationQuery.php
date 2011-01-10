<?php


/**
 * Base class that represents a query for the 'registration' table.
 *
 * 
 *
 * @method     RegistrationQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     RegistrationQuery orderByPassword($order = Criteria::ASC) Order by the password column
 * @method     RegistrationQuery orderByEmail($order = Criteria::ASC) Order by the email column
 * @method     RegistrationQuery orderByImEmail($order = Criteria::ASC) Order by the im_email column
 * @method     RegistrationQuery orderByImPassword($order = Criteria::ASC) Order by the im_password column
 * @method     RegistrationQuery orderByFirstName($order = Criteria::ASC) Order by the first_name column
 * @method     RegistrationQuery orderByLastName($order = Criteria::ASC) Order by the last_name column
 * @method     RegistrationQuery orderByWorkTitle($order = Criteria::ASC) Order by the work_title column
 * @method     RegistrationQuery orderByPhone($order = Criteria::ASC) Order by the phone column
 * @method     RegistrationQuery orderByLabAffiliation($order = Criteria::ASC) Order by the lab_affiliation column
 * @method     RegistrationQuery orderByAddr1($order = Criteria::ASC) Order by the addr1 column
 * @method     RegistrationQuery orderByAddr2($order = Criteria::ASC) Order by the addr2 column
 * @method     RegistrationQuery orderByCity($order = Criteria::ASC) Order by the city column
 * @method     RegistrationQuery orderByState($order = Criteria::ASC) Order by the state column
 * @method     RegistrationQuery orderByZipcode($order = Criteria::ASC) Order by the zipcode column
 *
 * @method     RegistrationQuery groupByUsername() Group by the username column
 * @method     RegistrationQuery groupByPassword() Group by the password column
 * @method     RegistrationQuery groupByEmail() Group by the email column
 * @method     RegistrationQuery groupByImEmail() Group by the im_email column
 * @method     RegistrationQuery groupByImPassword() Group by the im_password column
 * @method     RegistrationQuery groupByFirstName() Group by the first_name column
 * @method     RegistrationQuery groupByLastName() Group by the last_name column
 * @method     RegistrationQuery groupByWorkTitle() Group by the work_title column
 * @method     RegistrationQuery groupByPhone() Group by the phone column
 * @method     RegistrationQuery groupByLabAffiliation() Group by the lab_affiliation column
 * @method     RegistrationQuery groupByAddr1() Group by the addr1 column
 * @method     RegistrationQuery groupByAddr2() Group by the addr2 column
 * @method     RegistrationQuery groupByCity() Group by the city column
 * @method     RegistrationQuery groupByState() Group by the state column
 * @method     RegistrationQuery groupByZipcode() Group by the zipcode column
 *
 * @method     RegistrationQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     RegistrationQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     RegistrationQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     RegistrationQuery leftJoinDataVisibility($relationAlias = null) Adds a LEFT JOIN clause to the query using the DataVisibility relation
 * @method     RegistrationQuery rightJoinDataVisibility($relationAlias = null) Adds a RIGHT JOIN clause to the query using the DataVisibility relation
 * @method     RegistrationQuery innerJoinDataVisibility($relationAlias = null) Adds a INNER JOIN clause to the query using the DataVisibility relation
 *
 * @method     RegistrationQuery leftJoinUserVisibility($relationAlias = null) Adds a LEFT JOIN clause to the query using the UserVisibility relation
 * @method     RegistrationQuery rightJoinUserVisibility($relationAlias = null) Adds a RIGHT JOIN clause to the query using the UserVisibility relation
 * @method     RegistrationQuery innerJoinUserVisibility($relationAlias = null) Adds a INNER JOIN clause to the query using the UserVisibility relation
 *
 * @method     RegistrationQuery leftJoinAudit($relationAlias = null) Adds a LEFT JOIN clause to the query using the Audit relation
 * @method     RegistrationQuery rightJoinAudit($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Audit relation
 * @method     RegistrationQuery innerJoinAudit($relationAlias = null) Adds a INNER JOIN clause to the query using the Audit relation
 *
 * @method     RegistrationQuery leftJoinNetworkVisibility($relationAlias = null) Adds a LEFT JOIN clause to the query using the NetworkVisibility relation
 * @method     RegistrationQuery rightJoinNetworkVisibility($relationAlias = null) Adds a RIGHT JOIN clause to the query using the NetworkVisibility relation
 * @method     RegistrationQuery innerJoinNetworkVisibility($relationAlias = null) Adds a INNER JOIN clause to the query using the NetworkVisibility relation
 *
 * @method     RegistrationQuery leftJoinOutboxRelatedByFromuser($relationAlias = null) Adds a LEFT JOIN clause to the query using the OutboxRelatedByFromuser relation
 * @method     RegistrationQuery rightJoinOutboxRelatedByFromuser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the OutboxRelatedByFromuser relation
 * @method     RegistrationQuery innerJoinOutboxRelatedByFromuser($relationAlias = null) Adds a INNER JOIN clause to the query using the OutboxRelatedByFromuser relation
 *
 * @method     RegistrationQuery leftJoinOutboxRelatedByTouser($relationAlias = null) Adds a LEFT JOIN clause to the query using the OutboxRelatedByTouser relation
 * @method     RegistrationQuery rightJoinOutboxRelatedByTouser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the OutboxRelatedByTouser relation
 * @method     RegistrationQuery innerJoinOutboxRelatedByTouser($relationAlias = null) Adds a INNER JOIN clause to the query using the OutboxRelatedByTouser relation
 *
 * @method     RegistrationQuery leftJoinInboxRelatedByFromuser($relationAlias = null) Adds a LEFT JOIN clause to the query using the InboxRelatedByFromuser relation
 * @method     RegistrationQuery rightJoinInboxRelatedByFromuser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the InboxRelatedByFromuser relation
 * @method     RegistrationQuery innerJoinInboxRelatedByFromuser($relationAlias = null) Adds a INNER JOIN clause to the query using the InboxRelatedByFromuser relation
 *
 * @method     RegistrationQuery leftJoinInboxRelatedByTouser($relationAlias = null) Adds a LEFT JOIN clause to the query using the InboxRelatedByTouser relation
 * @method     RegistrationQuery rightJoinInboxRelatedByTouser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the InboxRelatedByTouser relation
 * @method     RegistrationQuery innerJoinInboxRelatedByTouser($relationAlias = null) Adds a INNER JOIN clause to the query using the InboxRelatedByTouser relation
 *
 * @method     Registration findOne(PropelPDO $con = null) Return the first Registration matching the query
 * @method     Registration findOneOrCreate(PropelPDO $con = null) Return the first Registration matching the query, or a new Registration object populated from the query conditions when no match is found
 *
 * @method     Registration findOneByUsername(string $username) Return the first Registration filtered by the username column
 * @method     Registration findOneByPassword(string $password) Return the first Registration filtered by the password column
 * @method     Registration findOneByEmail(string $email) Return the first Registration filtered by the email column
 * @method     Registration findOneByImEmail(string $im_email) Return the first Registration filtered by the im_email column
 * @method     Registration findOneByImPassword(string $im_password) Return the first Registration filtered by the im_password column
 * @method     Registration findOneByFirstName(string $first_name) Return the first Registration filtered by the first_name column
 * @method     Registration findOneByLastName(string $last_name) Return the first Registration filtered by the last_name column
 * @method     Registration findOneByWorkTitle(string $work_title) Return the first Registration filtered by the work_title column
 * @method     Registration findOneByPhone(string $phone) Return the first Registration filtered by the phone column
 * @method     Registration findOneByLabAffiliation(string $lab_affiliation) Return the first Registration filtered by the lab_affiliation column
 * @method     Registration findOneByAddr1(string $addr1) Return the first Registration filtered by the addr1 column
 * @method     Registration findOneByAddr2(string $addr2) Return the first Registration filtered by the addr2 column
 * @method     Registration findOneByCity(string $city) Return the first Registration filtered by the city column
 * @method     Registration findOneByState(string $state) Return the first Registration filtered by the state column
 * @method     Registration findOneByZipcode(string $zipcode) Return the first Registration filtered by the zipcode column
 *
 * @method     array findByUsername(string $username) Return Registration objects filtered by the username column
 * @method     array findByPassword(string $password) Return Registration objects filtered by the password column
 * @method     array findByEmail(string $email) Return Registration objects filtered by the email column
 * @method     array findByImEmail(string $im_email) Return Registration objects filtered by the im_email column
 * @method     array findByImPassword(string $im_password) Return Registration objects filtered by the im_password column
 * @method     array findByFirstName(string $first_name) Return Registration objects filtered by the first_name column
 * @method     array findByLastName(string $last_name) Return Registration objects filtered by the last_name column
 * @method     array findByWorkTitle(string $work_title) Return Registration objects filtered by the work_title column
 * @method     array findByPhone(string $phone) Return Registration objects filtered by the phone column
 * @method     array findByLabAffiliation(string $lab_affiliation) Return Registration objects filtered by the lab_affiliation column
 * @method     array findByAddr1(string $addr1) Return Registration objects filtered by the addr1 column
 * @method     array findByAddr2(string $addr2) Return Registration objects filtered by the addr2 column
 * @method     array findByCity(string $city) Return Registration objects filtered by the city column
 * @method     array findByState(string $state) Return Registration objects filtered by the state column
 * @method     array findByZipcode(string $zipcode) Return Registration objects filtered by the zipcode column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseRegistrationQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseRegistrationQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Registration', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new RegistrationQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    RegistrationQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof RegistrationQuery) {
			return $criteria;
		}
		$query = new RegistrationQuery();
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
	 * @return    Registration|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = RegistrationPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(RegistrationPeer::USERNAME, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(RegistrationPeer::USERNAME, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
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
		return $this->addUsingAlias(RegistrationPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the password column
	 * 
	 * @param     string $password The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
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
		return $this->addUsingAlias(RegistrationPeer::PASSWORD, $password, $comparison);
	}

	/**
	 * Filter the query on the email column
	 * 
	 * @param     string $email The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
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
		return $this->addUsingAlias(RegistrationPeer::EMAIL, $email, $comparison);
	}

	/**
	 * Filter the query on the im_email column
	 * 
	 * @param     string $imEmail The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByImEmail($imEmail = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($imEmail)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $imEmail)) {
				$imEmail = str_replace('*', '%', $imEmail);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::IM_EMAIL, $imEmail, $comparison);
	}

	/**
	 * Filter the query on the im_password column
	 * 
	 * @param     string $imPassword The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByImPassword($imPassword = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($imPassword)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $imPassword)) {
				$imPassword = str_replace('*', '%', $imPassword);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::IM_PASSWORD, $imPassword, $comparison);
	}

	/**
	 * Filter the query on the first_name column
	 * 
	 * @param     string $firstName The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByFirstName($firstName = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($firstName)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $firstName)) {
				$firstName = str_replace('*', '%', $firstName);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::FIRST_NAME, $firstName, $comparison);
	}

	/**
	 * Filter the query on the last_name column
	 * 
	 * @param     string $lastName The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByLastName($lastName = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($lastName)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $lastName)) {
				$lastName = str_replace('*', '%', $lastName);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::LAST_NAME, $lastName, $comparison);
	}

	/**
	 * Filter the query on the work_title column
	 * 
	 * @param     string $workTitle The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByWorkTitle($workTitle = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($workTitle)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $workTitle)) {
				$workTitle = str_replace('*', '%', $workTitle);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::WORK_TITLE, $workTitle, $comparison);
	}

	/**
	 * Filter the query on the phone column
	 * 
	 * @param     string $phone The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByPhone($phone = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($phone)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $phone)) {
				$phone = str_replace('*', '%', $phone);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::PHONE, $phone, $comparison);
	}

	/**
	 * Filter the query on the lab_affiliation column
	 * 
	 * @param     string $labAffiliation The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByLabAffiliation($labAffiliation = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($labAffiliation)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $labAffiliation)) {
				$labAffiliation = str_replace('*', '%', $labAffiliation);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::LAB_AFFILIATION, $labAffiliation, $comparison);
	}

	/**
	 * Filter the query on the addr1 column
	 * 
	 * @param     string $addr1 The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByAddr1($addr1 = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($addr1)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $addr1)) {
				$addr1 = str_replace('*', '%', $addr1);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::ADDR1, $addr1, $comparison);
	}

	/**
	 * Filter the query on the addr2 column
	 * 
	 * @param     string $addr2 The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByAddr2($addr2 = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($addr2)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $addr2)) {
				$addr2 = str_replace('*', '%', $addr2);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::ADDR2, $addr2, $comparison);
	}

	/**
	 * Filter the query on the city column
	 * 
	 * @param     string $city The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByCity($city = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($city)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $city)) {
				$city = str_replace('*', '%', $city);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::CITY, $city, $comparison);
	}

	/**
	 * Filter the query on the state column
	 * 
	 * @param     string $state The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByState($state = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($state)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $state)) {
				$state = str_replace('*', '%', $state);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::STATE, $state, $comparison);
	}

	/**
	 * Filter the query on the zipcode column
	 * 
	 * @param     string $zipcode The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByZipcode($zipcode = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($zipcode)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $zipcode)) {
				$zipcode = str_replace('*', '%', $zipcode);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::ZIPCODE, $zipcode, $comparison);
	}

	/**
	 * Filter the query by a related DataVisibility object
	 *
	 * @param     DataVisibility $dataVisibility  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByDataVisibility($dataVisibility, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $dataVisibility->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the DataVisibility relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinDataVisibility($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('DataVisibility');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'DataVisibility');
		}
		
		return $this;
	}

	/**
	 * Use the DataVisibility relation DataVisibility object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    DataVisibilityQuery A secondary query class using the current class as primary query
	 */
	public function useDataVisibilityQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinDataVisibility($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'DataVisibility', 'DataVisibilityQuery');
	}

	/**
	 * Filter the query by a related UserVisibility object
	 *
	 * @param     UserVisibility $userVisibility  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByUserVisibility($userVisibility, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $userVisibility->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the UserVisibility relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinUserVisibility($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('UserVisibility');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'UserVisibility');
		}
		
		return $this;
	}

	/**
	 * Use the UserVisibility relation UserVisibility object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    UserVisibilityQuery A secondary query class using the current class as primary query
	 */
	public function useUserVisibilityQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinUserVisibility($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'UserVisibility', 'UserVisibilityQuery');
	}

	/**
	 * Filter the query by a related Audit object
	 *
	 * @param     Audit $audit  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByAudit($audit, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $audit->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Audit relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinAudit($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Audit');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'Audit');
		}
		
		return $this;
	}

	/**
	 * Use the Audit relation Audit object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AuditQuery A secondary query class using the current class as primary query
	 */
	public function useAuditQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinAudit($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Audit', 'AuditQuery');
	}

	/**
	 * Filter the query by a related NetworkVisibility object
	 *
	 * @param     NetworkVisibility $networkVisibility  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByNetworkVisibility($networkVisibility, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $networkVisibility->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the NetworkVisibility relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinNetworkVisibility($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('NetworkVisibility');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'NetworkVisibility');
		}
		
		return $this;
	}

	/**
	 * Use the NetworkVisibility relation NetworkVisibility object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    NetworkVisibilityQuery A secondary query class using the current class as primary query
	 */
	public function useNetworkVisibilityQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinNetworkVisibility($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'NetworkVisibility', 'NetworkVisibilityQuery');
	}

	/**
	 * Filter the query by a related Outbox object
	 *
	 * @param     Outbox $outbox  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByOutboxRelatedByFromuser($outbox, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $outbox->getFromuser(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the OutboxRelatedByFromuser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinOutboxRelatedByFromuser($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('OutboxRelatedByFromuser');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'OutboxRelatedByFromuser');
		}
		
		return $this;
	}

	/**
	 * Use the OutboxRelatedByFromuser relation Outbox object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    OutboxQuery A secondary query class using the current class as primary query
	 */
	public function useOutboxRelatedByFromuserQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinOutboxRelatedByFromuser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'OutboxRelatedByFromuser', 'OutboxQuery');
	}

	/**
	 * Filter the query by a related Outbox object
	 *
	 * @param     Outbox $outbox  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByOutboxRelatedByTouser($outbox, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $outbox->getTouser(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the OutboxRelatedByTouser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinOutboxRelatedByTouser($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('OutboxRelatedByTouser');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'OutboxRelatedByTouser');
		}
		
		return $this;
	}

	/**
	 * Use the OutboxRelatedByTouser relation Outbox object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    OutboxQuery A secondary query class using the current class as primary query
	 */
	public function useOutboxRelatedByTouserQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinOutboxRelatedByTouser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'OutboxRelatedByTouser', 'OutboxQuery');
	}

	/**
	 * Filter the query by a related Inbox object
	 *
	 * @param     Inbox $inbox  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByInboxRelatedByFromuser($inbox, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $inbox->getFromuser(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the InboxRelatedByFromuser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinInboxRelatedByFromuser($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('InboxRelatedByFromuser');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'InboxRelatedByFromuser');
		}
		
		return $this;
	}

	/**
	 * Use the InboxRelatedByFromuser relation Inbox object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    InboxQuery A secondary query class using the current class as primary query
	 */
	public function useInboxRelatedByFromuserQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinInboxRelatedByFromuser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'InboxRelatedByFromuser', 'InboxQuery');
	}

	/**
	 * Filter the query by a related Inbox object
	 *
	 * @param     Inbox $inbox  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByInboxRelatedByTouser($inbox, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::USERNAME, $inbox->getTouser(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the InboxRelatedByTouser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinInboxRelatedByTouser($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('InboxRelatedByTouser');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'InboxRelatedByTouser');
		}
		
		return $this;
	}

	/**
	 * Use the InboxRelatedByTouser relation Inbox object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    InboxQuery A secondary query class using the current class as primary query
	 */
	public function useInboxRelatedByTouserQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinInboxRelatedByTouser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'InboxRelatedByTouser', 'InboxQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Registration $registration Object to remove from the list of results
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function prune($registration = null)
	{
		if ($registration) {
			$this->addUsingAlias(RegistrationPeer::USERNAME, $registration->getUsername(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseRegistrationQuery
