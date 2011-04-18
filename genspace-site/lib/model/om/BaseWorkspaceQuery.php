<?php


/**
 * Base class that represents a query for the 'workspace' table.
 *
 * 
 *
 * @method     WorkspaceQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     WorkspaceQuery orderByTitle($order = Criteria::ASC) Order by the title column
 * @method     WorkspaceQuery orderByCreator($order = Criteria::ASC) Order by the creator column
 * @method     WorkspaceQuery orderByDescription($order = Criteria::ASC) Order by the description column
 * @method     WorkspaceQuery orderByLocation($order = Criteria::ASC) Order by the location column
 * @method     WorkspaceQuery orderByCreatedat($order = Criteria::ASC) Order by the createdAt column
 * @method     WorkspaceQuery orderByVersion($order = Criteria::ASC) Order by the version column
 * @method     WorkspaceQuery orderByLastsync($order = Criteria::ASC) Order by the lastSync column
 * @method     WorkspaceQuery orderByLocked($order = Criteria::ASC) Order by the locked column
 * @method     WorkspaceQuery orderByLastlockeduser($order = Criteria::ASC) Order by the lastLockedUser column
 *
 * @method     WorkspaceQuery groupById() Group by the id column
 * @method     WorkspaceQuery groupByTitle() Group by the title column
 * @method     WorkspaceQuery groupByCreator() Group by the creator column
 * @method     WorkspaceQuery groupByDescription() Group by the description column
 * @method     WorkspaceQuery groupByLocation() Group by the location column
 * @method     WorkspaceQuery groupByCreatedat() Group by the createdAt column
 * @method     WorkspaceQuery groupByVersion() Group by the version column
 * @method     WorkspaceQuery groupByLastsync() Group by the lastSync column
 * @method     WorkspaceQuery groupByLocked() Group by the locked column
 * @method     WorkspaceQuery groupByLastlockeduser() Group by the lastLockedUser column
 *
 * @method     WorkspaceQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkspaceQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkspaceQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     WorkspaceQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     WorkspaceQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     WorkspaceQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     WorkspaceQuery leftJoinAnnotation($relationAlias = null) Adds a LEFT JOIN clause to the query using the Annotation relation
 * @method     WorkspaceQuery rightJoinAnnotation($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Annotation relation
 * @method     WorkspaceQuery innerJoinAnnotation($relationAlias = null) Adds a INNER JOIN clause to the query using the Annotation relation
 *
 * @method     WorkspaceQuery leftJoinHistory($relationAlias = null) Adds a LEFT JOIN clause to the query using the History relation
 * @method     WorkspaceQuery rightJoinHistory($relationAlias = null) Adds a RIGHT JOIN clause to the query using the History relation
 * @method     WorkspaceQuery innerJoinHistory($relationAlias = null) Adds a INNER JOIN clause to the query using the History relation
 *
 * @method     WorkspaceQuery leftJoinWorkspaceUser($relationAlias = null) Adds a LEFT JOIN clause to the query using the WorkspaceUser relation
 * @method     WorkspaceQuery rightJoinWorkspaceUser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the WorkspaceUser relation
 * @method     WorkspaceQuery innerJoinWorkspaceUser($relationAlias = null) Adds a INNER JOIN clause to the query using the WorkspaceUser relation
 *
 * @method     Workspace findOne(PropelPDO $con = null) Return the first Workspace matching the query
 * @method     Workspace findOneOrCreate(PropelPDO $con = null) Return the first Workspace matching the query, or a new Workspace object populated from the query conditions when no match is found
 *
 * @method     Workspace findOneById(int $id) Return the first Workspace filtered by the id column
 * @method     Workspace findOneByTitle(string $title) Return the first Workspace filtered by the title column
 * @method     Workspace findOneByCreator(int $creator) Return the first Workspace filtered by the creator column
 * @method     Workspace findOneByDescription(string $description) Return the first Workspace filtered by the description column
 * @method     Workspace findOneByLocation(string $location) Return the first Workspace filtered by the location column
 * @method     Workspace findOneByCreatedat(string $createdAt) Return the first Workspace filtered by the createdAt column
 * @method     Workspace findOneByVersion(int $version) Return the first Workspace filtered by the version column
 * @method     Workspace findOneByLastsync(string $lastSync) Return the first Workspace filtered by the lastSync column
 * @method     Workspace findOneByLocked(int $locked) Return the first Workspace filtered by the locked column
 * @method     Workspace findOneByLastlockeduser(int $lastLockedUser) Return the first Workspace filtered by the lastLockedUser column
 *
 * @method     array findById(int $id) Return Workspace objects filtered by the id column
 * @method     array findByTitle(string $title) Return Workspace objects filtered by the title column
 * @method     array findByCreator(int $creator) Return Workspace objects filtered by the creator column
 * @method     array findByDescription(string $description) Return Workspace objects filtered by the description column
 * @method     array findByLocation(string $location) Return Workspace objects filtered by the location column
 * @method     array findByCreatedat(string $createdAt) Return Workspace objects filtered by the createdAt column
 * @method     array findByVersion(int $version) Return Workspace objects filtered by the version column
 * @method     array findByLastsync(string $lastSync) Return Workspace objects filtered by the lastSync column
 * @method     array findByLocked(int $locked) Return Workspace objects filtered by the locked column
 * @method     array findByLastlockeduser(int $lastLockedUser) Return Workspace objects filtered by the lastLockedUser column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkspaceQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkspaceQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Workspace', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkspaceQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkspaceQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkspaceQuery) {
			return $criteria;
		}
		$query = new WorkspaceQuery();
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
	 * @return    Workspace|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkspacePeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkspacePeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkspacePeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkspacePeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the title column
	 * 
	 * @param     string $title The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByTitle($title = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($title)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $title)) {
				$title = str_replace('*', '%', $title);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::TITLE, $title, $comparison);
	}

	/**
	 * Filter the query on the creator column
	 * 
	 * @param     int|array $creator The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByCreator($creator = null, $comparison = null)
	{
		if (is_array($creator)) {
			$useMinMax = false;
			if (isset($creator['min'])) {
				$this->addUsingAlias(WorkspacePeer::CREATOR, $creator['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creator['max'])) {
				$this->addUsingAlias(WorkspacePeer::CREATOR, $creator['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::CREATOR, $creator, $comparison);
	}

	/**
	 * Filter the query on the description column
	 * 
	 * @param     string $description The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
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
		return $this->addUsingAlias(WorkspacePeer::DESCRIPTION, $description, $comparison);
	}

	/**
	 * Filter the query on the location column
	 * 
	 * @param     string $location The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByLocation($location = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($location)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $location)) {
				$location = str_replace('*', '%', $location);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::LOCATION, $location, $comparison);
	}

	/**
	 * Filter the query on the createdAt column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(WorkspacePeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(WorkspacePeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the version column
	 * 
	 * @param     int|array $version The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByVersion($version = null, $comparison = null)
	{
		if (is_array($version)) {
			$useMinMax = false;
			if (isset($version['min'])) {
				$this->addUsingAlias(WorkspacePeer::VERSION, $version['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($version['max'])) {
				$this->addUsingAlias(WorkspacePeer::VERSION, $version['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::VERSION, $version, $comparison);
	}

	/**
	 * Filter the query on the lastSync column
	 * 
	 * @param     string|array $lastsync The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByLastsync($lastsync = null, $comparison = null)
	{
		if (is_array($lastsync)) {
			$useMinMax = false;
			if (isset($lastsync['min'])) {
				$this->addUsingAlias(WorkspacePeer::LASTSYNC, $lastsync['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($lastsync['max'])) {
				$this->addUsingAlias(WorkspacePeer::LASTSYNC, $lastsync['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::LASTSYNC, $lastsync, $comparison);
	}

	/**
	 * Filter the query on the locked column
	 * 
	 * @param     int|array $locked The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByLocked($locked = null, $comparison = null)
	{
		if (is_array($locked)) {
			$useMinMax = false;
			if (isset($locked['min'])) {
				$this->addUsingAlias(WorkspacePeer::LOCKED, $locked['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($locked['max'])) {
				$this->addUsingAlias(WorkspacePeer::LOCKED, $locked['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::LOCKED, $locked, $comparison);
	}

	/**
	 * Filter the query on the lastLockedUser column
	 * 
	 * @param     int|array $lastlockeduser The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByLastlockeduser($lastlockeduser = null, $comparison = null)
	{
		if (is_array($lastlockeduser)) {
			$useMinMax = false;
			if (isset($lastlockeduser['min'])) {
				$this->addUsingAlias(WorkspacePeer::LASTLOCKEDUSER, $lastlockeduser['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($lastlockeduser['max'])) {
				$this->addUsingAlias(WorkspacePeer::LASTLOCKEDUSER, $lastlockeduser['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspacePeer::LASTLOCKEDUSER, $lastlockeduser, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspacePeer::CREATOR, $registration->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function joinRegistration($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Registration');
		
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
			$this->addJoinObject($join, 'Registration');
		}
		
		return $this;
	}

	/**
	 * Use the Registration relation Registration object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery A secondary query class using the current class as primary query
	 */
	public function useRegistrationQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinRegistration($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Registration', 'RegistrationQuery');
	}

	/**
	 * Filter the query by a related Annotation object
	 *
	 * @param     Annotation $annotation  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByAnnotation($annotation, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspacePeer::ID, $annotation->getWspid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Annotation relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
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
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByHistory($history, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspacePeer::ID, $history->getWspid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the History relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
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
	 * Filter the query by a related WorkspaceUser object
	 *
	 * @param     WorkspaceUser $workspaceUser  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function filterByWorkspaceUser($workspaceUser, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspacePeer::ID, $workspaceUser->getWspid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the WorkspaceUser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
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
	 * @param     Workspace $workspace Object to remove from the list of results
	 *
	 * @return    WorkspaceQuery The current query, for fluid interface
	 */
	public function prune($workspace = null)
	{
		if ($workspace) {
			$this->addUsingAlias(WorkspacePeer::ID, $workspace->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkspaceQuery
