<?php


/**
 * Base class that represents a query for the 'tig_nodes' table.
 *
 * 
 *
 * @method     TigNodesQuery orderByNid($order = Criteria::ASC) Order by the nid column
 * @method     TigNodesQuery orderByParentNid($order = Criteria::ASC) Order by the parent_nid column
 * @method     TigNodesQuery orderByUid($order = Criteria::ASC) Order by the uid column
 * @method     TigNodesQuery orderByNode($order = Criteria::ASC) Order by the node column
 *
 * @method     TigNodesQuery groupByNid() Group by the nid column
 * @method     TigNodesQuery groupByParentNid() Group by the parent_nid column
 * @method     TigNodesQuery groupByUid() Group by the uid column
 * @method     TigNodesQuery groupByNode() Group by the node column
 *
 * @method     TigNodesQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     TigNodesQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     TigNodesQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     TigNodesQuery leftJoinTigUsers($relationAlias = null) Adds a LEFT JOIN clause to the query using the TigUsers relation
 * @method     TigNodesQuery rightJoinTigUsers($relationAlias = null) Adds a RIGHT JOIN clause to the query using the TigUsers relation
 * @method     TigNodesQuery innerJoinTigUsers($relationAlias = null) Adds a INNER JOIN clause to the query using the TigUsers relation
 *
 * @method     TigNodesQuery leftJoinTigPairs($relationAlias = null) Adds a LEFT JOIN clause to the query using the TigPairs relation
 * @method     TigNodesQuery rightJoinTigPairs($relationAlias = null) Adds a RIGHT JOIN clause to the query using the TigPairs relation
 * @method     TigNodesQuery innerJoinTigPairs($relationAlias = null) Adds a INNER JOIN clause to the query using the TigPairs relation
 *
 * @method     TigNodes findOne(PropelPDO $con = null) Return the first TigNodes matching the query
 * @method     TigNodes findOneOrCreate(PropelPDO $con = null) Return the first TigNodes matching the query, or a new TigNodes object populated from the query conditions when no match is found
 *
 * @method     TigNodes findOneByNid(string $nid) Return the first TigNodes filtered by the nid column
 * @method     TigNodes findOneByParentNid(string $parent_nid) Return the first TigNodes filtered by the parent_nid column
 * @method     TigNodes findOneByUid(string $uid) Return the first TigNodes filtered by the uid column
 * @method     TigNodes findOneByNode(string $node) Return the first TigNodes filtered by the node column
 *
 * @method     array findByNid(string $nid) Return TigNodes objects filtered by the nid column
 * @method     array findByParentNid(string $parent_nid) Return TigNodes objects filtered by the parent_nid column
 * @method     array findByUid(string $uid) Return TigNodes objects filtered by the uid column
 * @method     array findByNode(string $node) Return TigNodes objects filtered by the node column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTigNodesQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseTigNodesQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'TigNodes', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new TigNodesQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    TigNodesQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof TigNodesQuery) {
			return $criteria;
		}
		$query = new TigNodesQuery();
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
	 * @return    TigNodes|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = TigNodesPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(TigNodesPeer::NID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(TigNodesPeer::NID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the nid column
	 * 
	 * @param     string|array $nid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByNid($nid = null, $comparison = null)
	{
		if (is_array($nid) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(TigNodesPeer::NID, $nid, $comparison);
	}

	/**
	 * Filter the query on the parent_nid column
	 * 
	 * @param     string|array $parentNid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByParentNid($parentNid = null, $comparison = null)
	{
		if (is_array($parentNid)) {
			$useMinMax = false;
			if (isset($parentNid['min'])) {
				$this->addUsingAlias(TigNodesPeer::PARENT_NID, $parentNid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($parentNid['max'])) {
				$this->addUsingAlias(TigNodesPeer::PARENT_NID, $parentNid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigNodesPeer::PARENT_NID, $parentNid, $comparison);
	}

	/**
	 * Filter the query on the uid column
	 * 
	 * @param     string|array $uid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByUid($uid = null, $comparison = null)
	{
		if (is_array($uid)) {
			$useMinMax = false;
			if (isset($uid['min'])) {
				$this->addUsingAlias(TigNodesPeer::UID, $uid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($uid['max'])) {
				$this->addUsingAlias(TigNodesPeer::UID, $uid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigNodesPeer::UID, $uid, $comparison);
	}

	/**
	 * Filter the query on the node column
	 * 
	 * @param     string $node The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByNode($node = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($node)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $node)) {
				$node = str_replace('*', '%', $node);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TigNodesPeer::NODE, $node, $comparison);
	}

	/**
	 * Filter the query by a related TigUsers object
	 *
	 * @param     TigUsers $tigUsers  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByTigUsers($tigUsers, $comparison = null)
	{
		return $this
			->addUsingAlias(TigNodesPeer::UID, $tigUsers->getUid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the TigUsers relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function joinTigUsers($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('TigUsers');
		
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
			$this->addJoinObject($join, 'TigUsers');
		}
		
		return $this;
	}

	/**
	 * Use the TigUsers relation TigUsers object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigUsersQuery A secondary query class using the current class as primary query
	 */
	public function useTigUsersQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinTigUsers($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'TigUsers', 'TigUsersQuery');
	}

	/**
	 * Filter the query by a related TigPairs object
	 *
	 * @param     TigPairs $tigPairs  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function filterByTigPairs($tigPairs, $comparison = null)
	{
		return $this
			->addUsingAlias(TigNodesPeer::NID, $tigPairs->getNid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the TigPairs relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function joinTigPairs($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('TigPairs');
		
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
			$this->addJoinObject($join, 'TigPairs');
		}
		
		return $this;
	}

	/**
	 * Use the TigPairs relation TigPairs object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigPairsQuery A secondary query class using the current class as primary query
	 */
	public function useTigPairsQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinTigPairs($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'TigPairs', 'TigPairsQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     TigNodes $tigNodes Object to remove from the list of results
	 *
	 * @return    TigNodesQuery The current query, for fluid interface
	 */
	public function prune($tigNodes = null)
	{
		if ($tigNodes) {
			$this->addUsingAlias(TigNodesPeer::NID, $tigNodes->getNid(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseTigNodesQuery
