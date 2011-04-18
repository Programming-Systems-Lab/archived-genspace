<?php


/**
 * Base class that represents a query for the 'registration' table.
 *
 * 
 *
 * @method     RegistrationQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     RegistrationQuery orderByPhone($order = Criteria::ASC) Order by the PHONE column
 * @method     RegistrationQuery orderByInterests($order = Criteria::ASC) Order by the INTERESTS column
 * @method     RegistrationQuery orderByState($order = Criteria::ASC) Order by the STATE column
 * @method     RegistrationQuery orderByOnlineStatus($order = Criteria::ASC) Order by the online_status column
 * @method     RegistrationQuery orderByPassword($order = Criteria::ASC) Order by the PASSWORD column
 * @method     RegistrationQuery orderByCity($order = Criteria::ASC) Order by the CITY column
 * @method     RegistrationQuery orderByUsername($order = Criteria::ASC) Order by the USERNAME column
 * @method     RegistrationQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     RegistrationQuery orderByFirstName($order = Criteria::ASC) Order by the first_name column
 * @method     RegistrationQuery orderByDatavisibility($order = Criteria::ASC) Order by the DATAVISIBILITY column
 * @method     RegistrationQuery orderByWorkTitle($order = Criteria::ASC) Order by the work_title column
 * @method     RegistrationQuery orderByLastName($order = Criteria::ASC) Order by the last_name column
 * @method     RegistrationQuery orderByZipcode($order = Criteria::ASC) Order by the ZIPCODE column
 * @method     RegistrationQuery orderByLabAffiliation($order = Criteria::ASC) Order by the lab_affiliation column
 * @method     RegistrationQuery orderByAddr1($order = Criteria::ASC) Order by the ADDR1 column
 * @method     RegistrationQuery orderByAddr2($order = Criteria::ASC) Order by the ADDR2 column
 * @method     RegistrationQuery orderByEmail($order = Criteria::ASC) Order by the EMAIL column
 * @method     RegistrationQuery orderByLogdata($order = Criteria::ASC) Order by the LOGDATA column
 * @method     RegistrationQuery orderByRootfolderId($order = Criteria::ASC) Order by the ROOTFOLDER_ID column
 *
 * @method     RegistrationQuery groupById() Group by the ID column
 * @method     RegistrationQuery groupByPhone() Group by the PHONE column
 * @method     RegistrationQuery groupByInterests() Group by the INTERESTS column
 * @method     RegistrationQuery groupByState() Group by the STATE column
 * @method     RegistrationQuery groupByOnlineStatus() Group by the online_status column
 * @method     RegistrationQuery groupByPassword() Group by the PASSWORD column
 * @method     RegistrationQuery groupByCity() Group by the CITY column
 * @method     RegistrationQuery groupByUsername() Group by the USERNAME column
 * @method     RegistrationQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     RegistrationQuery groupByFirstName() Group by the first_name column
 * @method     RegistrationQuery groupByDatavisibility() Group by the DATAVISIBILITY column
 * @method     RegistrationQuery groupByWorkTitle() Group by the work_title column
 * @method     RegistrationQuery groupByLastName() Group by the last_name column
 * @method     RegistrationQuery groupByZipcode() Group by the ZIPCODE column
 * @method     RegistrationQuery groupByLabAffiliation() Group by the lab_affiliation column
 * @method     RegistrationQuery groupByAddr1() Group by the ADDR1 column
 * @method     RegistrationQuery groupByAddr2() Group by the ADDR2 column
 * @method     RegistrationQuery groupByEmail() Group by the EMAIL column
 * @method     RegistrationQuery groupByLogdata() Group by the LOGDATA column
 * @method     RegistrationQuery groupByRootfolderId() Group by the ROOTFOLDER_ID column
 *
 * @method     RegistrationQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     RegistrationQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     RegistrationQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     RegistrationQuery leftJoinToolcomments($relationAlias = null) Adds a LEFT JOIN clause to the query using the Toolcomments relation
 * @method     RegistrationQuery rightJoinToolcomments($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Toolcomments relation
 * @method     RegistrationQuery innerJoinToolcomments($relationAlias = null) Adds a INNER JOIN clause to the query using the Toolcomments relation
 *
 * @method     RegistrationQuery leftJoinToolratings($relationAlias = null) Adds a LEFT JOIN clause to the query using the Toolratings relation
 * @method     RegistrationQuery rightJoinToolratings($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Toolratings relation
 * @method     RegistrationQuery innerJoinToolratings($relationAlias = null) Adds a INNER JOIN clause to the query using the Toolratings relation
 *
 * @method     RegistrationQuery leftJoinWorkflowcomments($relationAlias = null) Adds a LEFT JOIN clause to the query using the Workflowcomments relation
 * @method     RegistrationQuery rightJoinWorkflowcomments($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Workflowcomments relation
 * @method     RegistrationQuery innerJoinWorkflowcomments($relationAlias = null) Adds a INNER JOIN clause to the query using the Workflowcomments relation
 *
 * @method     RegistrationQuery leftJoinWorkflowratings($relationAlias = null) Adds a LEFT JOIN clause to the query using the Workflowratings relation
 * @method     RegistrationQuery rightJoinWorkflowratings($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Workflowratings relation
 * @method     RegistrationQuery innerJoinWorkflowratings($relationAlias = null) Adds a INNER JOIN clause to the query using the Workflowratings relation
 *
 * @method     RegistrationQuery leftJoinAnnotation($relationAlias = null) Adds a LEFT JOIN clause to the query using the Annotation relation
 * @method     RegistrationQuery rightJoinAnnotation($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Annotation relation
 * @method     RegistrationQuery innerJoinAnnotation($relationAlias = null) Adds a INNER JOIN clause to the query using the Annotation relation
 *
 * @method     RegistrationQuery leftJoinHistory($relationAlias = null) Adds a LEFT JOIN clause to the query using the History relation
 * @method     RegistrationQuery rightJoinHistory($relationAlias = null) Adds a RIGHT JOIN clause to the query using the History relation
 * @method     RegistrationQuery innerJoinHistory($relationAlias = null) Adds a INNER JOIN clause to the query using the History relation
 *
 * @method     RegistrationQuery leftJoinWorkspace($relationAlias = null) Adds a LEFT JOIN clause to the query using the Workspace relation
 * @method     RegistrationQuery rightJoinWorkspace($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Workspace relation
 * @method     RegistrationQuery innerJoinWorkspace($relationAlias = null) Adds a INNER JOIN clause to the query using the Workspace relation
 *
 * @method     RegistrationQuery leftJoinWorkspaceUser($relationAlias = null) Adds a LEFT JOIN clause to the query using the WorkspaceUser relation
 * @method     RegistrationQuery rightJoinWorkspaceUser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the WorkspaceUser relation
 * @method     RegistrationQuery innerJoinWorkspaceUser($relationAlias = null) Adds a INNER JOIN clause to the query using the WorkspaceUser relation
 *
 * @method     Registration findOne(PropelPDO $con = null) Return the first Registration matching the query
 * @method     Registration findOneOrCreate(PropelPDO $con = null) Return the first Registration matching the query, or a new Registration object populated from the query conditions when no match is found
 *
 * @method     Registration findOneById(int $ID) Return the first Registration filtered by the ID column
 * @method     Registration findOneByPhone(string $PHONE) Return the first Registration filtered by the PHONE column
 * @method     Registration findOneByInterests(string $INTERESTS) Return the first Registration filtered by the INTERESTS column
 * @method     Registration findOneByState(string $STATE) Return the first Registration filtered by the STATE column
 * @method     Registration findOneByOnlineStatus(int $online_status) Return the first Registration filtered by the online_status column
 * @method     Registration findOneByPassword(string $PASSWORD) Return the first Registration filtered by the PASSWORD column
 * @method     Registration findOneByCity(string $CITY) Return the first Registration filtered by the CITY column
 * @method     Registration findOneByUsername(string $USERNAME) Return the first Registration filtered by the USERNAME column
 * @method     Registration findOneByCreatedat(string $CREATEDAT) Return the first Registration filtered by the CREATEDAT column
 * @method     Registration findOneByFirstName(string $first_name) Return the first Registration filtered by the first_name column
 * @method     Registration findOneByDatavisibility(int $DATAVISIBILITY) Return the first Registration filtered by the DATAVISIBILITY column
 * @method     Registration findOneByWorkTitle(string $work_title) Return the first Registration filtered by the work_title column
 * @method     Registration findOneByLastName(string $last_name) Return the first Registration filtered by the last_name column
 * @method     Registration findOneByZipcode(string $ZIPCODE) Return the first Registration filtered by the ZIPCODE column
 * @method     Registration findOneByLabAffiliation(string $lab_affiliation) Return the first Registration filtered by the lab_affiliation column
 * @method     Registration findOneByAddr1(string $ADDR1) Return the first Registration filtered by the ADDR1 column
 * @method     Registration findOneByAddr2(string $ADDR2) Return the first Registration filtered by the ADDR2 column
 * @method     Registration findOneByEmail(string $EMAIL) Return the first Registration filtered by the EMAIL column
 * @method     Registration findOneByLogdata(int $LOGDATA) Return the first Registration filtered by the LOGDATA column
 * @method     Registration findOneByRootfolderId(int $ROOTFOLDER_ID) Return the first Registration filtered by the ROOTFOLDER_ID column
 *
 * @method     array findById(int $ID) Return Registration objects filtered by the ID column
 * @method     array findByPhone(string $PHONE) Return Registration objects filtered by the PHONE column
 * @method     array findByInterests(string $INTERESTS) Return Registration objects filtered by the INTERESTS column
 * @method     array findByState(string $STATE) Return Registration objects filtered by the STATE column
 * @method     array findByOnlineStatus(int $online_status) Return Registration objects filtered by the online_status column
 * @method     array findByPassword(string $PASSWORD) Return Registration objects filtered by the PASSWORD column
 * @method     array findByCity(string $CITY) Return Registration objects filtered by the CITY column
 * @method     array findByUsername(string $USERNAME) Return Registration objects filtered by the USERNAME column
 * @method     array findByCreatedat(string $CREATEDAT) Return Registration objects filtered by the CREATEDAT column
 * @method     array findByFirstName(string $first_name) Return Registration objects filtered by the first_name column
 * @method     array findByDatavisibility(int $DATAVISIBILITY) Return Registration objects filtered by the DATAVISIBILITY column
 * @method     array findByWorkTitle(string $work_title) Return Registration objects filtered by the work_title column
 * @method     array findByLastName(string $last_name) Return Registration objects filtered by the last_name column
 * @method     array findByZipcode(string $ZIPCODE) Return Registration objects filtered by the ZIPCODE column
 * @method     array findByLabAffiliation(string $lab_affiliation) Return Registration objects filtered by the lab_affiliation column
 * @method     array findByAddr1(string $ADDR1) Return Registration objects filtered by the ADDR1 column
 * @method     array findByAddr2(string $ADDR2) Return Registration objects filtered by the ADDR2 column
 * @method     array findByEmail(string $EMAIL) Return Registration objects filtered by the EMAIL column
 * @method     array findByLogdata(int $LOGDATA) Return Registration objects filtered by the LOGDATA column
 * @method     array findByRootfolderId(int $ROOTFOLDER_ID) Return Registration objects filtered by the ROOTFOLDER_ID column
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
		return $this->addUsingAlias(RegistrationPeer::ID, $key, Criteria::EQUAL);
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
		return $this->addUsingAlias(RegistrationPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(RegistrationPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the PHONE column
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
	 * Filter the query on the INTERESTS column
	 * 
	 * @param     string $interests The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByInterests($interests = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($interests)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $interests)) {
				$interests = str_replace('*', '%', $interests);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::INTERESTS, $interests, $comparison);
	}

	/**
	 * Filter the query on the STATE column
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
	 * Filter the query on the online_status column
	 * 
	 * @param     int|array $onlineStatus The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByOnlineStatus($onlineStatus = null, $comparison = null)
	{
		if (is_array($onlineStatus)) {
			$useMinMax = false;
			if (isset($onlineStatus['min'])) {
				$this->addUsingAlias(RegistrationPeer::ONLINE_STATUS, $onlineStatus['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($onlineStatus['max'])) {
				$this->addUsingAlias(RegistrationPeer::ONLINE_STATUS, $onlineStatus['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::ONLINE_STATUS, $onlineStatus, $comparison);
	}

	/**
	 * Filter the query on the PASSWORD column
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
	 * Filter the query on the CITY column
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
	 * Filter the query on the USERNAME column
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
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(RegistrationPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(RegistrationPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::CREATEDAT, $createdat, $comparison);
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
	 * Filter the query on the DATAVISIBILITY column
	 * 
	 * @param     int|array $datavisibility The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByDatavisibility($datavisibility = null, $comparison = null)
	{
		if (is_array($datavisibility)) {
			$useMinMax = false;
			if (isset($datavisibility['min'])) {
				$this->addUsingAlias(RegistrationPeer::DATAVISIBILITY, $datavisibility['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($datavisibility['max'])) {
				$this->addUsingAlias(RegistrationPeer::DATAVISIBILITY, $datavisibility['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::DATAVISIBILITY, $datavisibility, $comparison);
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
	 * Filter the query on the ZIPCODE column
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
	 * Filter the query on the ADDR1 column
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
	 * Filter the query on the ADDR2 column
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
	 * Filter the query on the EMAIL column
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
	 * Filter the query on the LOGDATA column
	 * 
	 * @param     int|array $logdata The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByLogdata($logdata = null, $comparison = null)
	{
		if (is_array($logdata)) {
			$useMinMax = false;
			if (isset($logdata['min'])) {
				$this->addUsingAlias(RegistrationPeer::LOGDATA, $logdata['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($logdata['max'])) {
				$this->addUsingAlias(RegistrationPeer::LOGDATA, $logdata['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::LOGDATA, $logdata, $comparison);
	}

	/**
	 * Filter the query on the ROOTFOLDER_ID column
	 * 
	 * @param     int|array $rootfolderId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByRootfolderId($rootfolderId = null, $comparison = null)
	{
		if (is_array($rootfolderId)) {
			$useMinMax = false;
			if (isset($rootfolderId['min'])) {
				$this->addUsingAlias(RegistrationPeer::ROOTFOLDER_ID, $rootfolderId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($rootfolderId['max'])) {
				$this->addUsingAlias(RegistrationPeer::ROOTFOLDER_ID, $rootfolderId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(RegistrationPeer::ROOTFOLDER_ID, $rootfolderId, $comparison);
	}

	/**
	 * Filter the query by a related Toolcomments object
	 *
	 * @param     Toolcomments $toolcomments  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByToolcomments($toolcomments, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $toolcomments->getCreatorId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Toolcomments relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinToolcomments($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Toolcomments');
		
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
			$this->addJoinObject($join, 'Toolcomments');
		}
		
		return $this;
	}

	/**
	 * Use the Toolcomments relation Toolcomments object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    ToolcommentsQuery A secondary query class using the current class as primary query
	 */
	public function useToolcommentsQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinToolcomments($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Toolcomments', 'ToolcommentsQuery');
	}

	/**
	 * Filter the query by a related Toolratings object
	 *
	 * @param     Toolratings $toolratings  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByToolratings($toolratings, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $toolratings->getCreatorId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Toolratings relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinToolratings($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Toolratings');
		
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
			$this->addJoinObject($join, 'Toolratings');
		}
		
		return $this;
	}

	/**
	 * Use the Toolratings relation Toolratings object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    ToolratingsQuery A secondary query class using the current class as primary query
	 */
	public function useToolratingsQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinToolratings($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Toolratings', 'ToolratingsQuery');
	}

	/**
	 * Filter the query by a related Workflowcomments object
	 *
	 * @param     Workflowcomments $workflowcomments  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByWorkflowcomments($workflowcomments, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $workflowcomments->getCreatorId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Workflowcomments relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinWorkflowcomments($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Workflowcomments');
		
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
			$this->addJoinObject($join, 'Workflowcomments');
		}
		
		return $this;
	}

	/**
	 * Use the Workflowcomments relation Workflowcomments object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkflowcommentsQuery A secondary query class using the current class as primary query
	 */
	public function useWorkflowcommentsQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinWorkflowcomments($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Workflowcomments', 'WorkflowcommentsQuery');
	}

	/**
	 * Filter the query by a related Workflowratings object
	 *
	 * @param     Workflowratings $workflowratings  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByWorkflowratings($workflowratings, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $workflowratings->getCreatorId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Workflowratings relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinWorkflowratings($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Workflowratings');
		
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
			$this->addJoinObject($join, 'Workflowratings');
		}
		
		return $this;
	}

	/**
	 * Use the Workflowratings relation Workflowratings object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkflowratingsQuery A secondary query class using the current class as primary query
	 */
	public function useWorkflowratingsQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinWorkflowratings($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Workflowratings', 'WorkflowratingsQuery');
	}

	/**
	 * Filter the query by a related Annotation object
	 *
	 * @param     Annotation $annotation  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByAnnotation($annotation, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $annotation->getCreator(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Annotation relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinAnnotation($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Annotation');
		
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
			$this->addJoinObject($join, 'Annotation');
		}
		
		return $this;
	}

	/**
	 * Use the Annotation relation Annotation object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AnnotationQuery A secondary query class using the current class as primary query
	 */
	public function useAnnotationQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinAnnotation($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Annotation', 'AnnotationQuery');
	}

	/**
	 * Filter the query by a related History object
	 *
	 * @param     History $history  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByHistory($history, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $history->getUid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the History relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinHistory($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('History');
		
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
			$this->addJoinObject($join, 'History');
		}
		
		return $this;
	}

	/**
	 * Use the History relation History object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    HistoryQuery A secondary query class using the current class as primary query
	 */
	public function useHistoryQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinHistory($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'History', 'HistoryQuery');
	}

	/**
	 * Filter the query by a related Workspace object
	 *
	 * @param     Workspace $workspace  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByWorkspace($workspace, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $workspace->getCreator(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Workspace relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinWorkspace($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Workspace');
		
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
			$this->addJoinObject($join, 'Workspace');
		}
		
		return $this;
	}

	/**
	 * Use the Workspace relation Workspace object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceQuery A secondary query class using the current class as primary query
	 */
	public function useWorkspaceQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinWorkspace($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Workspace', 'WorkspaceQuery');
	}

	/**
	 * Filter the query by a related WorkspaceUser object
	 *
	 * @param     WorkspaceUser $workspaceUser  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function filterByWorkspaceUser($workspaceUser, $comparison = null)
	{
		return $this
			->addUsingAlias(RegistrationPeer::ID, $workspaceUser->getUid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the WorkspaceUser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery The current query, for fluid interface
	 */
	public function joinWorkspaceUser($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('WorkspaceUser');
		
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
			$this->addJoinObject($join, 'WorkspaceUser');
		}
		
		return $this;
	}

	/**
	 * Use the WorkspaceUser relation WorkspaceUser object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceUserQuery A secondary query class using the current class as primary query
	 */
	public function useWorkspaceUserQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinWorkspaceUser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'WorkspaceUser', 'WorkspaceUserQuery');
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
			$this->addUsingAlias(RegistrationPeer::ID, $registration->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseRegistrationQuery
