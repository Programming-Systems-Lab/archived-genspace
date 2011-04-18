<?php


/**
 * Base class that represents a query for the 'access' table.
 *
 * 
 *
 * @method     AccessQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     AccessQuery orderByName($order = Criteria::ASC) Order by the name column
 *
 * @method     AccessQuery groupById() Group by the id column
 * @method     AccessQuery groupByName() Group by the name column
 *
 * @method     AccessQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     AccessQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     AccessQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     AccessQuery leftJoinWorkspaceUser($relationAlias = null) Adds a LEFT JOIN clause to the query using the WorkspaceUser relation
 * @method     AccessQuery rightJoinWorkspaceUser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the WorkspaceUser relation
 * @method     AccessQuery innerJoinWorkspaceUser($relationAlias = null) Adds a INNER JOIN clause to the query using the WorkspaceUser relation
 *
 * @method     Access findOne(PropelPDO $con = null) Return the first Access matching the query
 * @method     Access findOneOrCreate(PropelPDO $con = null) Return the first Access matching the query, or a new Access object populated from the query conditions when no match is found
 *
 * @method     Access findOneById(int $id) Return the first Access filtered by the id column
 * @method     Access findOneByName(string $name) Return the first Access filtered by the name column
 *
 * @method     array findById(int $id) Return Access objects filtered by the id column
 * @method     array findByName(string $name) Return Access objects filtered by the name column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseAccessQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseAccessQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Access', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new AccessQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    AccessQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof AccessQuery) {
			return $criteria;
		}
		$query = new AccessQuery();
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
	 * @return    Access|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = AccessPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    AccessQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(AccessPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    AccessQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(AccessPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AccessQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(AccessPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the name column
	 * 
	 * @param     string $name The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AccessQuery The current query, for fluid interface
	 */
	public function filterByName($name = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($name)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $name)) {
				$name = str_replace('*', '%', $name);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AccessPeer::NAME, $name, $comparison);
	}

	/**
	 * Filter the query by a related WorkspaceUser object
	 *
	 * @param     WorkspaceUser $workspaceUser  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AccessQuery The current query, for fluid interface
	 */
	public function filterByWorkspaceUser($workspaceUser, $comparison = null)
	{
		return $this
			->addUsingAlias(AccessPeer::ID, $workspaceUser->getGid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the WorkspaceUser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AccessQuery The current query, for fluid interface
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
	 * @param     Access $access Object to remove from the list of results
	 *
	 * @return    AccessQuery The current query, for fluid interface
	 */
	public function prune($access = null)
	{
		if ($access) {
			$this->addUsingAlias(AccessPeer::ID, $access->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseAccessQuery
