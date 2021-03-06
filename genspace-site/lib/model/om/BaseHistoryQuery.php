<?php


/**
 * Base class that represents a query for the 'history' table.
 *
 * 
 *
 * @method     HistoryQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     HistoryQuery orderByWspid($order = Criteria::ASC) Order by the wspid column
 * @method     HistoryQuery orderByUid($order = Criteria::ASC) Order by the uid column
 * @method     HistoryQuery orderByType($order = Criteria::ASC) Order by the type column
 * @method     HistoryQuery orderByAccessedat($order = Criteria::ASC) Order by the accessedAt column
 *
 * @method     HistoryQuery groupById() Group by the id column
 * @method     HistoryQuery groupByWspid() Group by the wspid column
 * @method     HistoryQuery groupByUid() Group by the uid column
 * @method     HistoryQuery groupByType() Group by the type column
 * @method     HistoryQuery groupByAccessedat() Group by the accessedAt column
 *
 * @method     HistoryQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     HistoryQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     HistoryQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     HistoryQuery leftJoinWorkspace($relationAlias = null) Adds a LEFT JOIN clause to the query using the Workspace relation
 * @method     HistoryQuery rightJoinWorkspace($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Workspace relation
 * @method     HistoryQuery innerJoinWorkspace($relationAlias = null) Adds a INNER JOIN clause to the query using the Workspace relation
 *
 * @method     HistoryQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     HistoryQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     HistoryQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     History findOne(PropelPDO $con = null) Return the first History matching the query
 * @method     History findOneOrCreate(PropelPDO $con = null) Return the first History matching the query, or a new History object populated from the query conditions when no match is found
 *
 * @method     History findOneById(int $id) Return the first History filtered by the id column
 * @method     History findOneByWspid(int $wspid) Return the first History filtered by the wspid column
 * @method     History findOneByUid(int $uid) Return the first History filtered by the uid column
 * @method     History findOneByType(string $type) Return the first History filtered by the type column
 * @method     History findOneByAccessedat(string $accessedAt) Return the first History filtered by the accessedAt column
 *
 * @method     array findById(int $id) Return History objects filtered by the id column
 * @method     array findByWspid(int $wspid) Return History objects filtered by the wspid column
 * @method     array findByUid(int $uid) Return History objects filtered by the uid column
 * @method     array findByType(string $type) Return History objects filtered by the type column
 * @method     array findByAccessedat(string $accessedAt) Return History objects filtered by the accessedAt column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseHistoryQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseHistoryQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'History', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new HistoryQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    HistoryQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof HistoryQuery) {
			return $criteria;
		}
		$query = new HistoryQuery();
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
	 * @return    History|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = HistoryPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(HistoryPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(HistoryPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(HistoryPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the wspid column
	 * 
	 * @param     int|array $wspid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByWspid($wspid = null, $comparison = null)
	{
		if (is_array($wspid)) {
			$useMinMax = false;
			if (isset($wspid['min'])) {
				$this->addUsingAlias(HistoryPeer::WSPID, $wspid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($wspid['max'])) {
				$this->addUsingAlias(HistoryPeer::WSPID, $wspid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(HistoryPeer::WSPID, $wspid, $comparison);
	}

	/**
	 * Filter the query on the uid column
	 * 
	 * @param     int|array $uid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByUid($uid = null, $comparison = null)
	{
		if (is_array($uid)) {
			$useMinMax = false;
			if (isset($uid['min'])) {
				$this->addUsingAlias(HistoryPeer::UID, $uid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($uid['max'])) {
				$this->addUsingAlias(HistoryPeer::UID, $uid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(HistoryPeer::UID, $uid, $comparison);
	}

	/**
	 * Filter the query on the type column
	 * 
	 * @param     string $type The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByType($type = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($type)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $type)) {
				$type = str_replace('*', '%', $type);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(HistoryPeer::TYPE, $type, $comparison);
	}

	/**
	 * Filter the query on the accessedAt column
	 * 
	 * @param     string|array $accessedat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByAccessedat($accessedat = null, $comparison = null)
	{
		if (is_array($accessedat)) {
			$useMinMax = false;
			if (isset($accessedat['min'])) {
				$this->addUsingAlias(HistoryPeer::ACCESSEDAT, $accessedat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($accessedat['max'])) {
				$this->addUsingAlias(HistoryPeer::ACCESSEDAT, $accessedat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(HistoryPeer::ACCESSEDAT, $accessedat, $comparison);
	}

	/**
	 * Filter the query by a related Workspace object
	 *
	 * @param     Workspace $workspace  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByWorkspace($workspace, $comparison = null)
	{
		return $this
			->addUsingAlias(HistoryPeer::WSPID, $workspace->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Workspace relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    HistoryQuery The current query, for fluid interface
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
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(HistoryPeer::UID, $registration->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    HistoryQuery The current query, for fluid interface
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
	 * Exclude object from result
	 *
	 * @param     History $history Object to remove from the list of results
	 *
	 * @return    HistoryQuery The current query, for fluid interface
	 */
	public function prune($history = null)
	{
		if ($history) {
			$this->addUsingAlias(HistoryPeer::ID, $history->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseHistoryQuery
