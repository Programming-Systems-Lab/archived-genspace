<?php


/**
 * Base class that represents a query for the 'workspace_user' table.
 *
 * 
 *
 * @method     WorkspaceUserQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     WorkspaceUserQuery orderByWspid($order = Criteria::ASC) Order by the wspid column
 * @method     WorkspaceUserQuery orderByUid($order = Criteria::ASC) Order by the uid column
 * @method     WorkspaceUserQuery orderByGid($order = Criteria::ASC) Order by the gid column
 *
 * @method     WorkspaceUserQuery groupById() Group by the id column
 * @method     WorkspaceUserQuery groupByWspid() Group by the wspid column
 * @method     WorkspaceUserQuery groupByUid() Group by the uid column
 * @method     WorkspaceUserQuery groupByGid() Group by the gid column
 *
 * @method     WorkspaceUserQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkspaceUserQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkspaceUserQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     WorkspaceUserQuery leftJoinWorkspace($relationAlias = null) Adds a LEFT JOIN clause to the query using the Workspace relation
 * @method     WorkspaceUserQuery rightJoinWorkspace($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Workspace relation
 * @method     WorkspaceUserQuery innerJoinWorkspace($relationAlias = null) Adds a INNER JOIN clause to the query using the Workspace relation
 *
 * @method     WorkspaceUserQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     WorkspaceUserQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     WorkspaceUserQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     WorkspaceUserQuery leftJoinAccess($relationAlias = null) Adds a LEFT JOIN clause to the query using the Access relation
 * @method     WorkspaceUserQuery rightJoinAccess($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Access relation
 * @method     WorkspaceUserQuery innerJoinAccess($relationAlias = null) Adds a INNER JOIN clause to the query using the Access relation
 *
 * @method     WorkspaceUser findOne(PropelPDO $con = null) Return the first WorkspaceUser matching the query
 * @method     WorkspaceUser findOneOrCreate(PropelPDO $con = null) Return the first WorkspaceUser matching the query, or a new WorkspaceUser object populated from the query conditions when no match is found
 *
 * @method     WorkspaceUser findOneById(int $id) Return the first WorkspaceUser filtered by the id column
 * @method     WorkspaceUser findOneByWspid(int $wspid) Return the first WorkspaceUser filtered by the wspid column
 * @method     WorkspaceUser findOneByUid(int $uid) Return the first WorkspaceUser filtered by the uid column
 * @method     WorkspaceUser findOneByGid(int $gid) Return the first WorkspaceUser filtered by the gid column
 *
 * @method     array findById(int $id) Return WorkspaceUser objects filtered by the id column
 * @method     array findByWspid(int $wspid) Return WorkspaceUser objects filtered by the wspid column
 * @method     array findByUid(int $uid) Return WorkspaceUser objects filtered by the uid column
 * @method     array findByGid(int $gid) Return WorkspaceUser objects filtered by the gid column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkspaceUserQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkspaceUserQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'WorkspaceUser', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkspaceUserQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkspaceUserQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkspaceUserQuery) {
			return $criteria;
		}
		$query = new WorkspaceUserQuery();
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
	 * @return    WorkspaceUser|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkspaceUserPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkspaceUserPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkspaceUserPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkspaceUserPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the wspid column
	 * 
	 * @param     int|array $wspid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByWspid($wspid = null, $comparison = null)
	{
		if (is_array($wspid)) {
			$useMinMax = false;
			if (isset($wspid['min'])) {
				$this->addUsingAlias(WorkspaceUserPeer::WSPID, $wspid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($wspid['max'])) {
				$this->addUsingAlias(WorkspaceUserPeer::WSPID, $wspid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspaceUserPeer::WSPID, $wspid, $comparison);
	}

	/**
	 * Filter the query on the uid column
	 * 
	 * @param     int|array $uid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByUid($uid = null, $comparison = null)
	{
		if (is_array($uid)) {
			$useMinMax = false;
			if (isset($uid['min'])) {
				$this->addUsingAlias(WorkspaceUserPeer::UID, $uid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($uid['max'])) {
				$this->addUsingAlias(WorkspaceUserPeer::UID, $uid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspaceUserPeer::UID, $uid, $comparison);
	}

	/**
	 * Filter the query on the gid column
	 * 
	 * @param     int|array $gid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByGid($gid = null, $comparison = null)
	{
		if (is_array($gid)) {
			$useMinMax = false;
			if (isset($gid['min'])) {
				$this->addUsingAlias(WorkspaceUserPeer::GID, $gid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($gid['max'])) {
				$this->addUsingAlias(WorkspaceUserPeer::GID, $gid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(WorkspaceUserPeer::GID, $gid, $comparison);
	}

	/**
	 * Filter the query by a related Workspace object
	 *
	 * @param     Workspace $workspace  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByWorkspace($workspace, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspaceUserPeer::WSPID, $workspace->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Workspace relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
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
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspaceUserPeer::UID, $registration->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
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
	 * Filter the query by a related Access object
	 *
	 * @param     Access $access  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function filterByAccess($access, $comparison = null)
	{
		return $this
			->addUsingAlias(WorkspaceUserPeer::GID, $access->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Access relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function joinAccess($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Access');
		
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
			$this->addJoinObject($join, 'Access');
		}
		
		return $this;
	}

	/**
	 * Use the Access relation Access object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AccessQuery A secondary query class using the current class as primary query
	 */
	public function useAccessQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinAccess($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Access', 'AccessQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     WorkspaceUser $workspaceUser Object to remove from the list of results
	 *
	 * @return    WorkspaceUserQuery The current query, for fluid interface
	 */
	public function prune($workspaceUser = null)
	{
		if ($workspaceUser) {
			$this->addUsingAlias(WorkspaceUserPeer::ID, $workspaceUser->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkspaceUserQuery
